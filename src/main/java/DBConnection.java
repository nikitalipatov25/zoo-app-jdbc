import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection conn = null;

    public void close() {
        if (conn != null) {
            try {
                System.out.println("Closing database connection to zooDB");
                conn.close();
            } catch (SQLException e) {
                System.out.println("Unable to close connection");
                e.printStackTrace();
            }
            conn = null;
        }
    }

    public Connection getConnection() throws SQLException {
        if (conn == null) {
            System.out.println("Opening connection to zooDB");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/zoo", "postgres", "root");
        }
        return conn;
    }
}
