package bookstore.web.servlet.client;

import bookstore.domain.message;
import bookstore.domain.user;
import bookstore.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class FindMessageByIdServlet
 */
public class FindMessageBynameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindMessageBynameServlet() {
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
		// 获取名为“user”的session
		user user = (user) request.getSession().getAttribute("user");
		if(user == null)
		{
			System.out.println("找不到用户");
		}
		   int userid=user.getId();
		   System.out.println(userid);
		MessageService mService = new MessageService();
		List<message> message = mService.findMessageByuserid(userid);
		//PrintWriter out=PrintWriter
		if(message!=null){
			request.setAttribute("mess",message);
			//System.out.println(message.getTelephone());
			request.getRequestDispatcher("/client/message.jsp").forward(request, response);
		}else{
		  request.getRequestDispatcher("/client/messagenull.jsp").forward(request, response);
		}
		}
}
