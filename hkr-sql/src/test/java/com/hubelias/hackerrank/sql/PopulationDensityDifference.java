package com.hubelias.hackerrank.sql;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.okio.Buffer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Testcontainers
public class PopulationDensityDifference {

    @Container
    private MSSQLServerContainer msSqlServerContainer = new MSSQLServerContainer();

    @Test
    void test() throws SQLException, IOException {
        List<String> setupCommands = extractSQL("population-density-difference-setup.sql");
        List<String> queryCommands = extractSQL("population-density-difference.sql");


        try (Connection connection = DriverManager.getConnection(
                msSqlServerContainer.getJdbcUrl(),
                msSqlServerContainer.getUsername(),
                msSqlServerContainer.getPassword()
        )) {
            System.out.println("Performing setup:");
            setupCommands.stream().forEach(cmd -> {
                System.out.println(cmd);
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(cmd);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            System.out.println("\nExecuting queries:");
            queryCommands.stream().flatMap(query -> {
                System.out.println();
                System.out.println(query);
                System.out.println();
                return executeQuery(connection, query).stream();
            }).forEach(System.out::println);
        }
    }

    @NotNull
    private List<String> extractSQL(String resourceName) throws IOException {
        List<String> setupCommands = new LinkedList<>();

        try (
                InputStream res = getClass().getResourceAsStream(resourceName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(res))
        ) {
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                builder.append(line);
                if (line.endsWith(";")) {
                    setupCommands.add(builder.toString());
                    builder = new StringBuilder();
                }
                line = reader.readLine();
            }
        }

        return setupCommands;
    }

    @NotNull
    private List<String> executeQuery(Connection connection, String query) {
        LinkedList<String> results = new LinkedList<>();

        try(Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                int columnCount = resultSet.getMetaData().getColumnCount();

                while (resultSet.next()) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i <= columnCount; i++) {
                        sb.append(resultSet.getString(i));
                        sb.append(" ");
                    }
                    results.add(sb.toString());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}
