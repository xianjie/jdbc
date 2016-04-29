package jdbc.statement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Title: JDBCStatementUpdateExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 13:57
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCStatementUpdateExample {

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            updateRecordIntoDbUserTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateRecordIntoDbUserTable() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        Statement statement = null;
        String updateTableSQL = "UPDATE tutorials_tbl SET tutorial_title = 'tutorials_tbl_update' WHERE tutorial_id = 1";
        try {
            dbConnection = DataSource.getInstance().getConnection();
            statement = dbConnection.createStatement();
            System.out.println(updateTableSQL);
            // execute update SQL statement
            statement.execute(updateTableSQL);
            System.out.println("Record is updated to tutorials_tbl table!");
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
