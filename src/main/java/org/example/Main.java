package org.example;
import org.postgresql.util.PSQLException;

import java.sql.*;

public class Main {
    static String url = "jdbc:postgresql://localhost:5432/A3Q1";
    static String user = "postgres";
    static String pass = "postgres";

    public static void main(String[] args) {
        getAllStudents();

    }

    public static void getAllStudents() {
        try {
            Class.forName("org.example.Main");
            Connection con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                Statement stmt = con.createStatement(); // Execute SQL query

                String SQL = "SELECT * FROM students";
                ResultSet rs = stmt.executeQuery(SQL); // Process the result
                System.out.println("ID  |    First Name    |    Last Name    |             Email             | Enrollment Date | ");
                while(rs.next()) {
                    String id = String.valueOf(rs.getInt("student_id"));
                    String fname = rs.getString("first_name");
                    String lname = rs.getString("last_name");
                    String email = rs.getString("email");
                    String date = String.valueOf(rs.getDate("enrollment_date"));

                    System.out.printf("%-4s|", id);
                    System.out.printf(" %-17s|", fname);
                    System.out.printf(" %-16s|", lname);
                    System.out.printf(" %-30s|", email);
                    System.out.printf(" %-16s|\n", date);
                }

                // Close resources
                rs.close();
                stmt.close();
            } else {
                System.out.println("Failed to Connect");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(String fname, String lname, String email, String date) {
        try {
            Class.forName("org.example.Main");
            Connection con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                Statement stmt = con.createStatement(); // Execute SQL query

                String SQL = String.format("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('%s', '%s', '%s', '%s')", fname, lname, email, date);
                stmt.executeUpdate(SQL); // Process the result
                //no results

                // Close resources

                stmt.close();

            } else {
                System.out.println("Failed to Connect");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudentEmail(int id, String newEmail) {
        try {
            Class.forName("org.example.Main");
            Connection con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                Statement stmt = con.createStatement(); // Execute SQL query

                String SQL = String.format("UPDATE students SET email='%s' WHERE student_id=%d", newEmail, id);
                stmt.executeUpdate(SQL); // Process the result

                // Close resources
                stmt.close();

            } else {
                System.out.println("Failed to Connect");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(int id) {
        try {
            Class.forName("org.example.Main");
            Connection con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                Statement stmt = con.createStatement(); // Execute SQL query

                String SQL = String.format("DELETE FROM students WHERE student_id=%d", id);
                stmt.executeUpdate(SQL); // Process the result

                // Close resources
                stmt.close();

            } else {
                System.out.println("Failed to Connect");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}