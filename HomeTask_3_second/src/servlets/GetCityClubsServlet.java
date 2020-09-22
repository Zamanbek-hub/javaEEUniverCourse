package servlets;

import db.classes.City;
import db.classes.League;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/get_clubs")
public class GetCityClubsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("city_id"));
            City city = DBManager.getCity(id);

            if (city != null) {
                String path = "<span style='color:blue; font-weight: bold;'><a href='/'>Home Page</a> / <a href='/leagues'>Leagues</a> / " +
                        "<a href='/get_cities?league_id="+ city.getLeague().getId()+"'>" + city.getLeague().getName()+ "</a> / </span>" + city.getName();
                request.setAttribute("clubs", DBManager.getClubsByCity(city.getId()));
                request.setAttribute("path", path);
                request.getRequestDispatcher("/views/navLinksPages/navClubs.jsp").forward(request, response);
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
