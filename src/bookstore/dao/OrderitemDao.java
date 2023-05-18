package bookstore.dao;

import bookstore.domain.orderitem;
import bookstore.domain.orders;
import bookstore.domain.products;
import bookstore.util.JDBCutil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderitemDao {
    public void addOrderItem(orders order) throws SQLException {
        // 1.生成sql语句
        String sql = "insert into orderItem values(?,?,?)";

        QueryRunner runner = new QueryRunner();

        List<orderitem> items = order.getOrderItems();

        Object[][] params = new Object[items.size()][3];

        for (int i = 0; i < params.length; i++) {
            params[i][0] = items.get(i).getOrder().getId();
            params[i][1] = items.get(i).getP().getId();
            params[i][2] = items.get(i).getBuynum();
        }

        runner.batch(JDBCutil.getConnection(), sql, params);
    }

    // 根据订单查找订单项.并将订单项中商品查找到。
    public List<orderitem> findOrderItemByOrder(final orders order)
            throws SQLException {
        String sql = "select * from orderItem,Products where products.id=orderItem.product_id and order_id=?";

        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());

        return runner.query(sql, new ResultSetHandler<List<orderitem>>() {
            public List<orderitem> handle(ResultSet rs) throws SQLException {

                List<orderitem> items = new ArrayList<orderitem>();
                while (rs.next()) {
                    orderitem item = new orderitem();

                    item.setOrder(order);
                    item.setBuynum(rs.getInt("buy_num"));

                    products p = new products();
                    p.setCategory(rs.getString("category"));
                    p.setId(rs.getString("id"));
                    p.setDescription(rs.getString("description"));
                    p.setImgurl(rs.getString("img_url"));
                    p.setName(rs.getString("name"));
                    p.setPnum(rs.getInt("pnum"));
                    p.setPrice(rs.getDouble("price"));
                    item.setP(p);

                    items.add(item);
                }

                return items;
            }
        }, order.getId());
    }
    // 根据订单号和商品号查找订单项.并将订单项中商品查找到。
    public List<orderitem> findOrderItemByOPid(final orders order,String pid)
            throws SQLException {
        String sql = "select * from orderItem,Products where products.id=orderItem.product_id and order_id=? and products.id=?";

        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());

        return runner.query(sql, new ResultSetHandler<List<orderitem>>() {
            public List<orderitem> handle(ResultSet rs) throws SQLException {

                List<orderitem> items = new ArrayList<orderitem>();
                while (rs.next()) {
                    orderitem item = new orderitem();
                    item.setOrder(order);
                    item.setBuynum(rs.getInt("buy_num"));

                    products p = new products();
                    p.setCategory(rs.getString("category"));
                    p.setId(rs.getString("id"));
                    p.setDescription(rs.getString("description"));
                    p.setImgurl(rs.getString("img_url"));
                    p.setName(rs.getString("name"));
                    p.setPnum(rs.getInt("pnum"));
                    p.setPrice(rs.getDouble("price"));
                    item.setP(p);

                    items.add(item);
                }
                return items;
            }
        }, order.getId(),pid);
    }
    //根据订单id删除订单项
    public void delOrderItems(String id) throws SQLException {
        String sql="delete from orderItem where order_id=?";

        QueryRunner runner=new QueryRunner();

        runner.update(JDBCutil.getConnection(),sql,id);
    }
}
