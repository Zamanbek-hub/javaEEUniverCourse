package servlets.add;

import db.classes.Bus;
import db.managers.TransportManager;
import interfaces.Transports;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add/bus")
public class AddBusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String model = request.getParameter("model");
        int passengerPlaces = Integer.parseInt(request.getParameter("passengerPlaces"));
        int price = Integer.parseInt(request.getParameter("price"));

        TransportManager.addTransport(new Bus(name,model,passengerPlaces,price));

        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/add/addBus.jsp").forward(request, response);
    }
}
