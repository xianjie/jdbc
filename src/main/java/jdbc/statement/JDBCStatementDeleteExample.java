package jdbc.statement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Title: JDBCStatementDeleteExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 14:01
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCStatementDeleteExample {

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            deleteRecordFromDbUserTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteRecordFromDbUserTable() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        Statement statement = null;
        String deleteTableSQL = "DELETE FROM tutorials_tbl WHERE tutorial_id = 1";
        try {
            dbConnection = DataSource.getInstance().getConnection();
            statement = dbConnection.createStatement();
            System.out.println(deleteTableSQL);
            // execute delete SQL statement
            statement.execute(deleteTableSQL);
            System.out.println("Record is deleted from tutorials_tbl table!");
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
