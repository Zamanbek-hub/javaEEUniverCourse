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
            System.out.println("league_id = "+league);

            if (league != null) {
                String path = "<span style='color:blue; font-weight: bold;'><a href='/'>Home Page</a> / <a href='/leagues'>Leagues</a> / </span>" + league.getName();
                request.setAttribute("league", league);
                request.setAttribute("cities", DBManager.getCitiesByLeague(league.getId()));
                request.setAttribute("path", path);

                if (request.getParameter("success") != null) {
                    boolean success = Boolean.parseBoolean(request.getParameter("success"));
                    String message = "";

                    if (success) {
                        int messageType = Integer.parseInt(request.getParameter("type"));

                        if (messageType == 1)
                            message = "new City was successfully added";
                        else if (messageType == 2)
                            message = "City was successfully updates";
                        else if (messageType == 3)
                            message = "City was successfully deleted";
                        else
                            throw new Exception();
                    }

                    request.setAttribute("success", success);
                    request.setAttribute("message", message);

                }
            } else {
                throw new Exception();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        request.getRequestDispatcher("/views/navLinksPages/navCities.jsp").forward(request, response);
    }
}
