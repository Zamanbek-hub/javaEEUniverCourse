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

        // Message
        boolean success = Boolean.parseBoolean(request.getParameter("success"));
        String messageType = request.getParameter("type");

        if (messageType != null)
            request.setAttribute("alert", DBManager.getAlert(success, Integer.parseInt(messageType), "Post", false));


        // Attributes
        request.setAttribute("posts", DBManager.getPostsByUser(current_user.getId()));
        request.setAttribute("latest_birthdays", DBManager.getUsersByBirthDate());
        request.setAttribute("online", true);
        request.getRequestDispatcher("/views/home.jsp").forward(request, response);


    }
}
