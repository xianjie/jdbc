package jdbc.preparedStatement;

import jdbc.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Title: JDBCPreparedStatementCreateExample
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 14:15
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class JDBCPreparedStatementCreateExample {

    public static void main(String[] argv) throws IOException, PropertyVetoException {
        try {
            createTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createTable() throws SQLException, IOException, PropertyVetoException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String createTableSQL = "CREATE TABLE photoAlbum(" +
                "  photoAlbum_Name char(20) NOT NULL CHECK(photoAlbum_Name!='')," +
                "  photoAlbum_Date datetime NOT NULL," +
                "  PRIMARY KEY (photoAlbum_Name)" +
                ")engine=innodb DEFAULT charset=utf8";
        try {
            dbConnection = DataSource.getInstance().getConnection();
            preparedStatement = dbConnection.prepareStatement(createTableSQL);
            System.out.println(createTableSQL);
            // execute create SQL statement
            preparedStatement.executeUpdate();

            System.out.println("Table \"photoAlbum\" is created!");
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
