package bookstore.dao;

import bookstore.domain.message;
import bookstore.util.JDBCutil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    // 前台系统，添加留言信息
    public void addHelpMessage(message help) throws SQLException {
        String sql = "insert into message(title,content,user_id,telephone,replycontent,send_time) values(?,?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        int row = runner.update(sql, help.getTitle(),help.getContent(),help.getUserid(),
                help.getTelephone(),help.getReplycontent(),help.getSend_time());
        if (row == 0) {
            throw new RuntimeException();
        }
    }
    //前台系统，根据用户名查询所有的留言
    //后台系统，根据id查找公告
    public List<message> findMessageByuserid(int id) throws SQLException {

        String sql = "select * from message where user_id = ?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new ResultSetHandler<List<message>>() {
            public List<message> handle(ResultSet rs) throws SQLException {
                List<message> messages = new ArrayList<message>();
                while (rs.next()) {
                    message message = new message();
                    message.setId(rs.getInt("id"));
                    message.setTitle(rs.getString("title"));
                    message.setContent(rs.getString("content"));
                    message.setReplycontent(rs.getString("replycontent"));

                    //message.setUser(user);
                    messages.add(message);
                }
                return messages;
            }
        }, id);
    }
    //后台系统，查询所有的留言
    public List<message> getAllMessages() throws SQLException {
        String sql = "select * from message";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new BeanListHandler<message>(message.class));
    }
    //后台系统，查询最新添加的留言信息
    public message getRecentMessage() throws SQLException {
        String sql = "select * from message";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new BeanHandler<message>(message.class));
    }

    //后台系统，根据id删除公告
    public void deleteMessage(String id) throws SQLException {
        String sql = "delete from message where id = ?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        runner.update(sql, id);
    }
    //后台系统，根据id修改单个留言
    public void updateMessage(message n) throws SQLException {
        String sql = "update message set replycontent=? where id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        runner.update(sql, n.getReplycontent(),n.getId());
    }
    //后台系统，根据id查找公告
    public message findMessageById(String id) throws SQLException {
        String sql = "select * from message where id = ?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new BeanHandler<message>(message.class),id);
    }

}
