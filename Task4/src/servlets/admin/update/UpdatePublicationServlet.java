package servlets.admin.update;

import db.classes.Language;
import db.classes.Publication;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/update_publication")
public class UpdatePublicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("update_id"));
            Publication publication = DBManager.getPublication(id);
            if(publication != null) {
                String name = request.getParameter("update_name");
                String description = request.getParameter("update_description");
                double rating  = Double.parseDouble(request.getParameter("update_rating"));

                if (DBManager.updatePublication(new Publication(publication.getId(),name, description, rating))) {
                    response.sendRedirect("/admin/publications?success=true&type=2");
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
