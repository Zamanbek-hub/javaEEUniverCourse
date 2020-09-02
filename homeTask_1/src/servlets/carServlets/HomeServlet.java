package servlets.carServlets;

import db.classes.Car;
import db.managers.CarManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet( value = "/")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello World");
        ArrayList<Car> Cars = CarManager.getAllCars();
        ArrayList<Car> reverseCars = new ArrayList<Car>();

        /*
         * Чтобы новые добавленные обекты выходили сверху
         * */
        for (int i = Cars.size() - 1; i >= 0; i--) {
            reverseCars.add(Cars.get(i));
        }

        request.setAttribute("footballlers", reverseCars);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
