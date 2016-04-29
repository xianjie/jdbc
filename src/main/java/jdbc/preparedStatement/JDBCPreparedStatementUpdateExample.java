package jdbc.preparedStatement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Title: JDBCPreparedStatementUpdateExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 14:25
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCPreparedStatementUpdateExample {

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            updateRecordToTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateRecordToTable() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateTableSQL = "UPDATE tutorials_tbl SET tutorial_title = ? WHERE tutorial_id = ?";
        try {
            dbConnection = DataSource.getInstance().getConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, "tutorial_title14_update");
            preparedStatement.setInt(2, 15);
            // execute update SQL statement
            preparedStatement.executeUpdate();
            System.out.println("Record is updated to tutorials_tbl table!");
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
