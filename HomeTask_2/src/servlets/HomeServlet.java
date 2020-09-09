package servlets;

import db.classes.Car;
import db.managers.TransportManager;
import interfaces.Transports;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Transports> transports = null;

        int pages = TransportManager.getAllTransports().size() / 3;

        if ((TransportManager.getAllTransports().size() - (3 * pages)) % 3 > 0) {
            pages += 1;
        }

        try{
            int page = Integer.parseInt(request.getParameter("page"));
            transports = TransportManager.getTransportsFromRange(page);
            request.setAttribute("transports", transports);
            request.setAttribute("active_page", page);
        }
        catch (Exception e){
            // Default pages = 1
            transports = TransportManager.getTransportsFromRange(1);
            request.setAttribute("transports", transports);
            request.setAttribute("active_page", 1);
        }

//        System.out.println("pages =" + pages);
        request.setAttribute("pages", pages);
        request.setAttribute("total", TransportManager.getAllTransports().size());
        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
    }
}
