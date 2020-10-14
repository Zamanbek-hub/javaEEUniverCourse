package servlets.friends;

import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/remove_friend")
public class RemoveFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search_user = request.getParameter("search_user");
        try {
            Long friend_id = Long.parseLong(request.getParameter("friend_id"));
            User current_user = (User)request.getSession().getAttribute("current_user");

            if(DBManager.removeFriend(friend_id, current_user.getId())){
                response.sendRedirect("/friends?success=true&type=3&search_user="+search_user);
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/friends?success=false&type=0&search_user="+search_user);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
