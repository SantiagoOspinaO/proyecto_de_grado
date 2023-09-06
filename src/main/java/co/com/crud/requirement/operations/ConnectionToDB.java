package co.com.crud.requirement.operations;

import co.com.crud.requirement.domain.exception.ErrorConnectionToDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionToDB {

    @Value("${spring.datasource.url}")
    private static String url = "jdbc:postgresql://localhost:5432/requisito-bd";

    @Value("${spring.datasource.username}")
    private static String username = "postgres";

    @Value("${spring.datasource.password}")
    private static String password = "Admin";

    public static Connection getConnectionToDB() throws SQLException, IOException {
        try {
            return DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new ErrorConnectionToDB(e.getMessage());
        }
    }
}