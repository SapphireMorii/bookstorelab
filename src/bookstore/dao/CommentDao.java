package bookstore.dao;

import bookstore.domain.comment;
import bookstore.domain.orderitem;
import bookstore.domain.orders;
import bookstore.util.JDBCutil;
import jdk.nashorn.internal.scripts.JD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    //生成comment记录
    public void addComment(comment comment) throws SQLException {
        // 1.生成Sql语句
        String sql = "insert into comment(user_id,order_id,product_id,comment_state,content) values(?,?,?,0,null)";
        // 2.生成执行sql语句的QueryRunner,不传递参数
        QueryRunner runner = new QueryRunner();

        List<orderitem> items = comment.getOrder().getOrderItems();

        Object[][] params = new Object[items.size()][3];

        for (int i = 0; i < params.length; i++) {
            params[i][0] = comment.getUserId();
            params[i][1] = items.get(i).getOrder().getId();
            params[i][2] = items.get(i).getP().getId();
            //params[i][3] = comment.getProductName();
            //params[i][4] = comment.getProductPrice();
        }

        // 3.执行batch()方法插入数据
        runner.batch(JDBCutil.getConnection(), sql, params);
    }
    //根据用户查评论
    public List<comment> findCommentByUser(final comment comment) throws SQLException {
        String sql = "select * from comment where user_id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new ResultSetHandler<List<comment>>() {
            public List<comment> handle(ResultSet rs) throws SQLException {
                List<comment> comments = new ArrayList<comment>();
                while (rs.next()) {
                    comment comment = new comment();
                    orders order = new orders();
                    order.setId(rs.getString("order_id"));
                    comment.setOrder(order);
                    comment.setProductId(rs.getString("product_id"));
                    //comment.setProductName(rs.getString("product_name"));
                    comment.setUserId(rs.getInt("user_id"));
                    comment.setCommentstate(rs.getInt("commentstate"));
                    comment.setContent(rs.getString("content"));
                    comments.add(comment);
                }
                return comments;
            }
        }, comment.getUserId());
    }
    //根据订单号和商品号修改评论状态和内容
    public boolean updateCommentState(String oid,String pid,String content) throws SQLException {
        String sql = "update comment set comment_state=1,content=? where order_id=? and product_id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        System.out.println(runner.update(sql, content, oid, pid));
        if(runner.update(sql, content, oid, pid)>0) {
            System.out.println(runner.update(sql, content, oid, pid));
            return true;
        }else {
            return false;
        }
    }

    //根据商品号查评论内容
    public List<comment> findCommentByPid(String pid) throws SQLException {
        String sql = "select * from comment where product_id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new ResultSetHandler<List<comment>>() {
            public List<comment> handle(ResultSet rs) throws SQLException {
                List<comment> comments = new ArrayList<comment>();
                while (rs.next()) {
                    comment comment = new comment();
                    orders order = new orders();
                    order.setId(rs.getString("order_id"));
                    comment.setOrder(order);
                    comment.setProductId(rs.getString("product_id"));
                    comment.setUserId(rs.getInt("user_id"));
                    comment.setCommentstate(rs.getInt("commentstate"));
                    comment.setContent(rs.getString("content"));
                    comments.add(comment);
                }
                return comments;
            }
        }, pid);
    }
    //后台系统，查询所有的公告
    public List<Object[]> getAllComments() throws SQLException {
        String sql = "select comment.id,comment.content,user.username,products.name from comment,user,products where comment.product_id=products.id and comment.user_id=user.id order by comment.id asc limit 0,10";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new ArrayListHandler());
    }

    //后台系统，根据id查找公告
    public comment findCommentById(String id) throws SQLException {
        String sql = "select * from comment where id = ?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new BeanHandler<comment>(comment.class),id);
    }
    //后台系统，根据id删除公告
    public void deleteComment(String id) throws SQLException {
        String sql = "delete from comment where id = ?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        runner.update(sql, id);
    }
}
