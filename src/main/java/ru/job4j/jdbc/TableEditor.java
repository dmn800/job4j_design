package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        this.connection = DriverManager.getConnection(url, login, password);
    }

    private void execute(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format("CREATE TABLE %s ();", tableName);
        execute(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("DROP TABLE %s;", tableName);
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("ALTER TABLE %s ADD %s %s;", tableName, columnName, type);
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
        execute(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        try (TableEditor tableEditor = new TableEditor(new Properties())) {
            tableEditor.initConnection();
            tableEditor.createTable("my_table");
            System.out.println(tableEditor.getTableScheme("my_table"));
            tableEditor.addColumn("my_table", "id", "serial primary key");
            System.out.println(tableEditor.getTableScheme("my_table"));
            tableEditor.renameColumn("my_table", "id", "count");
            System.out.println(tableEditor.getTableScheme("my_table"));
            tableEditor.dropColumn("my_table", "count");
            System.out.println(tableEditor.getTableScheme("my_table"));
            tableEditor.dropTable("my_table");
        }
    }
}
