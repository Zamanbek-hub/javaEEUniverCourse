package servlets.admin.update;

import db.classes.Language;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/update_language")
public class UpdateLanguageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("update_id"));
            Language language = DBManager.getLanguage(id);
            if(language != null) {
                String name = request.getParameter("update_name");
                String code = request.getParameter("update_code");

                if (DBManager.updateLanguage(new Language(language.getId(),name, code))) {
                    response.sendRedirect("/admin/languages?success=true&type=2");
                } else {
                    throw new Exception();
                }
            }
            else {
                throw new Exception();
            }

        } catch (Exception e){
            response.sendRedirect("/admin/language?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
