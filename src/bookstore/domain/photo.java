package bookstore.domain;

public class photo {
    private String id; // 图片编号
    private String img; // 图片路径
    private int state; // 图片状态
    private  String p_time;//设置轮播的时间

    public String getP_time() {
        return p_time;
    }
    public void setP_time(String p_time) {
        this.p_time = p_time;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return "photo [id=" + id + ", img=" + img + ", state=" + state + "]";
    }

}
