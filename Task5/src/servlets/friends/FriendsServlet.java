package servlets.friends;

import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/friends")
public class FriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");
        System.out.println("current_user = " + current_user);
        if(current_user != null) {
            // Message
            boolean success = Boolean.parseBoolean(request.getParameter("success"));
            String messageType = request.getParameter("type");

            if(messageType != null)
                request.setAttribute("alert", DBManager.getAlert(success, Integer.parseInt(messageType),"Friends", false));



            // Attributes
            request.setAttribute("friends", "d");
            request.setAttribute("latest_birthdays", DBManager.getUsersByBirthDate());
            request.setAttribute("online", true);
            request.getRequestDispatcher("/views/friends.jsp").forward(request,response);

        } else {
            response.sendRedirect("/login");
        }
    }
}
