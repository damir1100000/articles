package dao;

import java.sql.*;

public class PostgreSQLConnection {
    private String username = "postgres";

    private String password = "password";

    private Connection connection;

    public PostgreSQLConnection () throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", username, password);
    }

    private Statement statement() throws SQLException {
        return connection.createStatement();
    }

    public int updateOperation (String query) throws SQLException {
        return this.statement().executeUpdate(query);
    }

    public ResultSet readOperation (String query) throws SQLException {
        return this.statement().executeQuery(query);
    }
}
