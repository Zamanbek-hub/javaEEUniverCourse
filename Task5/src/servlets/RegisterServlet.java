package servlets;

import db.classes.Alert;
import db.classes.User;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(value="/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String re_password = request.getParameter("re_password");
            String full_name = request.getParameter("full_name");
            Date birthdate = Date.valueOf(request.getParameter("birthdate"));


            if (password.equals(re_password)) {
                if(DBManager.createUser(new User(email, password, full_name, birthdate))){
                    response.sendRedirect("/login");
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }

        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/register?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("current_user");
        System.out.println("current_user = " + current_user);

        String messageType = request.getParameter("type");

        if (messageType != null && messageType.equals("0")) {
            System.out.println("we are here");
            request.setAttribute("alert", DBManager.getAlert(false, -1, "Please input right your data", false));
        }

        request.setAttribute("online", false);
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);

    }
}
