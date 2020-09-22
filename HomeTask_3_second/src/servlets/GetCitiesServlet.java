package servlets;

import db.classes.League;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/get_cities")
public class GetCitiesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("league_id"));
            League league = DBManager.getLeague(id);

            if (league != null) {
                String path = "<span style='color:blue; font-weight: bold;'><a href='/'>Home Page</a> / <a href='/leagues'>Leagues</a> / </span>" + league.getName();
                request.setAttribute("cities", DBManager.getCitiesByLeague(league.getId()));
                request.setAttribute("path", path);
                request.getRequestDispatcher("/views/navLinksPages/navCities.jsp").forward(request, response);
            } else {
                throw new Exception();
            }
        }
        catch (Exception e){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<h1> No this Clubs</h1>");

        }
    }
}
