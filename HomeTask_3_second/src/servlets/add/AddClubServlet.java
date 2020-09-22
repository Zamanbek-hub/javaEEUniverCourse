package servlets.add;

import db.classes.City;
import db.classes.Club;
import db.classes.League;
import db.managers.DBManager;
import sun.security.pkcs11.Secmod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add_club")
public class AddClubServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("add_name");
            String description = request.getParameter("add_description");
            int founded_year = Integer.parseInt(request.getParameter("add_founded_year"));
            Long city_id = Long.parseLong(request.getParameter("city_id"));

            City city = DBManager.getCity(city_id);


            if(city != null) {
                if(DBManager.addClub(new Club(name, description, founded_year, city))) {
                    response.sendRedirect("/get_clubs?city_id=" + city.getId() + "&success=true&type=1");
                } else{
                    response.sendRedirect("/get_clubs?city_id=" + city.getId() + "&success=false&type=0");
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
