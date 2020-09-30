package servlets.users;

import db.classes.Language;
import db.classes.News;
import db.classes.Publication;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/")
public class UserHomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String publication_id = request.getParameter("publication_id");

        ArrayList<Publication> publications = DBManager.getPublications();
        ArrayList<Language> languages = DBManager.getLanguages();
        ArrayList<News> news = DBManager.getAllNewsByLanguage(languages.get(0).getId());
        Language language = languages.get(0);
        String style = "1";

        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies){
            if(cookie.getName().equals("language_id")){
                news = DBManager.getAllNewsByLanguage(Long.parseLong(cookie.getValue()));
                language = DBManager.getLanguage(Long.parseLong(cookie.getValue()));
            }
            if(cookie.getName().equals("style")){
                style = cookie.getValue();
            }
        }



        if (publication_id != null){
            System.out.println("Removed out");
            ArrayList<News> news1 = new ArrayList<>();
            for (int i = 0; i < news.size(); i++){
                if(news.get(i).getPublication().getId() == Long.parseLong(publication_id)){
                    news1.add(news.get(i));
                }
            }

            news = news1;
        }

        System.out.println("Style in back = " + style);





        request.setAttribute("languages", languages);
        request.setAttribute("language", language);
        request.setAttribute("publications", publications);
        request.setAttribute("news", news);
        request.setAttribute("style", style);
        request.setAttribute("table", "languageTable");
        request.getRequestDispatcher("/views/user/userHOME.jsp").forward(request, response);
    }
}
