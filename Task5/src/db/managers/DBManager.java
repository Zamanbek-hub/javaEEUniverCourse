package db.managers;

import db.classes.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBManager {
    private static Connection connection;
    private static final String url = "jdbc:postgresql://rogue.db.elephantsql.com:5432/bvgfhbsl";
    private static final String user = "bvgfhbsl";
    private static final String password = "v6fx99p123sROhhH3dTSgeoNgTYHteXZ";
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


    // ************************** Password **************************
    public static String getCrypt(String email, String password){
        String query = "SELECT password FROM users\n" +
                "WHERE email = ? and password = crypt(?,password);";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet result = statement.executeQuery();
            result.next();

            return result.getString("password");


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




    // ************************** User **************************

    public static User getUser(String email, String password){
        String query = "select * from  users where email = ? and password = crypt(?, password)";
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

    public static User getUserByPassword(String password){
        String query = "select * from  users where password = ? ";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,password);

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

        String query = "Update users set password = crypt(?, gen_salt('md5')) where id = ?";
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
        String query = "INSERT INTO users(email, password, full_name, birth_date) VALUES (?,crypt(?, gen_salt('md5')), ?, ?)";
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

    public static  Map<User, FriendRequest> getUsersByName(String name, Long user_id){
        String query = "SELECT CASE WHEN fr.id is null THEN -1 ELSE fr.id END as friend_id,\n" +
                "       u.id as sender_id, u.email, u.full_name, u.birth_date, u.picture_url\n" +
                "FROM  users u  LEFT JOIN friends_requests fr on u.id = fr.user_id\n" +
                "WHERE (full_name ~* ? or fr.request_sender_id = ?) and u.id != ?\n" +
                "ORDER BY full_name ASC;";
        Map<User, FriendRequest> users = new HashMap<User, FriendRequest>();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setLong(2,user_id);
            statement.setLong(3,user_id);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                users.put(

                        new User(
                                resultset.getLong("sender_id"),
                                resultset.getString("email"),
                                resultset.getString("full_name"),
                                resultset.getDate("birth_date"),
                                resultset.getString("picture_url")
                        ),
                        new FriendRequest(resultset.getLong("friend_id"))

                );


            }

            System.out.println("filtered users = " + users);
            return users;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<User> getUsersByBirthDate(){
        String query = "SELECT id, email, full_name, birth_date, picture_url FROM  users ORDER BY birth_date ASC";
        ArrayList<User> users = new ArrayList<>();
        try {
            statement = connection.prepareStatement(query);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                users.add(new User(
                        resultset.getLong("id"),
                        resultset.getString("email"),
                        resultset.getString("full_name"),
                        resultset.getDate("birth_date"),
                        resultset.getString("picture_url")
                ));

            }


            // Don't get more than 3
            int count = 0;
            ArrayList<User> users2 = new ArrayList<>();
            for(int i = users.size() - 1; i >= 0; i--){

                if(count == 4)
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
                "                       u.id as user_id,  u.email, u.full_name, u.birth_date, u.picture_url\n" +
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
                "                       u.id as user_id,  u.email,  u.full_name, u.birth_date, u.picture_url\n" +
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


    // ************************** Friends **************************
    public static ArrayList<User> getFriendRequests(Long user_id){
        String query = "SELECT u.id, u.email, u.full_name, u.birth_date, u.picture_url\n" +
                "    FROM users u INNER JOIN friends_requests fr on u.id = fr.request_sender_id\n" +
                "WHERE fr.user_id = ?;";

        ArrayList<User> friendRequests = new ArrayList<>();

        try {

            statement = connection.prepareStatement(query);
            statement.setLong(1, user_id);

            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                friendRequests.add(
                        new User(
                                resultset.getLong("id"),
                                resultset.getString("email"),
                                resultset.getString("full_name"),
                                resultset.getDate("birth_date"),
                                resultset.getString("picture_url")
                        ));
            }

            System.out.println("friend_requests in DBManager = " + friendRequests);

            return friendRequests;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static  Map<String, User> getUserByIdWithCheckToType(Long friend_id, Long user_id){
        String query = "SELECT id, email, full_name, birth_date, picture_url,\n" +
                "       CASE\n" +
                "            WHEN id in (Select friend_id from friends where user_id = ?) THEN 'friend'\n" +
                "            WHEN id in (Select user_id from friends_requests where request_sender_id = ?) THEN 'request_send'\n" +
                "            WHEN id in (Select request_sender_id from friends_requests where user_id = ?) THEN 'request_get'\n" +
                "            ELSE 'nothing'\n" +
                "        END\n" +
                "FROM users where id = ?";

        Map<String, User> userData = new HashMap<String, User>();
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,user_id);
            statement.setLong(2,user_id);
            statement.setLong(3,user_id);
            statement.setLong(4,friend_id);


            ResultSet result = statement.executeQuery();

            result.next();

            userData.put(
                        result.getString("case"),
                        new User(
                        result.getLong("id"),
                        result.getString("email"),
                        result.getString("full_name"),
                        result.getDate("birth_date"),
                        result.getString("picture_url")
                )
            );

            return userData;


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    public static boolean removeFriend(Long friend_id, Long user_id){
        String query = "DELETE FROM friends where friend_id = ? and user_id = ?";
        try {

            statement = connection.prepareStatement(query);
            statement.setLong(1, friend_id);
            statement.setLong(2, user_id);

            statement.executeUpdate();

            return true;


        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }



    // ************************** Friend Request **************************
    public static ArrayList<User> getFriends(Long user_id){
        String query = "SELECT u.id, u.email, u.full_name, u.birth_date, u.picture_url \n" +
                "                    FROM users u INNER JOIN friends fr on u.id = fr.friend_id\n" +
                "                WHERE fr.user_id = ?;";

        ArrayList<User> friends = new ArrayList<>();

        try {

            statement = connection.prepareStatement(query);
            statement.setLong(1, user_id);

            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                friends.add(
                        new User(
                                resultset.getLong("id"),
                                resultset.getString("email"),
                                resultset.getString("full_name"),
                                resultset.getDate("birth_date"),
                                resultset.getString("picture_url")
                        ));
            }

            System.out.println("friend_requests in DBManager = " + friends);

            return friends;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean sendFriendRequest(Long friend_id, Long user_id){
        String query = "INSERT INTO friends_requests(user_id, request_sender_id) values(?, ?);";

        try {

            statement = connection.prepareStatement(query);
            statement.setLong(1, friend_id);
            statement.setLong(2, user_id);
            statement.executeUpdate();

            return true;


        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean confirmFriendRequest(Long friend_id, Long user_id){
        String query = "INSERT INTO friends(user_id, friend_id) values(DELETE FROM);";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, user_id);
            statement.setLong(2, friend_id);
            statement.executeUpdate();

            return true;


        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public static boolean deleteFriendRequest(Long friend_id, Long user_id){
        String query = "DELETE FROM friends_requests where user_id = ? and request_sender_id = ?";
        try {

            statement = connection.prepareStatement(query);
            statement.setLong(1, friend_id);
            statement.setLong(2, user_id);
            System.out.println(statement.toString());
            statement.executeUpdate();

            return true;


        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    // ************************** Messages **************************

    public static Alert getAlert(boolean success, int messageType, String message, boolean hidden){

        if(!hidden && messageType != -1) {

            if (messageType == 1)
                message = "new " + message + " was successfully added";
            else if (messageType == 2)
                message = message + " was successfully updates";
            else if (messageType == 3)
                message = message + " was successfully deleted";


            System.out.println("Here 1 1");
            return new Alert(success, message, false);
        }

        System.out.println("Here 2 2");

        if(messageType == 0)
            message = "An error occurred";

        return new Alert(success, message, false);


    }


}
