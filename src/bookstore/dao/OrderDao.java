package bookstore.dao;

import bookstore.domain.orders;


import bookstore.domain.user;
import bookstore.util.JDBCutil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    /**
     *  生成订单
     * @param order
     * @throws SQLException
     */
    public void addProduct(orders order) throws SQLException {
        // 1.生成Sql语句
        String sql = "insert into orders values(?,?,0,null,0,?)";
        // 2.生成执行sql语句的QueryRunner,不传递参数
        QueryRunner runner = new QueryRunner();
        // 3.执行update()方法插入数据
        runner.update(JDBCutil.getConnection(), sql, order.getId(),
                order.getMoney(), order
                        .getUser().getId());

    }
    /**
     * 查找用户所属订单
     */
    public List<orders> findOrderByUser(final user user) throws SQLException {
        String sql = "select * from orders where user_id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new ResultSetHandler<List<orders>>() {
            public List<orders> handle(ResultSet rs) throws SQLException {
                List<orders> orders = new ArrayList<orders>();
                while (rs.next()) {
                    orders order = new orders();
                    order.setId(rs.getString("id"));
                    order.setMoney(rs.getDouble("money"));
                    order.setOrdertime(rs.getDate("ordertime"));
                    order.setPaystate(rs.getInt("paystate"));
                    order.setScore(rs.getDouble("score"));
                    order.setUser(user);
                    orders.add(order);
                }
                return orders;
            }
        }, user.getId());
    }
    /**
     * 根据id查找订单信息
     * @param id
     * @return
     * @throws SQLException
     */
    public orders findOrderById(String id) throws SQLException {
        String sql = "select * from orders,user where orders.user_id=user.id and orders.id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        return runner.query(sql, new ResultSetHandler<orders>() {
            public orders handle(ResultSet rs) throws SQLException {
                orders order = new orders();
                while (rs.next()) {
                    order.setId(rs.getString("orders.id"));
                    order.setMoney(rs.getDouble("orders.money"));
                    order.setOrdertime(rs.getDate("orders.ordertime"));
                    order.setPaystate(rs.getInt("orders.paystate"));
                    order.setScore(rs.getDouble("score"));

                    user user = new user();
                    user.setId(rs.getInt("user.id"));
                    user.setEmail(rs.getString("user.email"));
                    user.setGender(rs.getString("user.gender"));
                    user.setActiveCode(rs.getString("user.activecode"));
                    user.setIntroduce(rs.getString("user.introduce"));
                    user.setPassword(rs.getString("user.password"));
                    user.setRegistTime(rs.getDate("user.registtime"));
                    user.setRole(rs.getString("user.role"));
                    user.setState(rs.getInt("user.state"));
                    user.setTelephone(rs.getString("user.telephone"));
                    user.setUsername(rs.getString("user.username"));
                    order.setUser(user);
                }
                return order;
            }
        }, id);
    }
    /**
     *  查找所有订单
     * @return
     * @throws SQLException
     */
    public List<orders> findAllOrder() throws SQLException {
        //1.创建sql
        String sql = "select orders.*,user.* from orders,user where user.id=orders.user_id order by orders.user_id";
        //2.创建QueryRunner对象
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        //3.返回QueryRunner对象query()方法的查询结果
        return runner.query(sql, new ResultSetHandler<List<orders>>() {
            public List<orders> handle(ResultSet rs) throws SQLException {
                //创建订单集合
                List<orders> orders = new ArrayList<orders>();
                //循环遍历订单和用户信息
                while (rs.next()) {
                    orders order = new orders();
                    order.setId(rs.getString("orders.id"));
                    order.setMoney(rs.getDouble("orders.money"));
                    order.setOrdertime(rs.getDate("orders.ordertime"));
                    order.setPaystate(rs.getInt("orders.paystate"));
                    order.setScore(rs.getDouble("score"));
                    orders.add(order);

                    user user = new user();
                    user.setId(rs.getInt("user.id"));
                    user.setEmail(rs.getString("user.email"));
                    user.setGender(rs.getString("user.gender"));
                    user.setActiveCode(rs.getString("user.activecode"));
                    user.setIntroduce(rs.getString("user.introduce"));
                    user.setPassword(rs.getString("user.password"));
                    user.setRegistTime(rs.getDate("user.registtime"));
                    user.setRole(rs.getString("user.role"));
                    user.setState(rs.getInt("user.state"));
                    user.setTelephone(rs.getString("user.telephone"));
                    user.setUsername(rs.getString("user.username"));
                    order.setUser(user);
                }
                return orders;
            }
        });
    }
    /**
     *  根据订单号修改订单状态
     * @param id
     * @throws SQLException
     */
    public boolean updateOrderState(String id) throws SQLException {
        String sql = "update orders set paystate=1 where id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        if(runner.update(sql, id)>0) {
            System.out.println(runner.update(sql, id));
            return true;
        }else {
            return false;
        }
    }
    /**
     *  根据订单号修改积分
     * @param id
     * @throws SQLException
     */
    public boolean updateOrderScore(double score, String id) throws SQLException {
        String sql = "update orders set score=? where id=?";
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        if(runner.update(sql,score,id)>0) {
            System.out.println(runner.update(sql,score ,id));
            return true;
        }else {
            return false;
        }
    }
    /**
     *  多条件查询
     * @param id
     * @param receiverName
     * @return
     * @throws SQLException
     */
    public List<orders> findOrderByManyCondition(String id, String receiverName)
            throws SQLException {
        //1.创建集合对象
        List<Object> objs = new ArrayList<Object>();
        //2.定义查询sql
        String sql = "select orders.*,user.* from orders,user where user.id=orders.user_id ";
        //3.根据参数拼接sql语句
        if (id != null && id.trim().length() > 0) {
            sql += " and orders.id=?";
            objs.add(id);
        }
        if (receiverName != null && receiverName.trim().length() > 0) {
            sql += " and receiverName=?";
            objs.add(receiverName);
        }
        sql += " order by orders.user_id";
        //4.创建QueryRunner对象
        QueryRunner runner = new QueryRunner(JDBCutil.getDataSource());
        //5.返回QueryRunner对象query方法的执行结果
        return runner.query(sql, new ResultSetHandler<List<orders>>() {
            public List<orders> handle(ResultSet rs) throws SQLException {
                List<orders> orders = new ArrayList<orders>();
                //循环遍历出订单和用户信息
                while (rs.next()) {
                    orders order = new orders();
                    order.setId(rs.getString("orders.id"));
                    order.setMoney(rs.getDouble("orders.money"));
                    order.setOrdertime(rs.getDate("orders.ordertime"));
                    order.setPaystate(rs.getInt("orders.paystate"));
                    order.setScore(rs.getDouble("score"));
                    orders.add(order);

                    user user = new user();
                    user.setId(rs.getInt("user.id"));
                    user.setEmail(rs.getString("user.email"));
                    user.setGender(rs.getString("user.gender"));
                    user.setActiveCode(rs.getString("user.activeCode"));
                    user.setIntroduce(rs.getString("user.introduce"));
                    user.setPassword(rs.getString("user.password"));
                    user.setRegistTime(rs.getDate("user.regist_time"));
                    user.setRole(rs.getString("user.role"));
                    user.setState(rs.getInt("user.state"));
                    user.setTelephone(rs.getString("user.telephone"));
                    user.setUsername(rs.getString("user.username"));
                    order.setUser(user);

                }

                return orders;
            }
        }, objs.toArray());
    }
    /**
     * 根据id删除订单
     * @param id
     * @throws SQLException
     */
    public void delOrderById(String id) throws SQLException {
        String sql="delete from orders where id=?";
        QueryRunner runner = new QueryRunner();
        runner.update(JDBCutil.getConnection(),sql,id);
    }
}