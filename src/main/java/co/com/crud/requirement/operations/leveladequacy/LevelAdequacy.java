package co.com.crud.requirement.operations.leveladequacy;

import co.com.crud.requirement.operations.ConnectionToDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LevelAdequacy {

    public double calculateLevelAdequacy() {
        try {
            Connection connection = ConnectionToDB.getConnectionToDB();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM consultar_notas_caracteristica();";
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