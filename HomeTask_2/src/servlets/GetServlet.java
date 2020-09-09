package servlets;

import db.classes.Bus;
import db.classes.Car;
import db.classes.Truck;
import db.managers.TransportManager;
import interfaces.Transports;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/get")
public class GetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Long id = Long.parseLong(request.getParameter("id"));
            Transports transport = TransportManager.getTransports(id);

            request.setAttribute("transport", transport);
            if (transport instanceof Car) {
                request.getRequestDispatcher("/views/gets/getCar.jsp").forward(request, response);
            }
            else if (transport instanceof Bus) {
                request.getRequestDispatcher("/views/gets/getBus.jsp").forward(request, response);
            }
            else if (transport instanceof Truck) {
                request.getRequestDispatcher("/views/gets/getTruck.jsp").forward(request, response);
            }

        }
        catch (Exception e){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<h1> Please write Exist ID </h1>");

        }
    }
}
