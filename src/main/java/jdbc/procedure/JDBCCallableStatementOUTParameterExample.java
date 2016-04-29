package jdbc.procedure;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Title: JDBCCallableStatementOUTParameterExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 15:12
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCCallableStatementOUTParameterExample {

    /*
    CREATE DEFINER=`root`@`localhost` PROCEDURE `getById`(id int,out title varchar(100),out author varchar(100),out ssion_date varchar(100))
    BEGIN
        SELECT tutorial_title, tutorial_author, submission_date
        INTO title, author, ssion_date
        FROM tutorials_tbl WHERE tutorial_id = id;
    END
     */



    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            callOracleStoredProcOUTParameter();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void callOracleStoredProcOUTParameter() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String getByIdSql = "{call getById(?,?,?,?)}";
        try {
            dbConnection = DataSource.getInstance().getConnection();
            callableStatement = dbConnection.prepareCall(getByIdSql);

            callableStatement.setInt(1, 5);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);

            // execute getById store procedure
            callableStatement.executeUpdate();


            String tutorial_title = callableStatement.getString(2);
            String tutorial_author = callableStatement.getString(3);
            String submission_date = callableStatement.getString(4);

            System.out.println("tutorial_title : " + tutorial_title);
            System.out.println("tutorial_author : " + tutorial_author);
            System.out.println("submission_date : " + submission_date);
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
