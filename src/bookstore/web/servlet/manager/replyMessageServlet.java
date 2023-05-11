package bookstore.web.servlet.manager;

import bookstore.domain.message;
import bookstore.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class replyMessageServlet
 */
public class replyMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public replyMessageServlet() {
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
		MessageService mService = new MessageService();
		message message = new message();
		//获取表单参数
		int id = Integer.parseInt(request.getParameter("id"));
	
		//string id = Integer.parseInt(request.getParameter("id"));
		String content = request.getParameter("content");
		String reply = request.getParameter("reply");
		message.setReplycontent(reply);
		message.setId(id);
		//调用dao层方法
		mService.updateMessage(message);
		request.getRequestDispatcher("/manager/ListMessageServlet").forward(request, response);
	}

}
