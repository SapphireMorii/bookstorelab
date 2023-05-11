package bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookstore.dao.ProductDao;

import bookstore.domain.products;
import bookstore.domain.user;
import bookstore.util.JDBCutil;

import bookstore.domain.collection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;


public class CollectionDao {
    // 添加收藏品
    public void addCollection(collection c) throws SQLException {

        String sql = "insert into collection(user_id,product_id) values(?,?)";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        runner.update(sql, c.getUser().getId(),c.getProduct().getId());
    }
    // 根据id查找商品
    public collection findCollectByProductId(String id) throws SQLException {
        String sql = "select * from collection where product_id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new BeanHandler<collection>(collection.class), id);
    }
    // 根据id查找商品
    public collection findCollectById(String product_id,int user_id) throws SQLException {
        String sql = "select * from collection where product_id=? and user_id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new BeanHandler<collection>(collection.class), product_id,user_id);
    }
    //根据user_id查找收藏品
    public List<collection> findCollectByUserId(final int id) throws SQLException {
        String sql = "select * from collection where user_id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new ResultSetHandler<List<collection>>(){
            public List<collection> handle(ResultSet rs) throws SQLException{
                List<collection> collections = new ArrayList<collection>();
                while(rs.next()){
                    collection collection = new collection();
                    products product = new products();
                    user user = new user();
                    collection.setId(rs.getInt("id"));
                    String product_id = rs.getString("product_id");
                    ProductDao pd = new ProductDao();
                    product = pd.findProductById(product_id);
                    collection.setProduct(product);
                    user.setId(id);
                    collection.setUser(user);
                    collections.add(collection);
                }
                return collections;
            }
        }, id);
    }
    // 获取数据总条数
    public int findAllCount(int id) throws SQLException {
        String sql = "select count(*) from collection where user_id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        Long count = (Long) runner.query(sql, new ScalarHandler(),id);
        return count.intValue();
    }
    // 获取当前页数据
    public List<collection> findByPage(int currentPage, int currentCount,final int id) throws SQLException {
        // 要执行的sql语句
        String sql = null;
        // 参数
        Object[] obj = null;
        sql = "select * from collection  where user_id=? limit ?,?";
        obj = new Object[] { id,(currentPage - 1) * currentCount,
                currentCount, };
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        //return runner.query(sql, new BeanListHandler<collection>(collection.class),
        //obj);
        return runner.query(sql, new ResultSetHandler<List<collection>>(){
            public List<collection> handle(ResultSet rs) throws SQLException{
                List<collection> collections = new ArrayList<collection>();
                while(rs.next()){
                    collection collection = new collection();
                    products product = new products();
                    user user = new user();
                    collection.setId(rs.getInt("id"));
                    String product_id = rs.getString("product_id");
                    ProductDao pd = new ProductDao();
                    product = pd.findProductById(product_id);
                    collection.setProduct(product);
                    user.setId(id);
                    collection.setUser(user);
                    collections.add(collection);
                }
                return collections;
            }
        }, obj);
    }
    //后台系统，根据id删除收藏品信息
    public void delCollectionById(String id) throws SQLException {
        String sql = "DELETE FROM collection WHERE product_id = ?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        runner.update(sql, id);
    }
}
