package servlets;

import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/leagues")
public class NavLeaguesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "<span style='color:blue; font-weight: bold;'><a href='/'>Home Page</a> / </span> Leagues";
        request.setAttribute("path", path);
        request.setAttribute("leagues", DBManager.getLeagues());
        request.getRequestDispatcher("/views/navLinksPages/navLeagues.jsp").forward(request, response);
    }
}
