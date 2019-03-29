package com.hubelias.hackerrank.sql;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.*;

@Testcontainers
public class PopulationDensityDifference {

    @Container
    private MSSQLServerContainer msSqlServerContainer = new MSSQLServerContainer();

    @Test
    void test() throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                msSqlServerContainer.getJdbcUrl(),
                msSqlServerContainer.getUsername(),
                msSqlServerContainer.getPassword()
        )) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("CREATE")) {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(resultSet.getString(i));
                            System.out.print(" ");
                        }
                        System.out.println();
                    }
                }
            }
        }
    }
}
