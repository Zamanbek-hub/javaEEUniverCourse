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

@WebServlet(value="/add")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String club = request.getParameter("club");
        int salary = Integer.parseInt(request.getParameter("salary"));
        int transfer_price = Integer.parseInt(request.getParameter("transfer_price"));

        Footballer footballer = new Footballer();
        footballer.setName(name);
        footballer.setSurname(surname);
        footballer.setClub(club);
        footballer.setSalary(salary);
        footballer.setTransferPrice(transfer_price);

        DBManager.addFootballer(footballer);

        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Footballer> footballers = DBManager.getFootballers();
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }
}
