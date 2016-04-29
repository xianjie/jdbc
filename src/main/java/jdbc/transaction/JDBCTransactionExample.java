package jdbc.transaction;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * @Title: JDBCTransactionExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 15:58
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCTransactionExample {

    public static void main(String[] argv) throws SQLException, IOException, PropertyVetoException {

        Connection dbConnection = null;
        PreparedStatement preparedStatementInsert = null;
        PreparedStatement preparedStatementUpdate = null;

        String insertTableSQL = "INSERT INTO tutorials_tbl (tutorial_title, tutorial_author, submission_date) VALUES (?,?,?)";

        String updateTableSQL = "UPDATE tutorials_tbl SET tutorial_title = ? WHERE tutorial_id = ?";

        try {
            dbConnection = DataSource.getInstance().getConnection();

            // 不自动提交
            dbConnection.setAutoCommit(false);

            preparedStatementInsert = dbConnection.prepareStatement(insertTableSQL);
            preparedStatementInsert.setString(1, "tutorial_title21");
            preparedStatementInsert.setString(2, "tutorial_author21");
            preparedStatementInsert.setString(3, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
            preparedStatementInsert.executeUpdate();

            preparedStatementUpdate = dbConnection.prepareStatement(updateTableSQL);

            // 可以设置一个很很长的字符串导致DB错误
            // 正常执行
            // preparedStatementUpdate.setString(1, "new string");
            // 错误执行
            preparedStatementUpdate.setString(1, "JackJackJackJackJackJackJackJackJackJackJackJackJackJackJack" +
                    "JackJackJackJackJackJackJackJackJackJackJackJackJackJackJackJackJackJackJackJackJackJack");
            preparedStatementUpdate.setInt(2, 5);
            preparedStatementUpdate.executeUpdate();

            // 手动提交全部执行动作
            dbConnection.commit();
            System.out.println("Done!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // 异常回滚
            dbConnection.rollback();

            System.out.println("回滚操作");
        } finally {
            if (preparedStatementInsert != null) {
                preparedStatementInsert.close();
            }
            if (preparedStatementUpdate != null) {
                preparedStatementUpdate.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
