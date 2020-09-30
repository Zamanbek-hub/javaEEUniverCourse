package servlets.users;

import db.classes.News;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value="/news")
public class IndividNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));

            News news = DBManager.getNews(id);
            if(news != null) {
                request.setAttribute("news", news);
                request.setAttribute("languages", DBManager.getLanguages());
                request.setAttribute("publications", DBManager.getPublications());
                request.getRequestDispatcher("/views/user/newsIndividPage.jsp").forward(request, response);
            }
            else{
                throw new Exception();
            }

        }
        catch (Exception e){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<h1> No this League</h1>");

        }
    }
}
