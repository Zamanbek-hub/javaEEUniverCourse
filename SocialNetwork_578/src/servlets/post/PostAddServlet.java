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
import java.sql.Date;

@WebServlet(value = "/add_post")
public class PostAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String title = request.getParameter("add_title");
            String short_content = request.getParameter("add_short_content");
            String content = request.getParameter("add_content");
            Long user_id = Long.parseLong(request.getParameter("user_id"));



            if(DBManager.createPost(new Post(title, short_content, content, DBManager.getUserById(user_id)))){
                response.sendRedirect("/?success=true&type=1");
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
