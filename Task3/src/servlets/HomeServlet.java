package servlets;

import classes.Student;
import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;



@WebServlet(value = "/")
public class HomeServlet extends HttpServlet {

    public static String currentURL(String name, String surname, String iin, boolean is_grant){
        /*
         * When we use GET. In JavaEE Browser URL shows all params that was send in request
         * It's cool, but we use pagination in 'home' page
         * And everytime when we take GET Request with params
         * link of pagination have to will change according to request params
         * */

        String currentURL = "";
        if (name == null){
            currentURL += "?name=";
        }
        else {
            currentURL += "?name=" + name;
        }

        if (surname == null){
            currentURL += "&surname=";
        }
        else {
            currentURL += "&surname=" + surname;
        }

        if (iin == null){
            currentURL += "&iin=";
        }
        else {
            currentURL += "&iin=" + iin;
        }

        currentURL += "&is_grant=" + is_grant;

        return currentURL;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String iin = request.getParameter("iin");
        boolean is_grant = Boolean.parseBoolean(request.getParameter("is_grant"));

        String currentURL = currentURL(name,surname,iin,is_grant);
        ArrayList<Student> students = null;
        int count = 10; // count of objects for One page of Pagination
        int page = 1;

        try{
            page = Integer.parseInt(request.getParameter("page"));
            students = DBManager.findAllStudentsFilteredPaged(name, surname, iin, is_grant);
            request.setAttribute("active_page", page);
        }
        catch (Exception e){
            page = 1;
            students = DBManager.findAllStudentsFilteredPaged(name, surname, iin, is_grant);
            request.setAttribute("active_page", 1);
        }

        int pages = students.size() / count;

        if ((students.size() - (count * pages)) % count > 0) {
            pages += 1;
        }

        // logic to calculate pagination pages
        page = page - 1;
        // convert to fromIndex and toIndex to measure with id
        int fromIndex = page * count;
        int toIndex = fromIndex + count;
        if(toIndex > students.size())
            toIndex = students.size();


        request.setAttribute("pages", pages);
        request.setAttribute("total", new ArrayList<Student>(students.subList(fromIndex,toIndex)).size());
        request.setAttribute("students", new ArrayList<Student>(students.subList(fromIndex,toIndex)));
        request.setAttribute("currentURL", currentURL);
        request.setAttribute("is_grant", is_grant);
        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
    }
}
