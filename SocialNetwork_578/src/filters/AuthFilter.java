package filters;

import db.classes.User;
import db.managers.DBManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final HttpSession session = req.getSession();
        User current_user = (User)session.getAttribute("current_user");
        System.out.println("I am Filter 1");

        if (current_user != null) {

            System.out.println("I am Filter 2");

            chain.doFilter(req, res);
        } else {

            boolean isRemember = false;
            Cookie[] cookies = req.getCookies();
            if(cookies != null){
                for(Cookie c : cookies){
                    if(c.getName().equals("token")){
                        isRemember = true;
                        User user = DBManager.getUserByPassword(c.getValue());
                        if(user != null) {
                            session.setAttribute("current_user", DBManager.getUserByPassword(c.getValue()));
                            chain.doFilter(req, res);
                        }
                    }
                }
            }

            if(!isRemember) {
                System.out.println("I am Filter 3");
                String path = req.getServletPath();
                System.out.println("Path = " + path);
                if (path.startsWith("/login") || path.startsWith("/register")) {
                    chain.doFilter(req, res);
                } else {
                    res.sendRedirect("/login");
                }
            }

        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
