package bookstore.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ResourceBundle;
import java.sql.Connection;
public class JDBCutil {

    private static DataSource dataSource = new ComboPooledDataSource();
    static Connection conn = null;
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        Connection con = tl.get();
        if (con == null) {
            con = dataSource.getConnection();
            tl.set(con);
        }
        return con;
    }
//    static {
//        ResourceBundle bundle = ResourceBundle.getBundle("jdbcConfig");
//        String driver = bundle.getString("driverManager");
//        String url = bundle.getString("url");
//        String user = bundle.getString("user");
//        String password = bundle.getString("password");
//        try{
//            Class.forName(driver);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try{
//            conn = DriverManager.getConnection(url,user,password);
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//    public static Connection getConnection(){return conn;}

    public static void release(ResultSet set, Statement stmt, Connection connection) throws SQLException{
        if(set != null){
            set.close();
        }
        if(stmt!= null){
            stmt.close();
        }
        if(connection != null)
        {
            connection.close();
        }
    }
    /**
     * 开启事务
     * @throws SQLException
     */
    public static void startTransaction() throws SQLException {
        Connection con = getConnection();
        if (con != null)
            con.setAutoCommit(false);
    }
    /**
     * 从ThreadLocal中释放并且关闭Connection,并结束事务
     * @throws SQLException
     */
    public static void releaseAndCloseConnection() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.commit();
            tl.remove();
            con.close();
        }
    }
    /**
     * 事务回滚
     * @throws SQLException
     */
    public static void rollback() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.rollback();
        }
    }
}
