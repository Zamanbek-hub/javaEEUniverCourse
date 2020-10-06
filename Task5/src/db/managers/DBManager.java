package db.managers;

import db.classes.Message;
import db.classes.Post;
import db.classes.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    public static User getUserByEmail(String email){
        String query = "select * from  users where email = ? ";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,email);

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


    public static ArrayList<User> getUsersByBirthDate(){
        String query = "SELECT * FROM  users ORDER BY birth_date ASC";
        ArrayList<User> users = new ArrayList<>();
        try {
            statement = connection.prepareStatement(query);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                users.add(new User(
                        resultset.getLong("id"),
                        resultset.getString("email"),
                        resultset.getString("password"),
                        resultset.getString("full_name"),
                        resultset.getDate("birth_date"),
                        resultset.getString("picture_url")
                ));

            }


            // Don't get more than 3
            int count = 0;
            ArrayList<User> users2 = new ArrayList<>();
            for(int i = users.size() - 1; i >= 0; i--){

                if(count == 3)
                   break;

                users2.add(users.get(i));
                count++;
            }

            // Bubble sort by birthdate month
            for(int i = 0; i < users2.size() - 1; i++){
                for(int j = 0; j < users2.size() - i - 1; j++) {
                    if (users2.get(j).getBirth_date().getMonth() > users2.get(j + 1).getBirth_date().getMonth()) {
                        User temp = users2.get(j);
                        users2.set(j, users2.get(j + 1));
                        users2.set(j + 1, temp);
                    }
                }
            }

            System.out.println("latest_birthdays = " + users2);
            return users2;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // ************************** Post **************************

    public static Post getPost(Long post_id){
        String query = "SELECT p.id as post_id, p.title, p.short_content, p.content, p.post_date,  \n" +
                "                       u.id as user_id,  u.email, u.password, u.full_name, u.birth_date, u.picture_url\n" +
                "                FROM posts p\n" +
                "                    INNER JOIN users u on p.author_id = u.id\n" +
                "                WHERE p.id = ? ORDER BY p.post_date DESC;";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,post_id);

            ResultSet result = statement.executeQuery();

            result.next();

            Post post = new Post(
                    result.getLong("post_id"),
                    result.getString("title"),
                    result.getString("short_content"),
                    result.getString("content"),
                    result.getDate("post_date"),
                    new User(
                            result.getLong("user_id"),
                            result.getString("email"),
                            result.getString("password"),
                            result.getString("full_name"),
                            result.getDate("birth_date"),
                            result.getString("picture_url")
                    )
            );
            return post;


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean createPost(Post post){
        String query = "INSERT INTO posts(title, short_content, content, author_id) VALUES (?,?, ?, ?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,post.getTitle());
            statement.setString(2,post.getShort_content());
            statement.setString(3,post.getContent());
            statement.setLong(4,post.getAuthor().getId());
            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updatePost(Post post){

        String query = "Update posts set (title, short_content, content) = (?, ?, ?) where id = ?";
        System.out.println(post);
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,post.getTitle());
            statement.setString(2,post.getShort_content());
            statement.setString(3,post.getContent());
            statement.setLong(4,post.getId());
            statement.executeUpdate();

            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deletePost(Long post_id){
        String query = "DELETE FROM posts WHERE id = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,post_id);
            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public static ArrayList<Post> getPostsByUser(Long user_id){
        String query = "SELECT p.id as post_id, p.title, p.short_content, p.content, p.post_date,  \n" +
                "                       u.id as user_id,  u.email, u.password, u.full_name, u.birth_date, u.picture_url\n" +
                "                FROM users u\n" +
                "                    INNER JOIN posts p on p.author_id = u.id\n" +
                "                WHERE u.id = ? ORDER BY p.post_date DESC;";

        ArrayList<Post> posts = new ArrayList<>();

        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);
                statement.setLong(1, user_id);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    posts.add(new Post(
                            resultSet.getLong("post_id"),
                            resultSet.getString("title"),
                            resultSet.getString("short_content"),
                            resultSet.getString("content"),
                            resultSet.getDate("post_date"),
                            new User(
                                    resultSet.getLong("user_id"),
                                    resultSet.getString("email"),
                                    resultSet.getString("password"),
                                    resultSet.getString("full_name"),
                                    resultSet.getDate("birth_date"),
                                    resultSet.getString("picture_url")
                            )
                    ));
                }

                System.out.println(posts);
            }
            else{
                System.out.println("No connection");
            }
            return posts;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    // ************************** Messages **************************

    public static Message getMessage(boolean success, int messageType, String object, boolean hidden){
        String message = "";

        if(!hidden) {
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
