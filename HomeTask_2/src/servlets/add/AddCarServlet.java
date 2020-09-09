package servlets.add;

import db.classes.Bus;
import db.classes.Car;
import db.managers.TransportManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add/car")
public class AddCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String model = request.getParameter("model");
        String carcaseType = request.getParameter("carcaseType");
        int maxSpeed = Integer.parseInt(request.getParameter("maxSpeed"));
        double engineVolume = Double.parseDouble(request.getParameter("engineVolume"));
        int price = Integer.parseInt(request.getParameter("price"));
        int year = Integer.parseInt(request.getParameter("year"));

        TransportManager.addTransport(new Car(name,model,carcaseType,maxSpeed, engineVolume, price, year));

        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/add/addCar.jsp").forward(request, response);
    }
}
