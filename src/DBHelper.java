

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DBHelper {
    private static String DB_CONN_STRING = "";
    private static String CURRENT_DB_NAME = "";

    public DBHelper(String dbFile, String dbName) {
        DB_CONN_STRING = "jdbc:postgresql://localhost:15432/"+dbName;
        CURRENT_DB_NAME = dbName;
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:15432/db2022", User.getUsername(), User.getDbPassword());
            //Statement stmt = conn.createStatement();
            //int result = stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS" + CURRENT_DB_NAME);
           // if (result > 0) {
           //     System.out.println("Successfully created database: " + CURRENT_DB_NAME);
            //} else {
            //    System.out.println("Something went wrong...");
           // }
            conn.close();
            if (dbFile != null) {
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:15432/"+CURRENT_DB_NAME, User.getUsername(), User.getDbPassword());
                ScriptRunner runner = new ScriptRunner(conn, false, true);
                runner.runScript(new BufferedReader(new FileReader(dbFile)));
                conn.close();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean DBExists(String dbName) {
        boolean found = false;
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:15432/"+dbName, User.getUsername(), User.getDbPassword())) {
            ResultSet rs = conn.getMetaData().getCatalogs();
            while (rs.next()) {
                if (rs.getString(1).equals(dbName)) {
                    System.out.println(rs.getString(1));
                    found = true;
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return found;
    }

    public void SeedDatabase(String fileName) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db2022", User.getUsername(), User.getDbPassword())) {
            ScriptRunner runner = new ScriptRunner(conn, false, true);
            runner.runScript(new BufferedReader(new FileReader(fileName)));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean GetAdmin(Credentials credentials) {
        String sql = "select * from admin where username = ? and password = ?";
        try (Connection conn = DriverManager.getConnection(DB_CONN_STRING, User.getUsername(), User.getDbPassword());
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, User.getUsername());
            stmt.setString(2, User.getAdminPassword());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User.setName(rs.getString("name"));
                User.setUsername(rs.getString("username"));
                User.setDbPassword(rs.getString("password"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Instructor> SearchInstructor(String name) {
        ArrayList<Instructor> instructors = new ArrayList<>();
        String sql = "select ID, name from instructor where name like ?";
        try (Connection conn = DriverManager.getConnection(DB_CONN_STRING, User.getUsername(), User.getDbPassword());
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                instructors.add(new Instructor(rs.getInt("ID"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructors;
    }

    public static ArrayList<Instructor> SearchInstructorID(String id) {
        ArrayList<Instructor> instructors = new ArrayList<>();
        String sql = "select ID, name from instructor where ID = ?";
        try (Connection conn = DriverManager.getConnection(DB_CONN_STRING, User.getUsername(), User.getDbPassword());
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                instructors.add(new Instructor(rs.getInt("ID"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructors;
    }

    public static ArrayList<InstructorInfo> SearchInstructorInfo(String id) {
        ArrayList<InstructorInfo> instructors = new ArrayList<>();
        //String sql = "select ID, name from instructor where ID = ?";

        String sql = "select ID, name, dept_name,course_id,title,sec_id,semester, year from instructor natural join teaches natural join course where ID = ? order by dept_name, course_id, year, semester";
        try (Connection conn = DriverManager.getConnection(DB_CONN_STRING, User.getUsername(), User.getDbPassword());
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                instructors.add(new InstructorInfo(rs.getInt("ID"), rs.getString("name"), rs.getString("dept_name"), rs.getString("course_id"),rs.getString("title"),rs.getString("sec_id"),rs.getString("semester"),rs.getString("year")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructors;
    }
}
