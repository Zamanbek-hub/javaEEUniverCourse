package servlets.carServlets;

import db.classes.Car;
import db.managers.CarManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value="/get")
public class GetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("We are in get 2");

            String attr = request.getParameter("attr");
            String search = request.getParameter("search");
            Map<Integer, Car> carsWithIndex = new HashMap<Integer, Car>();
            ArrayList<Car> cars = new ArrayList<Car>();

            if (! attr.equals("none") && ! attr.equals("none2")) {
                System.out.println("We are in search 1");
                switch (attr) {
                    case "name":
                        System.out.println(attr);
                        cars = CarManager.getCarsByName(search);
                        break;
                    case "model":
                        System.out.println(attr);
                        cars = CarManager.getCarsByModel(search);
                        break;
                    case "year":
                        System.out.println(attr);
                        cars = CarManager.getCarsByYear(Integer.parseInt(search));
                        break;
                    case "price":
                        System.out.println(attr);
                        cars = CarManager.getCarsByPrice(Integer.parseInt(search));
                        break;
                }
            }else{
                System.out.println("We are in search 2");
                if(attr.equals("none"))
                    cars.add(CarManager.getCar(CarManager.size() - Integer.parseInt(search) - 1));
                else
                    cars.add(CarManager.getCar(CarManager.size() - Integer.parseInt(search)));
            }

            ArrayList<Car> allCars = CarManager.getAllCars();
            for(int i = 0; i < cars.size(); i++){
                carsWithIndex.put(allCars.indexOf(cars.get(i)), cars.get(i));
            }

            request.setAttribute("cars", carsWithIndex);
            request.setAttribute("attr", attr);
            request.setAttribute("search", search);
            request.getRequestDispatcher("/get.jsp").forward(request, response);


        }
        catch (Exception e){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<h1> Please write Exist ID </h1>");

        }
    }
}
