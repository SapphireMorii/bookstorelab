package bookstore.web.servlet.client;

import bookstore.dao.MessageDao;
import bookstore.domain.message;
import bookstore.domain.user;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class AddmessageSelvlet
 */
public class AddmessageSelvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddmessageSelvlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		user user = (user) request.getSession().getAttribute("user");
		message helpMessage = new message();
	    MessageDao dao = new MessageDao();
//	   int userid=user.getId();
	    //将当前时间设为添加留言的时间
		String t = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	  		helpMessage.setSend_time(t);
	  		//System.out.println(t);
	  		helpMessage.setUser(user);;
		try{
			BeanUtils.populate(helpMessage, request.getParameterMap());
			dao.addHelpMessage(helpMessage);
			// 留言成功，跳转到helpsuccess.jsp
			response.sendRedirect(request.getContextPath() + "/client/helpsuccess.jsp");
			return;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}


