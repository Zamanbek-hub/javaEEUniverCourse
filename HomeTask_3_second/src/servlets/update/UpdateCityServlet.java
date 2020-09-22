package servlets.update;

import db.classes.City;
import db.classes.League;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/update_city")
public class UpdateCityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long city_id = Long.parseLong(request.getParameter("update_id"));
            Long league_id = Long.parseLong(request.getParameter("league_id"));
            City city = DBManager.getCity(city_id);
            League league = DBManager.getLeague(league_id);

            if(city != null && league != null) {
                String name = request.getParameter("update_name");

                if (DBManager.updateCity(new City(city.getId(),name, league))) {
                    response.sendRedirect("/get_cities?league_id=" + league.getId() + "&success=true&type=2");
                } else {
                    response.sendRedirect("/get_cities?league_id=" + league.getId() + "&success=true&type=2");
                }
            }
            else {
                throw new Exception();
            }

        } catch (Exception e){
            response.sendRedirect("/leagues?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
