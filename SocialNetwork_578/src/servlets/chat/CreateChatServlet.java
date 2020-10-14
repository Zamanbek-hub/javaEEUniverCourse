package servlets.chat;

import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/create_chat")
public class CreateChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long friend_id = Long.parseLong(request.getParameter("opponent_user_id"));
            User current_user = (User)request.getSession().getAttribute("current_user");
            Long chat_id = DBManager.createChat(friend_id, current_user.getId());
            if(chat_id != -1){
                response.sendRedirect("/dialogue?friend="+friend_id+"&id="+chat_id);
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/friends?success=false&type=0&search_user=");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
