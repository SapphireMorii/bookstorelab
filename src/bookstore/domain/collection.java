package bookstore.domain;

public class collection {
    private int id; //收藏品编号
    private user user;
    private products product;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public user getUser() {
        return user;
    }
    public void setUser(user user) {
        this.user = user;
    }
    public products getProduct() {
        return product;
    }
    public void setProduct(products product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Collection [id=" + id + ", user=" + user + ", product=" + product + "]";
    }
}
