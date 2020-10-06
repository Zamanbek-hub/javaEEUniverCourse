package servlets;

import db.classes.Message;
import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/profile")
public class UserProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");
        System.out.println("current_user in profile = " + current_user);
        if(current_user != null) {
            String messageType = request.getParameter("type");
            boolean success = Boolean.parseBoolean(request.getParameter("success"));

            if(messageType != null) {
                if (success) {
                    request.setAttribute("message", new Message(true, messageType + " successfully updated", false));
                } else {
                    request.setAttribute("message", new Message(false, "An error occurred on update " + messageType, false));
                }
            }

            request.setAttribute("latest_birthdays", DBManager.getUsersByBirthDate());
            request.setAttribute("online", true);
            request.getRequestDispatcher("/views/userSettings.jsp").forward(request,response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
