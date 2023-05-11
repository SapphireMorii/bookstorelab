package bookstore.domain;

public class comment {
    private int id;
    private String productId;
    private int userId;
    private orders order;
    private int commentstate;
    private String content;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public orders getOrder() {
        return order;
    }
    public void setOrder(orders order) {
        this.order = order;
    }
    public int getCommentstate() {
        return commentstate;
    }
    public void setCommentstate(int commentstate) {
        this.commentstate = commentstate;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
