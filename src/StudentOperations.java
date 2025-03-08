import java.sql.*;
import java.util.Scanner;

public class StudentOperations {

    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // inserting student
    public static void insertStudent() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("insert into students" +
                     "(PRN, Name, Branch, Batch, CGPA)" +
                     "VALUES (?,?,?,?,?)")) {


    }
}