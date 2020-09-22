package servlets.delete;

import db.classes.City;
import db.classes.League;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete_city")
public class DeleteCityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long city_id = Long.parseLong(request.getParameter("update_id"));
            Long league_id = Long.parseLong(request.getParameter("league_id"));
            City city = DBManager.getCity(city_id);
            League league = DBManager.getLeague(league_id);

            if(city != null && league != null) {
                if (DBManager.deleteCity(city.getId())) {
                    response.sendRedirect("/get_cities?league_id=" + league.getId() + "&success=true&type=3");
                } else {
                    response.sendRedirect("/get_cities?league_id=" + league.getId() + "&success=true&type=3");
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
