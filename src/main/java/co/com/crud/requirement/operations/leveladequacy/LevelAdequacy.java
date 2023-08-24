package co.com.crud.requirement.operations.leveladequacy;

import co.com.crud.requirement.domain.exception.ErrorConnectionToDB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class LevelAdequacy {

    public Connection getConnectionToDB() throws SQLException, IOException {
        try {
            Properties properties = new Properties();
            FileInputStream stream = new FileInputStream("src/main/resources/application-devso.properties");
            properties.load(stream);
            stream.close();
            String url = properties.getProperty("spring.datasource.url");
            String username = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");
            return DriverManager.getConnection(url, username, password);

        } catch (SQLException | FileNotFoundException e) {
            throw new ErrorConnectionToDB(e.getMessage());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double calculateLevelAdequacy() {
        try {
            Connection connection = getConnectionToDB();
            Statement statement = connection.createStatement();
            String query = "select caracteristica.nota from public.caracteristica";
            ResultSet resultSet = statement.executeQuery(query);

            double sum = 0.0;
            int count = 0;

            while (resultSet.next()) {
                double grade = resultSet.getDouble("nota");
                sum += grade;
                count++;
            }

            double average = sum / count;

            resultSet.close();
            statement.close();
            connection.close();

            return average;

        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
            return 0.0;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
