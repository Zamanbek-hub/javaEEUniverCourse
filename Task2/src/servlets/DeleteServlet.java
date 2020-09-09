package servlets;

import db.clasess.Ticket;
import db.managers.TicketManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
            System.out.println("we are in delete 1");
            Long id = Long.parseLong(request.getParameter("id"));
            TicketManager.deleteTicket(id);
            System.out.println("we are in delete 2");
            response.sendRedirect("/");
//        }
//        catch (Exception e){
//            response.setContentType("text/html");
//            PrintWriter out = response.getWriter();
//            out.print("<h1> Please write Exist ID </h1>");
//
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
