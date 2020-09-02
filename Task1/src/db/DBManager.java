package db;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Footballer> footballers = new ArrayList<>();
    private static Long id = 4L;

    static {
        footballers.add(new Footballer(1l,"Cristiano","Ronaldo", 30, "Juventus", 320));
        footballers.add(new Footballer(2l,"Lionel","Messi", 35, "Barselona", 280));
        footballers.add(new Footballer(3l,"Zlatan","Ibragimovich", 25, "Milan", 150));
    }

    public static void addFootballer(Footballer footballer){
        footballer.setId(id);
        footballers.add(footballer);
        id++;
    }

    public static Footballer getFootballer(int id){
        return footballers.get(id);
    }

    public static ArrayList<Footballer> getFootballers(){
        return footballers;
    }

    public static void deleteFootBaller(int id){
        footballers.remove(id);
    }


}
