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
        ArrayList<Ticket> tickets = null;

        // get pages count
        int pages = TicketManager.getAllTickets().size() / 3;

        // if the remainder > 0 then + 1 page
        if ((TicketManager.getAllTickets().size() - (3 * pages)) % 3 > 0) {
            pages += 1;
        }


        try{
            int page = Integer.parseInt(request.getParameter("page"));
            tickets = TicketManager.getTicketsFromRange(page);
            request.setAttribute("tickets", tickets);
            request.setAttribute("active_page", page);
        }
        catch (Exception e){
            // Default pages = 1
            tickets = TicketManager.getTicketsFromRange(1);
            request.setAttribute("tickets", tickets);
            request.setAttribute("active_page", 1);
        }

        request.setAttribute("pages", pages);
        request.setAttribute("total", TicketManager.getAllTickets().size());
        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
    }
}
