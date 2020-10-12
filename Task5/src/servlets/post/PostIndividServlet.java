package servlets.post;

import db.classes.Post;
import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/post_details")
public class PostIndividServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User current_user = (User) request.getSession().getAttribute("current_user");


            Long post_id = Long.parseLong(request.getParameter("post_id"));
            Post post = DBManager.getPost(post_id);
            if (post != null) {
                request.setAttribute("online", true);
                request.setAttribute("post", post);
                request.setAttribute("latest_birthdays", DBManager.getUsersByBirthDate());
                request.getRequestDispatcher("/views/postIndivid.jsp").forward(request, response);
            } else {
                throw new Exception();
            }

        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/login");
        }

    }
}
