package servlets.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/set_style")
public class StyleSetCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String style = request.getParameter("style");
        if (style == null) {
            style = "1";
        }

        Cookie lan = new Cookie("style", style);

        lan.setMaxAge(24 * 60 * 60);

        response.addCookie(lan);

        response.sendRedirect("/");
    }
}
