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

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter PRN ");
            int prn = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter name: ");
            String name = scanner.nextLine();

            System.out.println("Enter Branch: ");
            String branch = scanner.nextLine();

            System.out.println("Enter Batch: ");
            String batch = scanner.nextLine();

            System.out.println("Enter CGPA: ");
            float cgpa = scanner.nextFloat();

            stmt.setInt(1, prn);
            stmt.setString(2, name);
            stmt.setString(3, branch);
            stmt.setString(4, batch);
            stmt.setFloat(5, cgpa);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student record inserted DONE!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void viewStudent(){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")
        )
    }
}