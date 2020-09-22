package servlets.update;

import db.classes.League;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/update_league")
public class UpdateLeagueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long league_id = Long.parseLong(request.getParameter("update_id"));
            League league = DBManager.getLeague(league_id);
            if(league != null) {
                String name = request.getParameter("update_name");
                String description = request.getParameter("update_description");

                if (DBManager.updateLeague(new League(league.getId(),name, description))) {
                    response.sendRedirect("/leagues?success=true&type=2");
                } else {
                    throw new Exception();
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
