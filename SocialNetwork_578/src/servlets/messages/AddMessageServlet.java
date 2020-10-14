package servlets.messages;

import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/add_message")
public class AddMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long user_id = Long.parseLong(request.getParameter("user_id"));
            Long chat_id = Long.parseLong(request.getParameter("chat_id"));
            User current_user = (User)request.getSession().getAttribute("current_user");
            String message_text = request.getParameter("message_text");

            Long message_id = DBManager.addMessage(chat_id,current_user.getId(),user_id, message_text);
            if(message_id != -1) {
                DBManager.updateChat(chat_id, message_id);
                response.sendRedirect("/dialogue?friend="+user_id+"&id="+chat_id);
            }else {
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
