import dao.ArticleDao;
import dao.PostgreSQLConnection;
import dao.UserDao;

import java.sql.*;


public class App {
    public static void main(String[] args) {
        try  {
            PostgreSQLConnection postgresConn = new PostgreSQLConnection();
            ArticleDao articleDao = new ArticleDao(postgresConn);
            UserDao userDao = new UserDao(postgresConn);

            System.out.println("Successfully connected");

            Menu menu = new Menu(articleDao, userDao);

            menu.start();
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();;
        } catch (Exception e) {
            System.out.println("The program stopped with the following message: " + e.getMessage());
        }
    }
}