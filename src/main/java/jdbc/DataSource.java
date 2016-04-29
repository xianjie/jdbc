package jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Title: DataSource
 * @Description:
 * @author: xian jie
 * @date: 2016-4-29 11:45
 * 杭州尚尚签网络科技有限公司
 * @version: 2.0
 */
public class DataSource {

    /**
     * 使用 c3p0 连接池
     */

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://127.0.0.1:3306/example";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";


    private static DataSource datasource;
    private ComboPooledDataSource comboPooledDataSource;

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(DB_DRIVER); //loads the jdbc driver
        comboPooledDataSource.setJdbcUrl(DB_CONNECTION);
        comboPooledDataSource.setUser(DB_USER);
        comboPooledDataSource.setPassword(DB_PASSWORD);

        // the settings below are optional -- c3p0 can work with defaults

        // 连接池初始化时创建的连接数
        comboPooledDataSource.setInitialPoolSize(3);
        // 最小连接数
        comboPooledDataSource.setMinPoolSize(5);
        // 连接池在无空闲连接可用时一次性创建的新数据库连接数 default:3
        comboPooledDataSource.setAcquireIncrement(5);
        // 最大连接数
        comboPooledDataSource.setMaxPoolSize(20);
        // 连接的最大空闲时间，如果超过这个时间，某个数据库连接还没有被使用，则会断开掉这个连接,单位秒
        comboPooledDataSource.setMaxIdleTime(100);
        // 连接池在获得新连接失败时重试的次数，如果小于等于0则无限重试直至连接获得成功
        comboPooledDataSource.setAcquireRetryAttempts(30);
        // 连接池在获得新连接时的间隔时间
        comboPooledDataSource.setAcquireRetryDelay(100);
        // 连接池为数据源缓存的PreparedStatement的总数。由于PreparedStatement属于单个Connection,所以这个数量应该根据应用中平均连接数乘以每个连接的平均PreparedStatement来计算
        comboPooledDataSource.setMaxStatements(180);

    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.comboPooledDataSource.getConnection();
    }


}
