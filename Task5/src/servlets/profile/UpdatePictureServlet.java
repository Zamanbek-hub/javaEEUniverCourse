package servlets.profile;

import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(value = "/update_picture_url")
public class UpdatePictureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = new User();
            user.setPicture_url(request.getParameter("picture_url"));

            User current_user = (User) request.getSession().getAttribute("current_user");


            user.setId(current_user.getId());

            System.out.println("user_id =" + current_user.getId());
            if (DBManager.updateUserPicture(user)) {

                request.getSession().setAttribute("current_user", DBManager.getUserById(user.getId()));
                response.sendRedirect("/profile?success=true&type=picture");
            } else {
                throw new Exception();
            }

        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/profile?success=false&type=picture");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
