package servlets;

import db.clasess.Ticket;
import db.managers.TicketManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value="/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello World");
        ArrayList<Ticket> tickets = null;
//        ArrayList<Ticket> reverseTickets = new ArrayList<Ticket>();

        int pages = TicketManager.getAllTickets().size() / 3;

        if ((TicketManager.getAllTickets().size() - (3 * pages)) % 3 > 0) {
            pages += 1;
        }
        System.out.println("pages ="+pages);

        try{
            System.out.println("Yeap");
            int page = Integer.parseInt(request.getParameter("page"));
            tickets = TicketManager.getTicketsFromRange(page);
            request.setAttribute("tickets", tickets);
            request.setAttribute("active_page", page);
        }
        catch (Exception e){
            System.out.println("Noop");
            tickets = TicketManager.getTicketsFromRange(1);
            request.setAttribute("tickets", tickets);
            request.setAttribute("active_page", 1);
        }

        request.setAttribute("pages", pages);
        request.setAttribute("total", TicketManager.getAllTickets().size());
        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
    }
}
