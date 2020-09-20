package db;

import classes.Student;
import sun.util.resources.ga.LocaleNames_ga;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;
    private static final String url = "jdbc:postgresql://localhost/postgres";
    private static final String user = "postgres";
    private static final String password = "cisco1HARD";
    private static PreparedStatement statement;

    static {
//        System.out.println("Connected to the PostgreSQL server ready 0.");
        try{
//            System.out.println("Connected to the PostgreSQL server ready 1.");
            Class.forName("org.postgresql.Driver");
//            System.out.println("Connected to the PostgreSQL server ready 2.");
            connection = DriverManager.getConnection(url,user,password);
//            System.out.println("Connected to the PostgreSQL server successfully.");


        }
        catch (Exception e){
//            System.out.println("Connected to the PostgreSQL server unsuccess.");
            e.printStackTrace();
        }
    }


    // Add student to db
    public static void addStudent(Student student){

        String query = "insert into students(name,surname,middle_name,birthdate,iin,is_grant,specialty) values (?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,student.getName());
            statement.setString(2,student.getSurname());
            statement.setString(3,student.getMiddleName());
            statement.setDate(4, student.getBirthdate());
            statement.setString(5,student.getIin());
            statement.setBoolean(6,student.isGrant());
            System.out.println("Specialty = " + student.getSpecialty());
            statement.setString(7,student.getSpecialty());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // edit student dates from db
    public static void editStudent(Student student){

        String query = "Update students set (name,surname,middle_name,birthdate,iin,is_grant,specialty) = (?,?,?,?,?,?,?) where id = ?";
        System.out.println(student);
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,student.getName());
            statement.setString(2,student.getSurname());
            statement.setString(3,student.getMiddleName());
            statement.setDate(4, student.getBirthdate());
            statement.setString(5,student.getIin());
            statement.setBoolean(6,student.isGrant());
            statement.setString(7,student.getSpecialty());
            statement.setLong(8,student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // get Student from db by id
    public static Student getStudent(Long id){
        String query = "select * from students where id = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);

            ResultSet result = statement.executeQuery();
            result.next();

            Student student = new Student(result.getLong(1), result.getString(2), result.getString(3), result.getString(4),
                                            result.getDate(5), result.getString(6), result.getBoolean(7), result.getString(8));
            return student;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //delete Student from db by id
    public static boolean deleteStudent(Long id){

        String query = "Delete from students where id = ? ";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);

            ResultSet result = statement.executeQuery();
//            result.next();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // size of students table
    public static int size(){
        String query = "select count(*) from students";
        return 0;
    }


    /*
    * generating Select query by taken parameters
    * for Strings using 'contains' ~*
    * */
    private static String generateQueryForFilter(String name, String surname, String iin,boolean is_grant){
        String query = "select * from students ";

        boolean itsFirstParameter = true;

        if(name != null) {
            if (!name.equals("")) {
                query += " where name ~* '" + name + "' and ";
                itsFirstParameter = false;
            }

        }
        if(surname != null) {
            if (!surname.equals("")) {
                if(itsFirstParameter){
                    query += " where ";
                }
                query += " surname ~* '" + surname + "' and ";
                itsFirstParameter = false;
            }

        }
        if(iin != null) {

            if (!iin.equals("")) {
                if(itsFirstParameter){
                    query += " where ";
                }
                query += " iin ~* '" + iin + "' and ";
                itsFirstParameter = false;
            }
        }


        if(itsFirstParameter){
            query += " where ";
        }

        query += " is_grant = " + is_grant;
        System.out.println("Query = " + query);
        return query;
    }

    // get Students Arraylist from db by taken parameters
    public static ArrayList<Student> findAllStudentsFilteredPagedInner(String name, String surname, String iin,boolean is_grant){
        ArrayList<Student> students = new ArrayList<>();
        try{
            statement = connection.prepareStatement(generateQueryForFilter(name, surname, iin, is_grant));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                students.add(new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("middle_name"),
                        resultSet.getDate("birthdate"),
                        resultSet.getString("iin"),
                        resultSet.getBoolean("is_grant"),
                        resultSet.getString("specialty")
                ));
            }
            return students;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    * just function
    * here was wrote some logic
    * but it was not accepted and function remained so
    * */
    public static ArrayList<Student> findAllStudentsFilteredPaged(String name, String surname, String iin,boolean is_grant){
        return findAllStudentsFilteredPagedInner(name,surname, iin, is_grant);
    }
}
