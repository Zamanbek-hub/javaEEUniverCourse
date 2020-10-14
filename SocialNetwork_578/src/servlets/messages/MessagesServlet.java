package servlets.messages;

import db.classes.Chat;
import db.classes.Message;
import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(value = "/dialogue")
public class MessagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long chat_id = Long.parseLong(request.getParameter("id"));
        Long friend_id = Long.parseLong(request.getParameter("friend"));
//        User current_user = (User) request.getSession().getAttribute("current_user");

        Chat chat = DBManager.getChat(chat_id);
        DBManager.updateChatRead(chat_id);


        request.setAttribute("user", DBManager.getUserById(friend_id));
        request.setAttribute("case", "friend");
        request.setAttribute("chat", chat);
        request.setAttribute("messages", DBManager.getMessages(chat_id));
        request.setAttribute("latest_birthdays", DBManager.getUsersByBirthDate());
        request.setAttribute("online", true);
        request.getRequestDispatcher("/views/dialogue.jsp").forward(request, response);

    }
}
