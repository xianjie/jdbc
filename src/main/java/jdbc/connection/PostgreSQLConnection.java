package jdbc.connection;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Title: PostgreSQLConnection
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 11:30
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class PostgreSQLConnection {

    @Test
    public void postgreSQL() {
        System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
            return;

        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/example", "root", "123456");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
