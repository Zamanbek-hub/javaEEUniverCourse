package servlets.admin.add;

import db.classes.Language;
import db.classes.News;
import db.classes.Publication;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(value="/add_news")
public class AddNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String title = request.getParameter("add_title");
            String short_content = request.getParameter("add_short_content");
            String content = request.getParameter("add_content");
            Long language_id = Long.parseLong(request.getParameter("add_language"));
            Long publication_id = Long.parseLong(request.getParameter("add_publication"));
            String picture_url = request.getParameter("add_picture_url");

            Language language = DBManager.getLanguage(language_id);
            Publication publication = DBManager.getPublication(publication_id);

            if(language != null && publication != null) {
                if(DBManager.addNews(new News(title, short_content, content, new Date(System.currentTimeMillis()), picture_url, language, publication))) {
                    response.sendRedirect("/admin/news?success=true&type=1");
                } else{
                    response.sendRedirect("/admin/news?success=false&type=0");
                }
            }else{
                throw new Exception();
            }

        } catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/leagues?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
