package jdbc.procedure;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Title: JDBCCallableStatementALLExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 15:42
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCCallableStatementALLExample {

    /*
    CREATE DEFINER=`root`@`localhost` PROCEDURE `getAll`()
    BEGIN
        select * from tutorials_tbl;
    END
     */

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            callOracleStoredProcCURSORParameter();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void callOracleStoredProcCURSORParameter() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String getDBUSERCursorSql = "{call getAll()}";
        try {
            dbConnection = DataSource.getInstance().getConnection();
            callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);
            // execute getDBUSERCursor store procedure
            callableStatement.executeUpdate();
            // get cursor and cast it to ResultSet
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                int tutorial_id = rs.getInt("tutorial_id");
                String tutorial_title = rs.getString("tutorial_title");
                String tutorial_author = rs.getString("tutorial_author");
                String submission_date = rs.getString("submission_date");

                System.out.println("tutorial_id: " + tutorial_id + "&tutorial_title : " + tutorial_title
                        + "&tutorial_author: " + tutorial_author + "&submission_date : " + submission_date);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
