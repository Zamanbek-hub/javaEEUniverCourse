package servlets;

import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/leagues")
public class NavLeaguesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "<span style='color:blue; font-weight: bold;'><a href='/'>Home Page</a> / </span> Leagues";
        request.setAttribute("path", path);
        request.setAttribute("leagues", DBManager.getLeagues());


        if (request.getParameter("success") != null) {
            boolean success = Boolean.parseBoolean(request.getParameter("success"));
            String message = "";

            try {
                if (success) {
                    int messageType = Integer.parseInt(request.getParameter("type"));

                    if (messageType == 1)
                        message = "new League was successfully added";
                    else if (messageType == 2)
                        message = "League was successfully updates";
                    else if (messageType == 3)
                        message = "League was successfully deleted";
                    else
                        throw new Exception();
                }

                request.setAttribute("success", success);
                request.setAttribute("message", message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        request.getRequestDispatcher("/views/navLinksPages/navLeagues.jsp").forward(request, response);
    }
}
