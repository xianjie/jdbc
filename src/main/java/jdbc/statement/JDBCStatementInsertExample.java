package jdbc.statement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 * @Title: JDBCStatementInsertExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 13:48
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCStatementInsertExample {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            insertRecordIntoDbUserTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertRecordIntoDbUserTable() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        Statement statement = null;
        String dateTime = dateFormat.format(new java.util.Date());
        String insertTableSQL = "INSERT INTO tutorials_tbl (tutorial_title, tutorial_author, submission_date) " +
                "VALUES('tutorial_title10','tutorial_author10','" + dateTime + "')";
        try {
            dbConnection = DataSource.getInstance().getConnection();
            statement = dbConnection.createStatement();

            System.out.println(insertTableSQL);

            // execute insert SQL Statement
            statement.executeUpdate(insertTableSQL);
            System.out.println("Record is inserted into tutorials_tbl table!");
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
