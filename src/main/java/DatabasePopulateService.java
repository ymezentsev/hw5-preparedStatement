import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class DatabasePopulateService {
    public static final String POPULATE_TABLE_WORKER = "insert into worker (name, birthday, level, salary)" +
            "values (?, ?, ?, ?)";
    public static final String POPULATE_TABLE_CLIENT = "insert into client (name) values (?)";
    public static final String POPULATE_TABLE_PROJECT = "insert into project (client_id, start_date, finish_date)" +
            "values (?, ?, ?)";
    public static final String POPULATE_TABLE_PROJECT_WORKER = "insert into project_worker (project_id, worker_id)" +
            "values (?, ?)";

    public static void main(String[] args) {
        Connection connection = DatabaseMySql.getInstance().getConnection();

        int projectCount = 10;
        int workerCount = populateTableWorker(connection);
        int clientsCount = populateTableClient(connection);
        populateTableProject(connection, clientsCount, projectCount);
        populateTableProjectWorker(connection, projectCount, workerCount);

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int populateTableWorker(Connection connection) {
        List<String> workerNames = List.of("Denis Petrenko", "Andriy Nikolaenko", "Andriy Semenov",
                "Maksim Kravchenko", "Oleksandr Ivanenko", "Ivan Petrenko", "Dmitrii Dmitriev",
                "Fedor Zaytsev", "Alex Zagorulko", "Yurii Polunin");
        List<String> workerLevels = List.of("Trainee", "Junior", "Middle", "Senior");

        try (PreparedStatement preparedStatement = connection.prepareStatement(POPULATE_TABLE_WORKER)) {
            for (String workerName : workerNames) {
                LocalDate workerBirthday = LocalDate.now().minusYears(18)
                        .minusDays(new Random().nextInt(18000));

                preparedStatement.setString(1, workerName);
                preparedStatement.setDate(2, java.sql.Date.valueOf(workerBirthday));
                preparedStatement.setString(3, workerLevels.get(new Random().nextInt(4)));
                preparedStatement.setInt(4, new Random().nextInt(99900) + 100);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            return workerNames.size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int populateTableClient(Connection connection) {
        List<String> clients = List.of("Softserv", "Luxotf", "Goit", "Genesis", "Sigma");

        try (PreparedStatement preparedStatement = connection.prepareStatement(POPULATE_TABLE_CLIENT)) {
            for (String client : clients) {
                preparedStatement.setString(1, client);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            return clients.size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void populateTableProject(Connection connection, int clientsCount, int projectCount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(POPULATE_TABLE_PROJECT)) {
            for (int i = 0; i < projectCount; i++) {
                LocalDate projectStartDate = LocalDate.now().minusDays(new Random().nextInt(1500));
                LocalDate projectFinishDate = projectStartDate.plusMonths(new Random().nextInt(100));

                preparedStatement.setLong(1, new Random().nextInt(clientsCount) + 1);
                preparedStatement.setDate(2, java.sql.Date.valueOf(projectStartDate));
                preparedStatement.setDate(3, java.sql.Date.valueOf(projectFinishDate));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void populateTableProjectWorker(Connection connection, int projectCount, int workerCount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(POPULATE_TABLE_PROJECT_WORKER)) {
            for (int projectId = 1; projectId <= projectCount; projectId++) {
                int workerOnProjectCount = new Random().nextInt(5) + 1;
                Set<Integer> workerOnProjectSet = new HashSet<>();

                for (int i = 0; i < workerOnProjectCount; i++) {
                    int workerId;
                    do {
                        workerId = new Random().nextInt(workerCount) + 1;
                    } while (!workerOnProjectSet.add(workerId));

                    preparedStatement.setLong(1, projectId);
                    preparedStatement.setLong(2, workerId);
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
