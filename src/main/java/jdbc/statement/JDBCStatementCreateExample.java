package jdbc.statement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Title: JDBCStatementCreateExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 11:32
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCStatementCreateExample {

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            createDbUserTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createDbUserTable() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        Statement statement = null;
        String createTableSQL = "create table tutorials_tbl(" +
                "   tutorial_id INT NOT NULL AUTO_INCREMENT," +
                "   tutorial_title VARCHAR(100) NOT NULL," +
                "   tutorial_author VARCHAR(40) NOT NULL," +
                "   submission_date DATE," +
                "   PRIMARY KEY ( tutorial_id )" +
                ")";
        try {
            dbConnection = DataSource.getInstance().getConnection();
            statement = dbConnection.createStatement();
            System.out.println(createTableSQL);
            // execute the SQL Statement
            statement.execute(createTableSQL);
            System.out.println("Table \"tutorials_tbl\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
