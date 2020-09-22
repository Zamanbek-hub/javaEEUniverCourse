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

@WebServlet(value = "/get_club")
public class GetClubServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long club_id = Long.parseLong(request.getParameter("club_id"));
            System.out.println("club_id = " + club_id);
            Club club = DBManager.getClub(club_id);
            if(club != null) {
                String path = "<span style='color:blue; font-weight: bold;'> <a href='/'>Home Page</a> / <a href='/leagueClubs?id=" +club.getCity().getLeague().getId()
                                +"'>" + club.getCity().getLeague().getName() +"</a> / </span>" + club.getName();

                request.setAttribute("club", club);
                request.setAttribute("path", path);
                request.getRequestDispatcher("/views/getClub.jsp").forward(request, response);
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
