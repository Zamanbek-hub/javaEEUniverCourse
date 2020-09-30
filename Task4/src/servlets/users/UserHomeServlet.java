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

        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies){
            if(cookie.getName().equals("language_id")){
                news = DBManager.getAllNewsByLanguage(Long.parseLong(cookie.getValue()));
            }
        }


        if (publication_id != null){
            for (int i = 0; i < news.size(); i++){
                if(news.get(i).getPublication().getId() != Long.parseLong(publication_id)){
                    news.remove(news.get(i));
                }
            }
        }




        System.out.println("User here 1");
        request.setAttribute("languages", languages);
        request.setAttribute("publications", publications);
        request.setAttribute("news", news);
        System.out.println("User here 2");
        request.setAttribute("table", "languageTable");
        request.getRequestDispatcher("/views/user/userHOME.jsp").forward(request, response);
    }
}
