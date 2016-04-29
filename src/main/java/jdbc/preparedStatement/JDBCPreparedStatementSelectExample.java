package jdbc.preparedStatement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Title: JDBCPreparedStatementSelectExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 14:37
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCPreparedStatementSelectExample {

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            selectRecordsFromTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void selectRecordsFromTable() throws SQLException, IOException, PropertyVetoException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT tutorial_id, tutorial_title, tutorial_author, submission_date from tutorials_tbl WHERE tutorial_id=?";

        try {
            dbConnection = DataSource.getInstance().getConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, 13);
            // execute select SQL statement
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int tutorial_id = rs.getInt("tutorial_id");
                String tutorial_title = rs.getString("tutorial_title");
                String tutorial_author = rs.getString("tutorial_author");
                String submission_date = rs.getString("submission_date");
                System.out.println("tutorial_id: " + tutorial_id + "&tutorial_title : " + tutorial_title
                        + "&tutorial_author : " + tutorial_author + "&submission_date : " + submission_date);

            }
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
