package jdbc.preparedStatement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * @Title: JDBCBatchInsertExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 14:42
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCBatchInsertExample {

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            batchInsertRecordsIntoTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void batchInsertRecordsIntoTable() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO tutorials_tbl (tutorial_title, tutorial_author, submission_date) VALUES (?,?,?)";

        try {
            dbConnection = DataSource.getInstance().getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            // 设置不自动提交
            dbConnection.setAutoCommit(false);

            preparedStatement.setString(1, "tutorial_title15");
            preparedStatement.setString(2, "tutorial_author15");
            preparedStatement.setString(3, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
            preparedStatement.addBatch();

            preparedStatement.setString(1, "tutorial_title16");
            preparedStatement.setString(2, "tutorial_author16");
            preparedStatement.setString(3, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
            preparedStatement.addBatch();

            preparedStatement.setString(1, "tutorial_title17");
            preparedStatement.setString(2, "tutorial_author17");
            preparedStatement.setString(3, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
            preparedStatement.addBatch();

            preparedStatement.executeBatch();

            // 手动提交
            dbConnection.commit();
            System.out.println("Record is inserted into tutorials_tbl table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            dbConnection.rollback();
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
