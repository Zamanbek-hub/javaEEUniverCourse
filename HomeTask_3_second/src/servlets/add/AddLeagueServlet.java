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

@WebServlet(value = "/add_league")
public class AddLeagueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            Long id = Long.parseLong(request.getParameter(""));
            String name = request.getParameter("add_name");
            String description = request.getParameter("add_description");

            DBManager.addLeague(new League(name, description));

            request.setAttribute("success", true);
            request.setAttribute("type", "success");
            request.setAttribute("message", "Student was added successfully");
            String path = "<span style='color:blue; font-weight: bold;'><a href='/'>Home Page</a> / </span> Leagues";
            request.setAttribute("path", path);
            request.setAttribute("leagues", DBManager.getLeagues());
            request.getRequestDispatcher("/views/navLinksPages/navLeagues.jsp").forward(request, response);

        } catch (Exception e){
            request.setAttribute("success", false);
            request.setAttribute("type", "danger");
            request.setAttribute("message", "Student wasn't added, something wrong <br>Try again");
        }

        request.getRequestDispatcher("/views/add.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
