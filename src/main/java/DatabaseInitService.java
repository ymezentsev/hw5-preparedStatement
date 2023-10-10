import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static final String FILE_FOR_INIT_DB = "src/sql/init_db.sql";

    public static void main(String[] args) {
        Connection connection = DatabaseMySql.getInstance().getConnection();

        try (Statement statement = connection.createStatement()) {
            String[] queries = Files.readString(Paths.get(FILE_FOR_INIT_DB)).split(";");
            for (String query : queries) {
                statement.addBatch(query);
            }
            statement.executeBatch();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
