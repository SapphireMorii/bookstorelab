package bookstore.service;

import bookstore.dao.OrderDao;
import bookstore.dao.OrderitemDao;
import bookstore.dao.ProductDao;
import bookstore.domain.orderitem;
import bookstore.domain.orders;
import bookstore.domain.user;
import bookstore.util.JDBCutil;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderitemDao oidao = new OrderitemDao();
    private OrderDao odao = new OrderDao();
    private ProductDao pdao = new ProductDao();
    // 1.添加订单
    public void addOrder(orders order) {
        try {
            // 1.开启事务
            JDBCutil.startTransaction();
            // 2.完成操作
            // 2.1向orders表中添加数据
            odao.addProduct(order);
            // 2.2向orderItem表中添加数据
            oidao.addOrderItem(order);
            // 2.3修改商品表中数据.
            pdao.changeProductNum(order);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                JDBCutil.rollback(); // 事务回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                // 关闭，释放以及提交事务
                JDBCutil.releaseAndCloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // 根据用户查找订单
    public List<orders> findOrderByUser(user user) {
        List<orders> orders = null;
        try {
            // 查找出订单信息
            orders = odao.findOrderByUser(user);

            // // 查找出订单项信息.
            // for (orders order : orders) {
            // List<OrderItem> items = oidao.findOrderItemByOrder(order);
            // //查找到订单中的订单项信息
            //
            // order.setOrderItems(items);
            // }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    // 根据id查找订单
    public orders findOrderById(String id) {
        orders order = null;
        try {
            order = odao.findOrderById(id);
            List<orderitem> items = oidao.findOrderItemByOrder(order);
            order.setOrderItems(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    // 根据id查找订单
    public orders findOrderByOPid(String oid,String pid) {
        orders order = null;
        try {
            order = odao.findOrderById(oid);
            System.out.println("service----"+order);
            List<orderitem> items = oidao.findOrderItemByOPid(order, pid);
            order.setOrderItems(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    // 查找所有订单
    public List<orders> findAllOrder() {
        List<orders> orders = null;
        try {
            // 查找出订单信息
            orders = odao.findAllOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    // 支付成功后修改订单状态
    public boolean updateState(String id) {
        try {
            if(odao.updateOrderState(id)) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    // 多条件查询订单信息
    public List<orders> findOrderByManyCondition(String id, String receiverName) {
        List<orders> orders = null;
        try {
            orders = odao.findOrderByManyCondition(id, receiverName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    //根据id删除订单 管理员删除订单
    public void delOrderById(String id) {
        try {
            JDBCutil.startTransaction();//开启事务
            oidao.delOrderItems(id);
            odao.delOrderById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                JDBCutil.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                JDBCutil.releaseAndCloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //普通用户删除订单
    public void delOrderByIdWithClient(String id) {
        try {
            JDBCutil.startTransaction();//开启事务
            //从订单项中查找商品购买数量
            orders order=new orders();
            order.setId(id);
            List<orderitem> items=oidao.findOrderItemByOrder(order);
            //修改商品数量			
            pdao.updateProductNum(items);
            oidao.delOrderItems(id); //删除订单项
            odao.delOrderById(id); //删除订单
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                JDBCutil.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                JDBCutil.releaseAndCloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean updateOrderScore(double score, String orderid) {
        try {
            if(odao.updateOrderScore(score,orderid)) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}