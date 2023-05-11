package bookstore.domain;

public class orderitem {

    private orders order;
    private products p;
    private int buynum;

    public orders getOrder() {
        return order;
    }

    public void setOrder(orders order) {
        this.order = order;
    }

    public products getP() {
        return p;
    }

    public void setP(products p) {
        this.p = p;
    }

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }



}
