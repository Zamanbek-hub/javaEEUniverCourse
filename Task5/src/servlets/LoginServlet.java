package servlets;

import db.classes.Alert;
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
                Cookie token = new Cookie("token", DBManager.getCrypt(email,password));
                token.setMaxAge(24 * 60 * 60);
                response.addCookie(token);
            }

            response.sendRedirect("/");
        } else {
            response.sendRedirect("/login?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");
        System.out.println("current_user = " + current_user);


        String messageType = request.getParameter("type");

        System.out.println("I am in LoginServlet");
        if (messageType != null && messageType.equals("0"))
            request.setAttribute("alert", DBManager.getAlert(false, -1, "Invalid login or password", false));

        request.setAttribute("online", false);
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);

    }
}
