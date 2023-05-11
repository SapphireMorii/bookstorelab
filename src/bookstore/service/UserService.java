package bookstore.service;

import bookstore.dao.UserDao;
import bookstore.domain.user;
import bookstore.exception.ActiveUserException;
import bookstore.exception.RegisterException;
import bookstore.util.MD5Util;
import bookstore.util.MailUtils;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class UserService {
    private UserDao dao = new UserDao();
    // 注册操作
    public void  register(user user) throws RegisterException {
        // 调用dao完成注册操作
        try {
            //密码加密处理
            String inStrPwd = MD5Util.string2MD5(user.getPassword());
            user.setPassword(inStrPwd);
            dao.addUser(user);
            // 发送激活邮件
            String emailMsg = "感谢您注册网上书城，点击"
                    + "<a href='http://localhost:8080/activeUser?activeCode="
                    + user.getActiveCode() + "'>&nbsp;激活&nbsp;</a>后使用。"
                    + "<br />为保障您的账户安全，请在24小时内完成激活操作";
            MailUtils.sendMail(user.getEmail(), emailMsg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RegisterException("注冊失败");
        }
    }
    // 激活用户
    public void activeUser(String activeCode) throws ActiveUserException {
        try {
            System.out.println(activeCode);
            // 根据激活码查找用户
            user user = dao.findUserByActiveCode(activeCode);
            if (user == null) {
                throw new ActiveUserException("激活用户失败");
            }
//            if(user.getRegistTime()==null)
//            {
//                System.out.println("没有信息");
//            }
            // 判断激活码是否过期 24小时内激活有效.
            // 1.得到注册时间
            Date registTime = user.getRegistTime();
            System.out.println(System.currentTimeMillis());
            System.out.println(registTime.getTime());
            // 2.判断是否超时
            long time = System.currentTimeMillis() - registTime.getTime();
            if (time / 1000 / 60 / 60 > 24) {
                throw new ActiveUserException("激活码过期");
            }
            // 激活用户，就是修改用户的state状态
            dao.activeUser(activeCode);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ActiveUserException("激活用户失败");
        }
    }
    // 登录操作
    public user login(String username, String password) throws LoginException {
        try {
            //密码解码处理
            String inStrPwd = MD5Util.string2MD5(password);
            //根据登录时表单输入的用户名和密码，查找用户
            user user = dao.findUserByUsernameAndPassword(username, inStrPwd);
            //如果找到，还需要确定用户是否为激活用户
            if (user != null) {
                // 只有是激活才能登录成功，否则提示“用户未激活”
                if (user.getState() == 1) {
                    return user;
                }
                throw new LoginException("用户未激活");
            }
            throw new LoginException("用户名或密码错误");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoginException("登录失败");
        }
    }
    //根据用户id查找用户
    public user findUserByUserid(int id) throws Exception{
        try {
            user user = dao.findUserByUserid(id);
            return user;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("查找失败");
        }
    }
}