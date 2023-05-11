package bookstore.service;

import bookstore.dao.MessageDao;
import bookstore.domain.message;

import java.sql.SQLException;
import java.util.List;

public class MessageService {
    private MessageDao dao = new MessageDao();
    //前台系统，查询个人留言信息
    //后台系统，根据id查找公告
    public List<message> findMessageByuserid(int id) {
        List<message> messages = null;
        try {
            messages=dao.findMessageByuserid(id);
        } catch (SQLException e) {
            throw new RuntimeException("根据id查找公告失败！");
        }
        return messages;
    }
    //后台系统，查询所有公告
    public List<message> getAllMessages() {
        try {
            return dao.getAllMessages();
        } catch (SQLException e) {
            throw new RuntimeException("查询所有的留言失败！");
        }
    }
    //后台系统，根据id删除留言
    public void deleteMessage(String n_id) {
        try {
            dao.deleteMessage(n_id);
        } catch (SQLException e) {
            throw new RuntimeException("根据id删除留言失败！");
        }
    }
    //后台系统，根据id修改留言
    public void updateMessage(message bean) {
        try {
            dao.updateMessage(bean);
        } catch (SQLException e) {
//				throw new RuntimeException("根据id修改留言失败！");
            e.printStackTrace();
        }
    }
    //后台系统，根据id查找公告
    public message findMessage(String n_id) {
        try {
            return dao.findMessageById(n_id);
        } catch (SQLException e) {
            throw new RuntimeException("根据id查找公告失败！");
        }
    }

}
