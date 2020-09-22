package servlets;

import db.classes.Club;
import db.classes.League;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value="/leagueClubs")
public class LeagueClubsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            League league = DBManager.getLeague(id);
            if(league != null) {
                ArrayList<Club> clubsByLeague = DBManager.getClubsByLeagueID(league.getId());

                if(clubsByLeague != null) {
                    String path = "<span style='color:blue; font-weight: bold;'><a href='/'>Home Page</a> / </span>" + league.getName();
                    request.setAttribute("league", league);
                    request.setAttribute("clubsByLeague", clubsByLeague);
                    request.setAttribute("path", path);
                    request.getRequestDispatcher("/views/leagueClubs.jsp").forward(request, response);
                }
                else{
                    throw new Exception();
                }


            }
            else{
                throw new Exception();
            }

        }
        catch (Exception e){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<h1> No this League</h1>");

        }
    }
}
