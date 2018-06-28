import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private static final String CONN_STRING =
            "jdbc:mysql://localhost/stock?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

    public static Connection getConnection() throws SQLException {

        //Class.forName("com.mysql.jdbc.Driver");

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

        } catch (SQLException e) {
            System.err.println(e);
        } finally {

        }
        return conn;

    }
}
