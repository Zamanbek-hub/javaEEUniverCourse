package servlets.post;

import db.classes.Post;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/post_delete")
public class PostDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long post_id = Long.parseLong(request.getParameter("post_id"));

            if(DBManager.deletePost(post_id)){
                response.sendRedirect("/?success=true&type=3");
            } else {
                throw new Exception();
            }


        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
