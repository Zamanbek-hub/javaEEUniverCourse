package servlets;

import db.DBManager;
import db.Footballer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet( value = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello World");
        ArrayList<Footballer>  oldFootballers = DBManager.getFootballers();
        ArrayList<Footballer> newFootballers = new ArrayList<Footballer>();

        /*
        * Чтобы новые добавленные обекты выходили сверху
        * */
        for (int i = oldFootballers.size() - 1; i >= 0; i--) {
            newFootballers.add(oldFootballers.get(i));
        }

        request.setAttribute("footballlers", newFootballers);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
