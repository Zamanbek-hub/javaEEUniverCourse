package servlets.admin.delete;

import db.classes.Language;
import db.classes.Publication;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete_publication")
public class DeletePublicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("delete_id"));
            Publication publication = DBManager.getPublication(id);
            if(publication != null) {
                if (DBManager.deletePublication(publication.getId())) {
                    response.sendRedirect("/admin/publications?success=true&type=3");
                } else {
                    throw new Exception();
                }
            }
            else {
                throw new Exception();
            }

        } catch (Exception e){
            response.sendRedirect("/admin/publications?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
