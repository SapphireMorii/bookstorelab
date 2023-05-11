package bookstore.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class orders {
    private String id; // 订单编号
    private double money; // 订单总价
//    private String receiverAddress; // 送货地址
//    private String receiverName; // 收货人姓名
//    private String receiverPhone; // 收货人电话
    private int paystate; // 订单状态
    private Date ordertime; // 下单时间
    private double score;  //订单积分

    private user user; // 订单所属用户
    private List<orderitem> orderItems = new ArrayList<orderitem>();

    public List<orderitem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<orderitem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getPaystate() {
        return paystate;
    }

    public void setPaystate(int paystate) {
        this.paystate = paystate;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "orders [id=" + id + ", money=" + money + ", paystate=" + paystate
                + ", ordertime=" + ordertime + ", user=" + user
                + ", orderItems=" + orderItems + "]";
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
