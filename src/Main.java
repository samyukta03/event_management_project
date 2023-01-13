import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("JDBC Connection Testing ");
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE", "system", "samyu");
            if (conn != null)
                System.out.println("Connected to the database!");
            else
                System.out.println("Failed to make connection!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
