package co.com.crud.requirement.operations.leveladequacy;

import co.com.crud.requirement.domain.exception.validation.RequirementAdecuationValidator;
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

    public double MaximunAccumulatedScore() {
        try {
            Connection connection = ConnectionToDB.getConnectionToDB();
            Statement statement = connection.createStatement();
            String queryMaximunScore = "SELECT * FROM consultar_notas_caracteristica();";
            ResultSet resultSet = statement.executeQuery(queryMaximunScore);

            double sum = 0.0;
            double sumA = 0.0;
            int count = 0;

            while (resultSet.next()) {
                double grade = resultSet.getDouble("nota");
                sum += grade;
                count++;
                sum = sum + sumA;
            }

            resultSet.close();
            statement.close();
            connection.close();

            return sum;

        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
            return 0.0;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Integer EvaluatedCharacterForRequirement() {
        try {
            Connection connection = ConnectionToDB.getConnectionToDB();
            Statement statement = connection.createStatement();
            String queryMaximunScore = "SELECT * FROM consultar_notas_caracteristica();";
            ResultSet resultSet = statement.executeQuery(queryMaximunScore);

            int count = 0;

            while (resultSet.next()) {
                count++;
            }

            resultSet.close();
            statement.close();
            connection.close();

            return count;

        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
            return 0;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double levelWeightedScore9Characters() {
        double maxSocre = MaximunAccumulatedScore();
        double result = ((maxSocre / 81) * 100);
        return result;
    }

    public double calculateWeightedAverage() {
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
            double weightedAverage = average / EvaluatedCharacterForRequirement();

            resultSet.close();
            statement.close();
            connection.close();

            return weightedAverage;

        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
            return 0.0;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String AllEvaluationCharactersResult() {
        double result = MaximunAccumulatedScore();
        if (result > 72) {
            return RequirementAdecuationValidator.Adecuation_Alto_Alto;
        } else if (result < 72 && result > 63) {
            return RequirementAdecuationValidator.Adecuation_Alto_Medio;
        } else if (result < 63 && result > 54) {
            return RequirementAdecuationValidator.Adecuation_Alto_Bajo;
        } else if (result < 54 && result > 45) {
            return RequirementAdecuationValidator.Adecuation_Medio_Alto;
        } else if (result < 45 && result > 36) {
            return RequirementAdecuationValidator.Adecuation_Medio_Medio;
        } else if (result < 36 && result > 27) {
            return RequirementAdecuationValidator.Adecuation_Medio_Bajo;
        } else if (result < 27 && result > 18) {
            return RequirementAdecuationValidator.Adecuation_Bajo_Bajo;
        } else if (result < 18 && result > 9) {
            return RequirementAdecuationValidator.Adecuation_Bajo_Medio;
        } else if (result < 9) {
            return RequirementAdecuationValidator.Adecuation_Bajo_Bajo;
        }

        return null;
    }
}