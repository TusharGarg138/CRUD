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
        ){
            System.out.println("\n Student records");
            System.out.println("--------------------------------------");
            while (rs.next()) {
                System.out.println("PRN: " + rs.getInt("PRN")+
                        " Name: " + rs.getString("Name")+
                        " Branch: " + rs.getString("Branch")+
                        " Batch: " + rs.getString("Batch")+
                        " CGPA: " + rs.getFloat("cgpa"));
            }
            System.out.println("--------------------------------------");


        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateStudent() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter PRN of student to update: ");
            int prn = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Select the field to update:");
            System.out.println("1. Name\n2. Branch\n3. Batch\n4. CGPA");
            int choice = scanner.nextInt();
            scanner.nextLine();

            String field = "";
            String newValue = "";
            float newCgpa = 0;
            switch (choice) {
                case 1:
                    field = "Name";
                    System.out.print("Enter new Name: ");
                    newValue = scanner.nextLine();
                    break;
                case 2:
                    field = "Branch";
                    System.out.print("Enter new Branch: ");
                    newValue = scanner.nextLine();
                    break;
                case 3:
                    field = "Batch";
                    System.out.print("Enter new Batch: ");
                    newValue = scanner.nextLine();
                    break;
                case 4:
                    field = "CGPA";
                    System.out.print("Enter new CGPA: ");
                    newCgpa = scanner.nextFloat();
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }

            String sql = "UPDATE students SET " + field + " = ? WHERE PRN = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                if (choice == 4) {
                    stmt.setFloat(1, newCgpa);
                } else {
                    stmt.setString(1, newValue);
                }
                stmt.setInt(2, prn);
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Student record updated successfully!");
                } else {
                    System.out.println("No record found with the given PRN.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent() {

    }
}