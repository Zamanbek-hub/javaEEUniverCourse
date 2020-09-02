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

@WebServlet(value = "/list")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Footballer> footballers = DBManager.getFootballers();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<h1>Total: " + footballers.size() + "</h1>");

        for(Footballer f: footballers){
            out.print("<h2 style='color:darkred; border:1px solid grey; border-radius:3px;'><strong>" + f.getName()+ " " + f.getSurname() +"</strong></h2>");
            out.print("<h4> Club:" + f.getClub() + "</h4>");
            out.print("<h4> Salary:" + f.getSalary()+ " M$</h4>");
            out.print("<h4> Transfer Price:" + f.getTransferPrice()+ " M$</h4>");
        }
    }
}
