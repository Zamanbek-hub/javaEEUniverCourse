package servlets;

import db.DBManager;
import db.Footballer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/get")
public class GetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("We are in get");

            int id = Integer.parseInt(request.getParameter("get_id"));
            ArrayList<Footballer> footballers = DBManager.getFootballers();

            /*
            * Так как нарушается совпадение Arraylist id и Footballer class id при удалении
            * */
            boolean notExist = true;
            for(int i = 0; i < footballers.size(); i++){
                if(id  == footballers.get(i).getId()){
                    Footballer footballer = DBManager.getFootballer(i);
                    request.setAttribute("footballler", footballer);
                    request.getRequestDispatcher("/get.jsp").forward(request, response);
                    notExist = false;
                    break;
                }
            }

            if(notExist){
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.print("<h1> Please write Exist ID </h1>");
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
