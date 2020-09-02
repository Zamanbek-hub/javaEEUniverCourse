package servlets.carServlets;

import db.managers.CarManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value="/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("We are in delete");
            String attr = request.getParameter("attr");
            String search = request.getParameter("search");
            int id = Integer.parseInt(request.getParameter("delete_id"));
            CarManager.deleteCar(id);

            try {
                System.out.println("delete try");
                Integer.parseInt(search);
                response.sendRedirect("/");
            }
            catch (Exception e) {
                System.out.println("delete catch");
                response.sendRedirect("/get?search=" + search + "&attr=" + attr);
            }
        }
        catch (Exception e){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<h1> Please write Exist ID </h1>");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
