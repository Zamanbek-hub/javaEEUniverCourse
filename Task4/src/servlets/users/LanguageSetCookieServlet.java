package servlets.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/set_language_cookie")
public class LanguageSetCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language_id = request.getParameter("language_id");
        if (language_id != null) {
            Cookie lan = new Cookie("language_id", language_id);

            lan.setMaxAge(24 * 60 * 60);

            response.addCookie(lan);
        }

        response.sendRedirect("/");

    }
}
