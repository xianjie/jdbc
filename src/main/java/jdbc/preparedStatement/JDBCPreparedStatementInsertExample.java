package jdbc.preparedStatement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * @Title: JDBCPreparedStatementInsertExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 14:22
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCPreparedStatementInsertExample {

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            insertRecordIntoTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertRecordIntoTable() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO tutorials_tbl (tutorial_title, tutorial_author, submission_date) VALUES (?,?,?)";

        try {
            dbConnection = DataSource.getInstance().getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, "tutorial_title14");
            preparedStatement.setString(2, "tutorial_author14");
            preparedStatement.setString(3, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
            // execute insert SQL statement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into tutorials_tbl table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
