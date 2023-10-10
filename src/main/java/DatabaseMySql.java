import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseMySql {
    private static final DatabaseMySql INSTANCE = new DatabaseMySql();
    private final Connection connection;

    private DatabaseMySql() {
        ResourceBundle resource = ResourceBundle.getBundle("database_mysql");
        String dbName = resource.getString("db.name");
        String dbUrl = resource.getString("db.url");
        String dbUser = resource.getString("db.user");
        String dbPassword = resource.getString("db.password");

        try {
            connection = DriverManager.getConnection(dbUrl + dbName, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseMySql getInstance(){
        return INSTANCE;
    }

    public Connection getConnection(){
        return this.connection;
    }
}
