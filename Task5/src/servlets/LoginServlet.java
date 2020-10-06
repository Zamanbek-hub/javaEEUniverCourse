package servlets;

import db.classes.Message;
import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean remember_me = Boolean.parseBoolean(request.getParameter("remember_me"));
        User user = DBManager.getUser(email, password);


        if(user != null) {
            request.getSession().setAttribute("current_user", user);
            if(remember_me){
                Cookie token = new Cookie("token", email);
                Cookie remember = new Cookie("remember", "yes");

                token.setMaxAge(24 * 60 * 60);
                remember.setMaxAge(24 * 60 * 60);

                response.addCookie(token);
                response.addCookie(remember);

            }

            response.sendRedirect("/");
        } else {
            response.sendRedirect("/login?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");
        System.out.println("current_user = " + current_user);
        if(current_user == null) {
            String messageType = request.getParameter("type");

            if (messageType != null && messageType.equals("0"))
                request.setAttribute("message", new Message(false, "Invalid login or password", false));

            request.setAttribute("online", false);
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }
}
