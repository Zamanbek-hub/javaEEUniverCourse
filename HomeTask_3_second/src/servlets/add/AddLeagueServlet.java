package servlets.add;

import db.classes.League;
import db.managers.DBManager;
import servlets.LeagueClubsServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.ExportException;

@WebServlet(value = "/add_league")
public class AddLeagueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("add_name");
            String description = request.getParameter("add_description");

            if(DBManager.addLeague(new League(name, description))) {
                response.sendRedirect("/leagues?success=true&type=1");
            }else{
                throw new Exception();
            }

        } catch (Exception e){
            response.sendRedirect("/leagues?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
