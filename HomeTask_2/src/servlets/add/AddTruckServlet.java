package servlets.add;

import db.classes.Bus;
import db.classes.Truck;
import db.managers.TransportManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add/truck")
public class AddTruckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String model = request.getParameter("model");
        double liftingCapacity = Double.parseDouble(request.getParameter("liftingCapacity"));
        int price = Integer.parseInt(request.getParameter("price"));
        int trailerPrice = Integer.parseInt(request.getParameter("trailerPrice"));

        TransportManager.addTransport(new Truck(name,model,liftingCapacity,price,trailerPrice));

        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/add/addTruck.jsp").forward(request, response);
    }
}
