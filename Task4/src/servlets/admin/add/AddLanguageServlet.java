package servlets.admin.add;

import db.classes.Language;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/add_language")
public class AddLanguageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("add_name");
            String code = request.getParameter("add_code");

            if(DBManager.addLeague(new Language(name, code))) {
                response.sendRedirect("/admin/languages?success=true&type=1");
            }else{
                throw new Exception();
            }

        } catch (Exception e){
            response.sendRedirect("/admin/language?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
