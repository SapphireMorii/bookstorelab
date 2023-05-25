package bookstore.dao;

import java.sql.*;

import bookstore.domain.user;
import bookstore.util.JDBCutil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.sql.SQLException;


public class UserDao {
    // 添加用户
    public void addUser(user user) throws SQLException {
        String sql = "insert into user(username,password,gender,email,telephone,introduce,active_code) values(?,?,?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        int row = runner.update(sql, user.getUsername(), user.getPassword(),
                user.getGender(), user.getEmail(), user.getTelephone(),
                user.getIntroduce(), user.getActiveCode());
        if (row == 0) {
            throw new RuntimeException();
        }
    }

    // 根据激活码查找用户
    public user findUserByActiveCode(String activeCode) throws SQLException {
        String sql = "select * from user where active_code =  ? ";
        Connection connection = JDBCutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,activeCode);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next())
        {
            user user = new user();
            user.setRegistTime(rs.getDate("regist_time"));
            return user;
        }
        return null;

//        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
//        user u = runner.query(sql, new BeanHandler<user>(user.class), activeCode);
//        return runner.query(sql, new BeanHandler<user>(user.class), activeCode);
    }

    // 激活用戶
    public void activeUser(String activeCode) throws SQLException {
        String sql = "update user set state=? where active_code=?";
        try {
            Connection connection = JDBCutil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, activeCode);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据用户名与密码查找用户
    public user findUserByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select * from user where username=? and password=?";
        try {
            Connection connection = JDBCutil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user u = new user();
                u.setActiveCode(rs.getString("active_code"));
                u.setState(rs.getInt("state"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getInt("id"));
                u.setIntroduce(rs.getString("introduce"));
                u.setTelephone(rs.getString("telephone"));
                return u;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据用户id查找用户
    public user findUserByUserid(int id) throws SQLException {
        String sql = "select * from user where id=?";
        try {
            Connection connection = JDBCutil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                user u = new user();
                u.setActiveCode(rs.getString("active_code"));
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setGender(rs.getString("gender"));
                u.setTelephone(rs.getString("telephone"));
                u.setIntroduce(rs.getString("introduce"));
                u.setPassword(rs.getString("password"));
                u.setRegistTime(rs.getDate("regist_time"));
                u.setRole(rs.getString("role"));
                u.setUsername(rs.getString("username"));
                u.setState(rs.getInt("state"));
                return u;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
