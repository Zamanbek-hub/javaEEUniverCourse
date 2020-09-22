package servlets.add;

import db.classes.City;
import db.classes.League;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/add_city")
public class AddCityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("add_name");
            Long league_id = Long.parseLong(request.getParameter("league_id"));

            League league = DBManager.getLeague(league_id);

            if(league != null) {
                if(DBManager.addCity(new City(name, league))) {
                    response.sendRedirect("/get_cities?league_id=" + league.getId() + "&success=true&type=1");
                } else{
                    response.sendRedirect("/get_cities?league_id=" + league.getId() + "&success=false&type=0");
                }
            }else{
                throw new Exception();
            }

        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/leagues?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
