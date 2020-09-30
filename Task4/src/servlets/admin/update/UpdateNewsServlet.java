package servlets.admin.update;

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

@WebServlet(name = "UpdateNewsServlet")
public class UpdateNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("update_id"));
            String title = request.getParameter("update_title");
            String short_content = request.getParameter("update_short_content");
            String content = request.getParameter("update_content");
            Long language_id = Long.parseLong(request.getParameter("update_language_id"));
            Long publication_id = Long.parseLong(request.getParameter("update_publication_id"));
            String picture_url = request.getParameter("update_picture_url");

            Language language = DBManager.getLanguage(language_id);
            Publication publication = DBManager.getPublication(publication_id);
            News news = DBManager.getNews(id);

            if(language != null && publication != null && news != null) {
                if(DBManager.updateNews(new News(title, short_content, content, new Date(System.currentTimeMillis()), picture_url, language, publication))) {
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
