package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private PostgreSQLConnection postgreSQLConnection;

    public UserDao (PostgreSQLConnection postgreSQLConnection) {
        this.postgreSQLConnection = postgreSQLConnection;
    }

    public List<User> allUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        ResultSet usersResult = this.postgreSQLConnection.readOperation("select * from users");

        while (usersResult.next()) {
            User u = new User(usersResult.getLong("userid"),
                              usersResult.getString("email"),
                              usersResult.getString("password"));
            users.add(u);
        }
        return users;
    }

    public User getUserById (long userid) throws SQLException {
        ResultSet userResult = this.postgreSQLConnection.readOperation("select * from users where userid=" + userid + ";");

        User user = null;

        while (userResult.next()) {
            user = new User(userResult.getLong("userid"), userResult.getString("email"));
        }

        return user;
    }

    public User getUserByEmailAndPassword (String email, String password) throws SQLException {
        ResultSet userResult = this.postgreSQLConnection.readOperation("select * from users where users.email = '" + email + "' and " + "users.password = '" + password + "' ;");

        User user = null;

        while (userResult.next()) {
            user = new User(userResult.getLong("userid"), userResult.getString("email"), userResult.getString("password"));
        }

        return user;
    }

    public User createUser (String email, String password) throws SQLException {

        ResultSet mailResult = this.postgreSQLConnection.readOperation("SELECT FROM users WHERE email ='" + email + "';");

        User user = null;

        if(mailResult.next()) {
            throw new RuntimeException("Email is already used");
        }

        int userUpdate = this.postgreSQLConnection.updateOperation("insert into users (email, password) values ('" + email + "','" + password + "');");

        ResultSet userResult = this.postgreSQLConnection.readOperation("SELECT * from users");

        while (userResult.next()) {
            user = new User(userResult.getLong("userId"), userResult.getString("email"), userResult.getString("password"));
        }

        return user;
    }
}
