import dao.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    Connection connection;
    public final String FILE_FOR_FIND_MAX_SALARY_WORKER = "src/sql/find_max_salary_worker.sql";
    public final String FILE_FOR_FIND_MAX_PROJECTS_CLIENT = "src/sql/find_max_projects_client.sql";
    public final String FILE_FOR_FIND_LONGEST_PROJECT = "src/sql/find_longest_project.sql";
    public final String FILE_FOR_FIND_YOUNGEST_ELDEST_WORKERS = "src/sql/find_youngest_eldest_workers.sql";
    public final String FILE_FOR_FIND_PROJECT_PRICE = "src/sql/print_project_prices.sql";


    public DatabaseQueryService() {
        connection = DatabaseMySql.getInstance().getConnection();
    }

    public static void main(String[] args) {
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        databaseQueryService.findMaxSalaryWorker().forEach(System.out::println);
        databaseQueryService.findMaxProjectsClient().forEach(System.out::println);
        databaseQueryService.findLongestProject().forEach(System.out::println);
        databaseQueryService.findYoungestEldestWorkers().forEach(System.out::println);
        databaseQueryService.findProjectPrice().forEach(System.out::println);

        try {
            databaseQueryService.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> maxSalaryWorkerList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = Files.readString(Paths.get(FILE_FOR_FIND_MAX_SALARY_WORKER));

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                maxSalaryWorkerList.add(new MaxSalaryWorker(resultSet.getString(1),
                        resultSet.getInt(2)));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return maxSalaryWorkerList;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> maxProjectCountClientList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = Files.readString(Paths.get(FILE_FOR_FIND_MAX_PROJECTS_CLIENT));

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                maxProjectCountClientList.add(new MaxProjectCountClient(resultSet.getString(1),
                        resultSet.getInt(2)));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return maxProjectCountClientList;
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> longestProjectList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = Files.readString(Paths.get(FILE_FOR_FIND_LONGEST_PROJECT));

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                longestProjectList.add(new LongestProject(resultSet.getInt(1),
                        resultSet.getInt(2)));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return longestProjectList;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        List<YoungestEldestWorker> youngestEldestWorkerList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = Files.readString(Paths.get(FILE_FOR_FIND_YOUNGEST_ELDEST_WORKERS));

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                youngestEldestWorkerList.add(new YoungestEldestWorker(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return youngestEldestWorkerList;
    }

    public List<ProjectPrice> findProjectPrice() {
        List<ProjectPrice> projectPriceList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = Files.readString(Paths.get(FILE_FOR_FIND_PROJECT_PRICE));

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                projectPriceList.add(new ProjectPrice(resultSet.getInt(1),
                        resultSet.getInt(2)));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return projectPriceList;
    }
}
