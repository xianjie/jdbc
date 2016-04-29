package jdbc.preparedStatement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Title: JDBCPreparedStatementDeleteExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 14:29
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCPreparedStatementDeleteExample {

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            deleteRecordFromTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteRecordFromTable() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String deleteSQL = "DELETE FROM tutorials_tbl WHERE tutorial_id = ?";
        try {
            dbConnection = DataSource.getInstance().getConnection();
            preparedStatement = dbConnection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, 20);

            // execute delete SQL statement
            preparedStatement.executeUpdate();
            System.out.println("Record is deleted!");
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
