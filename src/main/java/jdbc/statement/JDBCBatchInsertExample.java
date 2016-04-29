package jdbc.statement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @Title: JDBCBatchInsertExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 14:10
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCBatchInsertExample {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            batchInsertRecordsIntoTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void batchInsertRecordsIntoTable() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        Statement statement = null;

        String dateTime = dateFormat.format(new java.util.Date());
        String insertTableSQL1 = "INSERT INTO tutorials_tbl (tutorial_title, tutorial_author, submission_date) " +
                "VALUES('tutorial_title11','tutorial_author11','" + dateTime + "')";

        dateTime = dateFormat.format(new java.util.Date());
        String insertTableSQL2 = "INSERT INTO tutorials_tbl (tutorial_title, tutorial_author, submission_date) " +
                "VALUES('tutorial_title12','tutorial_author12','" + dateTime + "')";

        dateTime = dateFormat.format(new java.util.Date());
        String insertTableSQL3 = "INSERT INTO tutorials_tbl (tutorial_title, tutorial_author, submission_date) " +
                "VALUES('tutorial_title13','tutorial_author13','" + dateTime + "')";

        try {
            dbConnection = DataSource.getInstance().getConnection();
            statement = dbConnection.createStatement();

            // 设置不自动提交
            dbConnection.setAutoCommit(false);

            statement.addBatch(insertTableSQL1);
            statement.addBatch(insertTableSQL2);
            statement.addBatch(insertTableSQL3);

            statement.executeBatch();

            // 手动提交
            dbConnection.commit();
            System.out.println("Records are inserted into tutorials_tbl table!");
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
