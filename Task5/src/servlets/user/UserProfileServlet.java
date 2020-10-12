package servlets.user;

import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(value="/user_profile")
public class UserProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User current_user = (User) request.getSession().getAttribute("current_user");
        System.out.println("current_user = " + id);

        Map<String, User> userData = DBManager.getUserByIdWithCheckToType(id, current_user.getId());
        Map.Entry<String,User> entry = userData.entrySet().iterator().next();

        if(entry.getValue() != null) {
            // Message
//            boolean success = Boolean.parseBoolean(request.getParameter("success"));
//            String messageType = request.getParameter("type");
//
//            if (messageType != null)
//                request.setAttribute("alert", DBManager.getAlert(success, Integer.parseInt(messageType), "Post", false));
//

            // Attributes
            request.setAttribute("posts", DBManager.getPostsByUser(entry.getValue().getId()));
            request.setAttribute("user", entry.getValue());
            request.setAttribute("case", entry.getKey());
            request.setAttribute("latest_birthdays", DBManager.getUsersByBirthDate());
            request.setAttribute("online", true);
            request.getRequestDispatcher("/views/userProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect("/friends?success=false&type=0");
        }
    }
}
