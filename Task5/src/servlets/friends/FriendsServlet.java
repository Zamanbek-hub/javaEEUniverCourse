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

        // Search
        String search_user = request.getParameter("search_user");
        if(search_user != null)
            request.setAttribute("search_users", DBManager.getUsersByName(search_user, current_user.getId()));
        request.setAttribute("search_value", search_user);

        // Message
        boolean success = Boolean.parseBoolean(request.getParameter("success"));
        String messageType = request.getParameter("type");

        if(messageType != null) {
            if (messageType.equals("1"))
                request.setAttribute("alert", DBManager.getAlert(success, -1, "Request send successfully", false));
            else if (messageType.equals("2"))
                request.setAttribute("alert", DBManager.getAlert(success, -1, "New Friend added successfully", false));
            else if (messageType.equals("3"))
                request.setAttribute("alert", DBManager.getAlert(success, -1, "Request deleted successfully", false));
            else if(messageType.equals("0"))
                request.setAttribute("alert", DBManager.getAlert(success, 0, "An error occurred", false));
        }

        // Attributes
        request.setAttribute("friend_requests", DBManager.getFriendRequests(current_user.getId()));
        request.setAttribute("friends", DBManager.getFriends(current_user.getId()));
        request.setAttribute("latest_birthdays", DBManager.getUsersByBirthDate());
        request.setAttribute("online", true);
        request.getRequestDispatcher("/views/friends.jsp").forward(request, response);

    }
}
