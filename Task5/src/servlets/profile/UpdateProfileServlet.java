package servlets.profile;

import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(value="/update_profile")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = new User();
            user.setEmail(request.getParameter("email"));
            user.setFull_name(request.getParameter("full_name"));
            user.setBirth_date(Date.valueOf(request.getParameter("birthdate")));

            User current_user = (User) request.getSession().getAttribute("current_user");


            user.setId(current_user.getId());

            if (DBManager.updateUserProfile(user)) {
                current_user = DBManager.getUserById(user.getId());

                request.getSession().setAttribute("current_user", current_user);
                response.sendRedirect("/profile?success=true&type=profile");
            } else {
                throw new Exception();
            }

        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/profile?success=false&type=profile");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
