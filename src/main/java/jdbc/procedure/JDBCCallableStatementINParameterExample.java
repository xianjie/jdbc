package jdbc.procedure;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * @Title: JDBCCallableStatementINParameterExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 14:53
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCCallableStatementINParameterExample {

    /*A stored procedure in Mysql database

    SQL
    CREATE PROCEDURE `tutorials_tbl_insert_procedure` (title VARCHAR(100),author VARCHAR(100),ssion_date VARCHAR(100))
    BEGIN
        INSERT INTO tutorials_tbl (tutorial_title, tutorial_author, submission_date)
        VALUES(title,author,ssion_date);
    END
    */


    /**
     *  调用 存储过程
     */


    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            callOracleStoredProcINParameter();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void callOracleStoredProcINParameter() throws SQLException, IOException, PropertyVetoException {

        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String insertStoreProc = "{call tutorials_tbl_insert_procedure(?,?,?)}";

        try {
            dbConnection = DataSource.getInstance().getConnection();
            callableStatement = dbConnection.prepareCall(insertStoreProc);

            callableStatement.setString(1, "tutorial_title18");
            callableStatement.setString(2, "tutorial_author18");
            callableStatement.setString(3, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));

            // execute tutorials_tbl_insert_procedure store procedure
            callableStatement.executeUpdate();

            System.out.println("Record is inserted into tutorials_tbl table!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
