package servlets.admin.get;

import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/admin/languages")
public class GetLanguageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("languages", DBManager.getLanguages());
        boolean success = Boolean.parseBoolean(request.getParameter("success"));
        String messageType = request.getParameter("type");

        if(messageType != null)
            request.setAttribute("message",DBManager.getMessage(success, Integer.parseInt(messageType),"Language", true));
        else
            request.setAttribute("message",DBManager.getMessage(success, 0,"Language", false));


        request.setAttribute("table", "languageTable");
        request.getRequestDispatcher("/views/admin/adminHome.jsp").forward(request, response);
    }
}
