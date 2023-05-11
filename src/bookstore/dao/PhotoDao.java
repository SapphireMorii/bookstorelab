package bookstore.dao;

import bookstore.domain.photo;

import bookstore.util.JDBCutil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PhotoDao {
    // 查找所有商品
    public List<photo> getAllPhoto() throws SQLException {
        String sql = "select * from photo";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new BeanListHandler<photo>(photo.class));
    }
    // 前台系统，添加信息
    public void addPhoto(photo p) throws SQLException {
        String sql = "insert into photo values(?,?,?,?)";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        runner.update(sql, p.getId(), p.getImg(),p.getState(),p.getP_time());

    }
    //前台系统，查找所有商品
    public List<photo> getselectPhoto() throws SQLException {
        String sql = "select * from photo where state=1 order by time desc ";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new BeanListHandler<photo>(photo.class));

    }
    //后台系统，根据id删除图片信息
    public void deletePhoto(String id) throws SQLException {
        String sql = "DELETE FROM photo WHERE id = ?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        runner.update(sql, id);
    }
    //后台系统，根据id设置图片为轮播图片
    public void updatePhoto(String id) throws SQLException {
        String p_time= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String sql = "update photo set state=1,time=? where id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        runner.update(sql,p_time,id);
    }
}
