package db.managers;

import db.classes.Bus;
import db.classes.Car;
import db.classes.Truck;
import interfaces.Transports;



import java.util.ArrayList;

public class TransportManager {
    private static ArrayList<Transports> transports = new ArrayList<>();
    private static Long id = 10L;

    static {
        transports.add(new Bus(1L, "eCitaro 2020", "Bus Mercedes",35, 20000000));
        transports.add(new Car(2L, "Campy 70", "Toyota", "Sedan", 200, 2.5, 13000000, 2017));
        transports.add(new Truck(3L, "NEO", "KAMAZ-5490", 5500,  85000, 700));

        transports.add(new Bus(4L, "Rosa", "Mitsubishi",33, 45000));
        transports.add(new Car(5L, "Skyline GTR R34", "Nissan", "Sedan", 330, 2.6, 30000, 2001));
        transports.add(new Truck(6L, "FH", "Volvo", 20000,  350000, 2000));

        transports.add(new Bus(7L, "Minibus", "Tesla",10, 25000));
        transports.add(new Car(8L, "A7", "Audi", "liftback", 250, 3, 22000, 2018));
        transports.add(new Truck(9L, "NEO", "Tesla", 36000,  27000, 3500));

//        tickets.add(new Ticket(1L, "Zhezkazgan", "Taldykorgan", 28000, 15));
    }

    public static void addTransport(Transports transport) {
        transport.setId(id);
        transports.add(transport);
        id++;
    }


    //this method returns an object of ticket by id
    public static Transports getTransports(Long id) {
        for(Transports transport: transports){
            if(transport.getId().equals(id)){
                return transport;
            }
        }
        return null; // null cannot be returned
    }

    public static ArrayList<Transports> getAllTransports() {
        return transports;
    }

    public static void deleteTransposrt(Long id) {
        for(int i = 0; i < transports.size(); i++){
            if(transports.get(i).getId().equals(id)){
                transports.remove(transports.get(i));
                break;
            }
        }
    }

    //this method returns a list of tickets from index with size
    public static ArrayList<Transports> getTransportsFromRange(int page) {
        page = page - 1;
        // convert to fromIndex and toIndex to measure with id
        int fromIndex = page * 3;
        int toIndex = fromIndex + 3;
        if(toIndex > transports.size())
            toIndex = transports.size();


        // so that new tickets are first on the list
        ArrayList<Transports> reverseList = new ArrayList<>();
        for(int i = transports.size()-1; i >= 0 ; i--){
            reverseList.add(transports.get(i));
        }

        return new ArrayList<Transports>(reverseList.subList(fromIndex,toIndex));
    }
}
