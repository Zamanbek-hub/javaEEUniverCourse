package servlets.admin.add;

import db.classes.Language;
import db.classes.Publication;
import db.managers.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add_publication")
public class AddPublicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("add_name");
            String description = request.getParameter("add_description");
            double rating = Double.parseDouble(request.getParameter("add_rating"));

            if(DBManager.addPublication(new Publication(name, description, rating))) {
                response.sendRedirect("/admin/publications?success=true&type=1");
            }else{
                throw new Exception();
            }

        } catch (Exception e){
            response.sendRedirect("/admin/publications?success=false&type=0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
