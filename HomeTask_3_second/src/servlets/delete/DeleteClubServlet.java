package servlets.delete;

import db.classes.City;
import db.classes.Club;
import db.classes.League;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/delete_club")
public class DeleteClubServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long club_id = Long.parseLong(request.getParameter("update_id"));
            Long city_id = Long.parseLong(request.getParameter("city_id"));
            Club club = DBManager.getClub(club_id);

            if(club != null) {
                City city = DBManager.getCity(city_id);
                if (DBManager.deleteClub(club.getId())) {
                    response.sendRedirect("/get_clubs?city_id=" + city.getId() + "&success=true&type=3");
                } else {
                    response.sendRedirect("/get_clubs?city_id=" + city.getId() + "&success=true&type=3");
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
