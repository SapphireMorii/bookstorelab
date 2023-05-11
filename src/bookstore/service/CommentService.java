package bookstore.service;

import bookstore.dao.CommentDao;
import bookstore.domain.comment;

import java.sql.SQLException;
import java.util.List;

public class CommentService {
    private CommentDao cdao = new CommentDao();
    // 1.添加评论
    public void addComment(comment comment) {
        try {
            cdao.addComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 根据用户查找评论
    public List<comment> findCommentByUser(comment comment) {
        List<comment> comments = null;
        try {
            // 查找出评论信息
            comments = cdao.findCommentByUser(comment);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    // 评论成功后修改评论状态
    public boolean updateState(String oid,String pid,String content) {
        try {
            if(cdao.updateCommentState(oid,pid,content)) {
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

    // 根据商品号查找评论
    public List<comment> findCommentByPid(String pid) {
        List<comment> comments = null;
        try {
            // 查找出订单信息
            comments = cdao.findCommentByPid(pid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    //后台系统，查询所有公告
    public List<Object[]> getAllComments() {
        List<Object[]> oblists = null;
        try {
            oblists = cdao.getAllComments();
        } catch (SQLException e) {
            throw new RuntimeException("查询所有的公告失败！");
        }
        return oblists;
    }
    //后台系统，根据id查找公告
    public comment findCommentById(String id) {
        try {
            return cdao.findCommentById(id);
        } catch (SQLException e) {
            throw new RuntimeException("根据id查找公告失败！");
        }
    }

    //后台系统，根据id删除公告
    public void deleteComment(String id) {
        try {
            cdao.deleteComment(id);
        } catch (SQLException e) {
            throw new RuntimeException("根据id删除公告失败！");
        }
    }
}

