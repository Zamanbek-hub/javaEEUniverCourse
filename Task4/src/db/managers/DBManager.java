package db.managers;

import db.classes.Language;
import db.classes.Message;
import db.classes.News;
import db.classes.Publication;

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

    // ************************** Language **************************
    public static Language getLanguage(Long id){
        String query = "select * from  languages where id = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);

            ResultSet result = statement.executeQuery();
            result.next();

            Language language = new Language(result.getLong(1), result.getString(2), result.getString(3));
            return language;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addLeague(Language language){
        String query = "INSERT INTO languages(name,code) VALUES (?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,language.getName());
            statement.setString(2,language.getCode());
            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateLanguage(Language language){

        String query = "Update languages set (name,code) = (?,?) where id = ?";
        System.out.println(language);
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,language.getName());
            statement.setString(2,language.getCode());
            statement.setLong(3,language.getId());
            statement.executeUpdate();

            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteLanguage(Long id){
        String query = "Delete from languages where id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);
            statement.executeUpdate();

            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ArrayList<Language> getLanguages(){
        String query = "SELECT * FROM languages ORDER BY name ASC";
        ArrayList<Language> languages = new ArrayList<>();

        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    languages.add(new Language(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("code")
                    ));
                }

                System.out.println(languages);
            }
            else{
                System.out.println("No connection");
            }
            return languages;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    // ************************** Publications **************************
    public static Publication getPublication(Long id){
        String query = "select * from  publications where id = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);

            ResultSet result = statement.executeQuery();
            result.next();

            Publication publication = new Publication(result.getLong(1), result.getString(2), result.getString(3), result.getDouble(4));
            return publication;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addPublication(Publication publication){
        String query = "INSERT INTO publications(name,description, rating) VALUES (?,?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,publication.getName());
            statement.setString(2,publication.getDescription());
            statement.setDouble(3,publication.getRating());
            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updatePublication(Publication publication){

        String query = "Update publications set (name,description, rating) = (?,?, ?) where id = ?";
        System.out.println(publication);
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,publication.getName());
            statement.setString(2,publication.getDescription());
            statement.setDouble(3,publication.getRating());
            statement.setLong(4,publication.getId());
            statement.executeUpdate();

            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deletePublication(Long id){
        String query = "Delete from publications where id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);
            statement.executeUpdate();

            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public static ArrayList<Publication> getPublications(){
        String query = "SELECT * FROM publications ORDER BY name ASC";
        ArrayList<Publication> publications = new ArrayList<>();

        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    publications.add(new Publication(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getDouble("rating")
                    ));
                }

                System.out.println(publications);
            }
            else{
                System.out.println("No connection");
            }
            return publications;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    // ************************** News **************************

    public static News getNews(Long id){
        String query = "SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url,\n" +
                "       l.id as language_id, l.name as language_name, l.code as language_code,\n" +
                "       p.id as publication_id, p.name as publication_name, p.description as publication_description, p.rating as publication_rating\n" +
                "FROM news n\n" +
                "    INNER JOIN languages l on l.id = n.language_id\n" +
                "    INNER JOIN publications p on p.id = n.publications_id\n" +
                "WHERE n.id = ? ORDER BY n.id DESC";

        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);
                statement.setLong(1,id);

                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                News news = new News(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getString("picture_url"),
                        new Language(
                                resultSet.getLong("language_id"),
                                resultSet.getString("language_name"),
                                resultSet.getString("language_code")
                        ),
                        new Publication(
                                resultSet.getLong("publication_id"),
                                resultSet.getString("publication_name"),
                                resultSet.getString("publication_description"),
                                resultSet.getDouble("publication_rating")
                        )
                );

                return news;
            }
            else{
                System.out.println("No connection");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addNews(News news){
        String query = "insert into news(title, short_content, content, post_date, picture_url,  language_id, publications_id) values (?,?, ?, ?,?,?,?)";
        System.out.println(query);
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,news.getTitle());
            statement.setString(2,news.getShort_content());
            statement.setString(3,news.getContent());
            statement.setDate(4,news.getPost_date());
            statement.setString(5,news.getPicture_url());
            statement.setLong(6,news.getLanguage().getId());
            statement.setLong(7,news.getPublication().getId());
            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateNews(News news){

        String query = "Update news set (title, short_content, content, post_date, picture_url,  language_id, publications_id) = (?, ?, ?, ?, ?, ?, ?) where id = ?";
        System.out.println(news);
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,news.getTitle());
            statement.setString(2,news.getShort_content());
            statement.setString(3,news.getContent());
            statement.setDate(4,news.getPost_date());
            statement.setString(5,news.getPicture_url());
            statement.setLong(6,news.getLanguage().getId());
            statement.setLong(7,news.getPublication().getId());
            statement.setLong(8,news.getId());
            statement.executeUpdate();

            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteNews(Long id){
        String query = "Delete from news where id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);
            statement.executeUpdate();

            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ArrayList<News> getAllNews(){
        String query = "SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url,\n" +
                "       l.id as language_id, l.name as language_name, l.code as language_code,\n" +
                "       p.id as publication_id, p.name as publication_name, p.description as publication_description, p.rating as publication_rating\n" +
                "FROM news n\n" +
                "    INNER JOIN languages l on l.id = n.language_id\n" +
                "    INNER JOIN publications p on p.id = n.publications_id\n" +
                "ORDER BY n.id DESC";
        ArrayList<News> news = new ArrayList<>();

        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    news.add(new News(
                            resultSet.getLong("id"),
                            resultSet.getString("title"),
                            resultSet.getString("short_content"),
                            resultSet.getString("content"),
                            resultSet.getDate("post_date"),
                            resultSet.getString("picture_url"),
                            new Language(
                                    resultSet.getLong("language_id"),
                                    resultSet.getString("language_name"),
                                    resultSet.getString("language_code")
                            ),
                            new Publication(
                                    resultSet.getLong("publication_id"),
                                    resultSet.getString("publication_name"),
                                    resultSet.getString("publication_description"),
                                    resultSet.getDouble("publication_rating")
                            )

                    ));
                }

                System.out.println(news);
            }
            else{
                System.out.println("No connection");
            }
            return news;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<News> getAllNewsByLanguage(Long id){
        String query = "SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url,\n" +
                "       l.id as language_id, l.name as language_name, l.code as language_code,\n" +
                "       p.id as publication_id, p.name as publication_name, p.description as publication_description, p.rating as publication_rating\n" +
                "FROM news n\n" +
                "    INNER JOIN languages l on l.id = n.language_id\n" +
                "    INNER JOIN publications p on p.id = n.publications_id\n" +
                "WHERE l.id = ? ORDER BY n.id DESC";
        ArrayList<News> news = new ArrayList<>();

        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);
                statement.setLong(1,id);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    news.add(new News(
                            resultSet.getLong("id"),
                            resultSet.getString("title"),
                            resultSet.getString("short_content"),
                            resultSet.getString("content"),
                            resultSet.getDate("post_date"),
                            resultSet.getString("picture_url"),
                            new Language(
                                    resultSet.getLong("language_id"),
                                    resultSet.getString("language_name"),
                                    resultSet.getString("language_code")
                            ),
                            new Publication(
                                    resultSet.getLong("publication_id"),
                                    resultSet.getString("publication_name"),
                                    resultSet.getString("publication_description"),
                                    resultSet.getDouble("publication_rating")
                            )

                    ));
                }

                System.out.println(news);
            }
            else{
                System.out.println("No connection");
            }
            return news;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<News> getAllNewsByPublication(Long id){
        String query = "SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url,\n" +
                "       l.id as language_id, l.name as language_name, l.code as language_code,\n" +
                "       p.id as publication_id, p.name as publication_name, p.description as publication_description, p.rating as publication_rating\n" +
                "FROM news n\n" +
                "    INNER JOIN languages l on l.id = n.language_id\n" +
                "    INNER JOIN publications p on p.id = n.publications_id\n" +
                "WHERE p.id = ? ORDER BY n.id DESC";
        ArrayList<News> news = new ArrayList<>();

        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);
                statement.setLong(1,id);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    news.add(new News(
                            resultSet.getLong("id"),
                            resultSet.getString("title"),
                            resultSet.getString("short_content"),
                            resultSet.getString("content"),
                            resultSet.getDate("post_date"),
                            resultSet.getString("picture_url"),
                            new Language(
                                    resultSet.getLong("language_id"),
                                    resultSet.getString("language_name"),
                                    resultSet.getString("language_code")
                            ),
                            new Publication(
                                    resultSet.getLong("publication_id"),
                                    resultSet.getString("publication_name"),
                                    resultSet.getString("publication_description"),
                                    resultSet.getDouble("publication_rating")
                            )

                    ));
                }

                System.out.println(news);
            }
            else{
                System.out.println("No connection");
            }
            return news;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<News> getAllNewsByLanguageAdnPublication(Long id1, Long id2){
        String query = "SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url,\n" +
                "       l.id as language_id, l.name as language_name, l.code as language_code,\n" +
                "       p.id as publication_id, p.name as publication_name, p.description as publication_description, p.rating as publication_rating\n" +
                "FROM news n\n" +
                "    INNER JOIN languages l on l.id = n.language_id\n" +
                "    INNER JOIN publications p on p.id = n.publications_id\n" +
                "WHERE l.id = ? and p.id = ? ORDER BY n.id DESC";
        ArrayList<News> news = new ArrayList<>();

        try{
            if(connection != null) {
                statement = connection.prepareStatement(query);
                statement.setLong(1,id1);
                statement.setLong(2,id2);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    news.add(new News(
                            resultSet.getLong("id"),
                            resultSet.getString("title"),
                            resultSet.getString("short_content"),
                            resultSet.getString("content"),
                            resultSet.getDate("post_date"),
                            resultSet.getString("picture_url"),
                            new Language(
                                    resultSet.getLong("language_id"),
                                    resultSet.getString("language_name"),
                                    resultSet.getString("language_code")
                            ),
                            new Publication(
                                    resultSet.getLong("publication_id"),
                                    resultSet.getString("publication_name"),
                                    resultSet.getString("publication_description"),
                                    resultSet.getDouble("publication_rating")
                            )

                    ));
                }

                System.out.println(news);
            }
            else{
                System.out.println("No connection");
            }
            return news;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
