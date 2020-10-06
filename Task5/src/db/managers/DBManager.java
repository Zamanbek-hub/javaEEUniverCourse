package db.managers;

import db.classes.Message;
import db.classes.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {
    private static Connection connection;
    private static final String url = "jdbc:postgresql://rogue.db.elephantsql.com:5432/bvgfhbsl";
    private static final String user = "bvgfhbsl";
    private static final String password = "TMtObo_nWsigJgVW5ivB7oVElXs5fzvU";
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


    // ************************** User **************************

    public static User getUser(String email, String password){
        String query = "select * from  users where email = ? and password = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2,password);

            ResultSet result = statement.executeQuery();

                result.next();

                User user = new User(
                        result.getLong("id"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("full_name"),
                        result.getDate("birth_date"),
                        result.getString("picture_url")
                );
                return user;


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static User getUserById(Long id){
        String query = "select * from  users where id = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);


            ResultSet result = statement.executeQuery();

            result.next();

            User user = new User(
                    result.getLong("id"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("full_name"),
                    result.getDate("birth_date"),
                    result.getString("picture_url")
            );
            return user;


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateUserProfile(User user){

        String query = "Update users set (email,full_name, birth_date) = (?,?, ?) where id = ?";
        System.out.println(user);
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getFull_name());
            statement.setDate(3,user.getBirth_date());
            statement.setLong(4,user.getId());
            statement.executeUpdate();

            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateUserPicture(User user){

        String query = "Update users set picture_url = ? where id = ?";
        System.out.println(user);
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,user.getPicture_url());
            statement.setLong(2,user.getId());
            statement.executeUpdate();

            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateUserPassword(User user){

        String query = "Update users set password= ? where id = ?";
        System.out.println(user);
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,user.getPassword());
            statement.setLong(2,user.getId());
            statement.executeUpdate();

            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean createUser(User user){
        String query = "INSERT INTO users(email, password, full_name, birth_date) VALUES (?,?, ?, ?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getFull_name());
            statement.setDate(4,user.getBirth_date());
            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }




    // ************************** Messages **************************

    public static Message getMessage(boolean success, int messageType, String object, boolean hidden){
        String message = "";

        if(hidden) {
            if (messageType == 1)
                message = "new " + object + " was successfully added";
            else if (messageType == 2)
                message = object + " was successfully updates";
            else if (messageType == 3)
                message = object + " was successfully deleted";

            System.out.println("Here 1 1");
            return new Message(success, message, true);
        }

        System.out.println("Here 2 2");
        return new Message(success, message, false);


    }


}
