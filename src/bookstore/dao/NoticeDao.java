package bookstore.dao;

import bookstore.domain.notice;
import bookstore.util.JDBCutil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDao {
    //后台系统，查询所有的公告
    public List<notice> getAllNotices() throws SQLException {
        String sql = "select * from notice order by time desc limit 0,10";
//        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
//        return runner.query(sql, new BeanListHandler<notice>(notice.class));
        Connection connection = JDBCutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        List<notice> noticeList = new ArrayList<>();
        while (rs.next())
        {
            notice notice = new notice();
            notice.setN_id(rs.getInt("id"));
            notice.setTitle(rs.getString("title"));
            notice.setDetails(rs.getString("details"));
            notice.setN_time(rs.getString("time"));

            noticeList.add(notice);
        }
        return noticeList;
    }

    //后台系统，添加公告
    public void addNotice(notice n) throws SQLException {
        String sql = "insert into notice(title,details,time) values(?,?,?)";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        runner.update(sql, n.getTitle(),n.getDetails(),n.getN_time());
    }

    //后台系统，根据id查找公告
    public notice findNoticeById(String n_id) throws SQLException {
        String sql = "select * from notice where id = ?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new BeanHandler<notice>(notice.class),n_id);
    }

    //后台系统，根据id修改单个公告
    public void updateNotice(notice n) throws SQLException {
        String sql = "update notice set title=?,details=?,time=? where id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        runner.update(sql, n.getTitle(),n.getDetails(),n.getN_time(),n.getN_id());
    }

    //后台系统，根据id删除公告
    public void deleteNotice(String n_id) throws SQLException {
        String sql = "delete from notice where id = ?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        runner.update(sql, n_id);
    }

    //前台系统，查询最新添加或修改的一条公告
    public notice getRecentNotice() throws SQLException {
        String sql = "select * from notice order by time desc limit 0,1";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new BeanHandler<notice>(notice.class));
    }
}
