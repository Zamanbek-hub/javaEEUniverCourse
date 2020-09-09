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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value="/get")
public class GetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("we are here 1");
            Long id = Long.parseLong(request.getParameter("id"));
            Ticket ticket = TicketManager.getTicket(id);
            System.out.println("we are here 2");
            request.setAttribute("ticket", ticket);
            request.getRequestDispatcher("/views/get.jsp").forward(request, response);
        }
        catch (Exception e){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<h1> Please write Exist ID </h1>");

        }
    }
}
