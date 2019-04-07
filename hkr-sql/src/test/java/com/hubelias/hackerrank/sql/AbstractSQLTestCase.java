package com.hubelias.hackerrank.sql;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.google.common.base.CaseFormat;
import org.testcontainers.shaded.okio.Buffer;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.hubelias.hackerrank.sql.Utils.camelCaseToKebabCase;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public abstract class AbstractSQLTestCase {

    @Container
    private MSSQLServerContainer msSqlServerContainer = new MSSQLServerContainer();

    @Test
    void testQuery() throws SQLException, IOException {
        final String classNamePrefix = camelCaseToKebabCase(getClass().getSimpleName());
        final List<String> setupCommands = extractSQL(classNamePrefix + ".setup.sql");
        final List<String> queryCommands = extractSQL(classNamePrefix + ".sql");

        try (Connection connection = DriverManager.getConnection(
                msSqlServerContainer.getJdbcUrl(),
                msSqlServerContainer.getUsername(),
                msSqlServerContainer.getPassword()
        )) {
            System.out.println("Performing setup:");
            setupCommands.forEach(cmd -> {
                System.out.println(cmd);
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(cmd);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            System.out.println("\nExecuting queries:");
            Stream<String> queryResults = queryCommands.stream()
                    .peek(query -> System.out.println("\n" + query + "\n"))
                    .flatMap(query -> executeQuery(connection, query).stream())
                    .peek(System.out::println);

            assertThat(queryResults).containsExactlyElementsOf(getExpectedResults());
        }
    }

    @NotNull
    private List<String> extractSQL(String resourceName) throws IOException {
        List<String> setupCommands = new LinkedList<>();

        InputStream res = getClass().getResourceAsStream(resourceName);
        if(res == null) {
            return Collections.emptyList();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(res))) {
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                builder.append(line.trim());
                builder.append(" ");
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

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                int columnCount = resultSet.getMetaData().getColumnCount();

                while (resultSet.next()) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < columnCount; i++) {
                        sb.append(resultSet.getString(i));
                        sb.append(" ");
                    }
                    sb.append(resultSet.getString(columnCount));
                    results.add(sb.toString());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    protected abstract List<String> getExpectedResults();
}
