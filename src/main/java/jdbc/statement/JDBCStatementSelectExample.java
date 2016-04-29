package jdbc.statement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Title: JDBCStatementSelectExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 14:03
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCStatementSelectExample {

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            selectRecordsFromDbUserTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void selectRecordsFromDbUserTable() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet rs = null;
        String selectTableSQL = "SELECT tutorial_id, tutorial_title, tutorial_author, submission_date from tutorials_tbl";

        try {
            dbConnection = DataSource.getInstance().getConnection();
            statement = dbConnection.createStatement();
            System.out.println(selectTableSQL);
            // execute select SQL statement
            rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                int tutorial_id = rs.getInt("tutorial_id");
                String tutorial_title = rs.getString("tutorial_title");
                String tutorial_author = rs.getString("tutorial_author");
                String submission_date = rs.getString("submission_date");

                System.out.println("tutorial_id : " + tutorial_id + "&tutorial_title : " + tutorial_title
                        + "&tutorial_author : " + tutorial_author + "&submission_date : " + submission_date);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
