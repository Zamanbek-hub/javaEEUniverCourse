package db.managers;

import db.classes.City;
import db.classes.Club;
import db.classes.League;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;
    private static final String url = "";
    private static final String user = "";
    private static final String password = "";
    private static PreparedStatement statement;

    static {
        System.out.println("Connected to the PostgreSQL server ready 0.");
        try{
            System.out.println("Connected to the PostgreSQL server ready 1.");
            Class.forName("org.postgresql.Driver");
            System.out.println("Connected to the PostgreSQL server ready 2.");
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connected to the PostgreSQL server successfully.");


        }
        catch (Exception e){
            System.out.println("Connected to the PostgreSQL server unsuccess.");
            e.printStackTrace();
        }
    }




    // Add league to db
    public static void addLeague(League league){
        String query = "insert into leagues(name,description) values (?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,league.getName());
            statement.setString(2,league.getDescription());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<League> getLeagues(){
        String query = "Select * from leagues";
        ArrayList<League> leagues = new ArrayList<>();

        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    leagues.add(new League(
                            resultSet.getLong("league_id"),
                            resultSet.getString("name"),
                            resultSet.getString("description")
                    ));
                }

                System.out.println(leagues);
            }
            else{
                System.out.println("No connection");
            }
            return leagues;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static League getLeague(Long id){
        String query = "select * from  leagues where league_id = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);

            ResultSet result = statement.executeQuery();
            result.next();

            League league = new League(result.getLong(1), result.getString(2), result.getString(3));
            return league;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    public static City getCity(Long id){
        String query = "SELECT c.city_id, c.name as city_name, l.league_id, l.name as league_name, l.description\n" +
                "FROM  cities c INNER JOIN leagues l on l.league_id = c.league_id\n" +
                "    where c.city_id = ?;";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);

            ResultSet result = statement.executeQuery();
            result.next();

            City city = new City(result.getLong("city_id"),
                                    result.getString("city_name"),
                                    new League(
                                            result.getLong("league_id"),
                                            result.getString("league_name"),
                                            result.getString("description")

                                    ));
            return city;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<City> getCitiesByLeague(Long id){
        String query = "SELECT c.city_id, c.name as city_name,\n" +
                "       l.league_id, l.name as league_name, l.description as league_description\n" +
                "FROM cities c  INNER JOIN leagues l on l.league_id = c.league_id\n" +
                "WHERE l.league_id = ?;";
        System.out.println(query);
        ArrayList<City> cities = new ArrayList<>();
        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);
                statement.setLong(1,id);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    cities.add(new City(
                                    resultSet.getLong("city_id"),
                                    resultSet.getString("city_name"),
                                    new League(
                                            resultSet.getLong("league_id"),
                                            resultSet.getString("league_name"),
                                            resultSet.getString("league_description")
                                    )
                            )
                    );
                }

                System.out.println(cities);
            }
            else{
                System.out.println("No connection");
            }
            return cities;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




    public static Club getClub(Long id){
        String query = "SELECT c.club_id, c.name as club_name, c.description as club_description, c.founded_year,\n" +
                "       c2.city_id, c2.name as city_name,\n" +
                "       l.league_id, l.name as league_name, l.description as league_description\n" +
                "FROM clubs c INNER JOIN cities c2 ON c2.city_id = c.city_id INNER JOIN leagues l on l.league_id = c2.league_id\n" +
                "WHERE c.club_id = ?;";

        System.out.println(query);
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);

            ResultSet result = statement.executeQuery();
            result.next();

            Club club = new Club(result.getLong("club_id"),
                                    result.getString("club_name"),
                                    result.getString("club_description"),
                                    result.getInt("founded_year"),
                                    new City(
                                            result.getLong("city_id"),
                                            result.getString("city_name"),
                                            new League(
                                                    result.getLong("league_id"),
                                                    result.getString("league_name"),
                                                    result.getString("league_description")
                                            )
                                    ));
            return club;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Club> getClubsByLeagueID(Long id){
        String query = "SELECT c.club_id, c.name as club_name, c.description as club_description, c.founded_year,\n" +
                "       c2.city_id, c2.name as city_name,\n" +
                "       l.league_id, l.name as league_name, l.description as league_description\n" +
                "FROM clubs c INNER JOIN cities c2 ON c2.city_id = c.city_id INNER JOIN leagues l on l.league_id = c2.league_id\n" +
                "WHERE l.league_id = ?;";
        System.out.println(query);
        ArrayList<Club> clubs = new ArrayList<>();
        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);
                statement.setLong(1,id);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    clubs.add(new Club(
                            resultSet.getLong("club_id"),
                            resultSet.getString("club_name"),
                            resultSet.getString("club_description"),
                            resultSet.getInt("founded_year"),
                            new City(
                                    resultSet.getLong("city_id"),
                                    resultSet.getString("city_name"),
                                    new League(
                                            resultSet.getLong("league_id"),
                                            resultSet.getString("league_name"),
                                            resultSet.getString("league_description")
                                    )
                            )
                    ));
                }

                System.out.println(clubs);
            }
            else{
                System.out.println("No connection");
            }
            return clubs;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Club> getClubsByCity(Long id){
        String query = "SELECT c.club_id, c.name as club_name, c.description as club_description, c.founded_year,\n" +
                "       c2.city_id, c2.name as city_name,\n" +
                "       l.league_id, l.name as   league_name, l.description as league_description\n" +
                "FROM clubs c INNER JOIN cities c2 ON c2.city_id = c.city_id INNER JOIN leagues l on l.league_id = c2.league_id\n" +
                "WHERE c.city_id = ?;";

        System.out.println(query);
        ArrayList<Club> clubs = new ArrayList<>();
        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);
                statement.setLong(1,id);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    clubs.add(new Club(
                            resultSet.getLong("club_id"),
                            resultSet.getString("club_name"),
                            resultSet.getString("club_description"),
                            resultSet.getInt("founded_year"),
                            new City(
                                    resultSet.getLong("city_id"),
                                    resultSet.getString("city_name"),
                                    new League(
                                            resultSet.getLong("league_id"),
                                            resultSet.getString("league_name"),
                                            resultSet.getString("league_description")
                                    )
                            )
                    ));
                }

                System.out.println(clubs);
            }
            else{
                System.out.println("No connection");
            }
            return clubs;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
