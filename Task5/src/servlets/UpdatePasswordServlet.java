package servlets;

import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(value = "/update_password")
public class UpdatePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String old_password = request.getParameter("old_password");
            String new_password = request.getParameter("new_password");
            String re_new_password = request.getParameter("re_new_password");


            User current_user = (User) request.getSession().getAttribute("current_user");

            if (current_user != null) {
                if (new_password.equals(re_new_password) && old_password.equals(current_user.getPassword())) {
                    User user = new User();
                    user.setPassword(re_new_password);
                    user.setId(current_user.getId());

                    System.out.println("user_id =" + current_user.getId());
                    if (DBManager.updateUserPassword(user)) {
                        current_user = DBManager.getUserById(user.getId());

                        System.out.println("current_user in update profile =" + current_user);

                        request.getSession().setAttribute("current_user", current_user);
                        response.sendRedirect("/profile?success=true&type=password");
                    } else {
                        throw new Exception();
                    }
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/profile?success=false&type=password");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
