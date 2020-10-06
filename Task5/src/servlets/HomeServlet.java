package servlets;

import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");
        System.out.println("current_user = " + current_user);
        if(current_user != null) {
            // Message
            boolean success = Boolean.parseBoolean(request.getParameter("success"));
            String messageType = request.getParameter("type");

            if(messageType != null)
                request.setAttribute("message",DBManager.getMessage(success, Integer.parseInt(messageType),"Post", false));
            else
                request.setAttribute("message",DBManager.getMessage(success, 0,"Post", false));


            // Attributes
            request.setAttribute("posts", DBManager.getPostsByUser(current_user.getId()));
            request.setAttribute("latest_birthdays", DBManager.getUsersByBirthDate());
            request.setAttribute("online", true);
            request.getRequestDispatcher("/views/home.jsp").forward(request,response);

        } else {

            Cookie [] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie c : cookies){
                    if(c.getName().equals("token")){
                        request.getSession().setAttribute("current_user", DBManager.getUserByEmail(c.getValue()));
                    }
                }
            }

            response.sendRedirect("/login");
        }

    }
}
