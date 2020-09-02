package db.managers;

import db.classes.Car;

import java.util.ArrayList;

public class CarManager {
    private static ArrayList<Car> cars = new ArrayList<>();
    private static Long id = 9L;

    static {
        cars.add(new Car(1L, "s-class", "Mercedes", 2003, 7000000));
        cars.add(new Car(1L, "e-class", "Mercedes", 2008, 4000000));
        cars.add(new Car(1L, "a7", "Audi", 2001, 9000000));
        cars.add(new Car(1L, "rs7", "Audi", 2009, 33000000));
        cars.add(new Car(1L, "skyline", "Nissan", 2001, 6000000));
        cars.add(new Car(1L, "qashqai", "Nissan", 2011, 4000000));
        cars.add(new Car(1L, "model s", "Tesla", 2017, 25000000));
        cars.add(new Car(1L, "model x", "Tesla", 2018, 35000000));
    }

    public static void  addCar(Car car){
        car.setId(id);
        cars.add(car);
        id++;
    }

    public static Car getCar(int id){
        return cars.get(id);
    }

    public static ArrayList<Car> getAllCars(){
        return cars;
    }

    public static void deleteCar(int id){
        cars.remove(id);
    }

    public static ArrayList<Car> getCarsByName(String name){
        return findByAttribute("name", name, -1);
    }

    public static ArrayList<Car> getCarsByModel(String model){
        return findByAttribute("model", model, -1);
    }

    public static ArrayList<Car> getCarsByYear(int yearFrom){
        return findByAttribute("year", "%&#*$", yearFrom);
    }

    public static ArrayList<Car>  getCarsByPrice(int priceTo){
        return findByAttribute("price", "%&#*$", priceTo);
    }

    public static int size(){
        return cars.size();
    }

    private static ArrayList<Car> findByAttribute(String attr, String word, int num){
        ArrayList<Car> findCars = new ArrayList<>();
        System.out.println("FindBy " + attr + " " + num);
        for(int i = 0; i < cars.size(); i++){

            if(cars.get(i).getName().toLowerCase().contains(word.toLowerCase()) && attr.equals("name")){
                findCars.add(cars.get(i));
//                System.out.println(cars.get(i).getName() + ": " + word);
            }
            else if(cars.get(i).getModel().toLowerCase().contains(word.toLowerCase()) && attr.equals("model")) {
                findCars.add(cars.get(i));
//                System.out.println(cars.get(i).getName() + ": " + word);
            }
            else if(cars.get(i).getYear() >= num && attr.equals("year")){
                findCars.add(cars.get(i));
//                System.out.println(cars.get(i).getYear() + ": " + num);
            }
            else if(cars.get(i).getPrice() <= num && attr.equals("price")){
                findCars.add(cars.get(i));
//                System.out.println(cars.get(i).getPrice() + ": " + num);
            }
            System.out.println("cars = " +findCars.size());
            for(Car car:findCars){
                System.out.println(car);
            }
        }

        return findCars;
    }


}
