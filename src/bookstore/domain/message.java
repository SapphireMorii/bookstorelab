package bookstore.domain;

public class message {
    @Override
    public String toString() {
        return "message [id=" + id + ", title=" + title + ", content=" + content + ", user_id=" + user_id
                + ", telephone=" + telephone + ", replycontent=" + replycontent + ", send_time=" + send_time + "]";
    }
    private static final long serialVersionUID = 1L;
    private int id;
    private int user_id;
    private String title; // 留言标题
    private String content; // 留言内容
    private String telephone; // 用户联系电话
    private String replycontent; //回复内容
    private String send_time;//留言时间

    public String getSend_time() {
        return send_time;
    }
    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getUserid() {
        return user_id;
    }
    public void setUserid(int user_id) {
        this.user_id = user_id;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getReplycontent() {
        return replycontent;
    }
    public void setReplycontent(String replycontent) {
        this.replycontent = replycontent;
    }

}
