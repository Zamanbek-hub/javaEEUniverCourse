package servlets;

import classes.Student;
import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet(value = "/edit")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("edited_id"));
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String middle_name = request.getParameter("middle_name");
            Date birthdate = Date.valueOf(request.getParameter("birthdate"));
            String iin = request.getParameter("iin");
            boolean is_grant = Boolean.parseBoolean(request.getParameter("is_grant"));
            String specialty = request.getParameter("specialty");

            DBManager.editStudent(new Student(id, name, surname, middle_name, birthdate, iin, is_grant, specialty));

            request.setAttribute("success", true);
            request.setAttribute("type", "success");
            request.setAttribute("message", "Student was added successfully");

        } catch (Exception e){
            request.setAttribute("success", false);
            request.setAttribute("type", "danger");
            request.setAttribute("message", "Student wasn't added, something wrong <br>Try again");
        }

        request.getRequestDispatcher("/views/edit.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Long id = Long.parseLong(request.getParameter("id"));
            Student student = DBManager.getStudent(id);

            request.setAttribute("student", student);
            request.getRequestDispatcher("/views/edit.jsp").forward(request, response);

        }
        catch (Exception e){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<h1> No this student</h1>");

        }
    }
}
