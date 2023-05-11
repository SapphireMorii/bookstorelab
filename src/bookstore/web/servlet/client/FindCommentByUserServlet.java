package bookstore.web.servlet.client;

import bookstore.domain.comment;
import bookstore.domain.user;
import bookstore.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class FindCommentByUserServlet
 */
public class FindCommentByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		// 获取名为“user”的session
		user user = (user) request.getSession().getAttribute("user");
		comment comment = new comment();
		comment.setUserId(user.getId());
		// 调用service中的方法,根据用户信息查找订单
		CommentService service = new CommentService();
		List<comment> comments = service.findCommentByUser(comment);
		request.setAttribute("comments", comments);		
		request.getRequestDispatcher("/client/commentlist.jsp").forward(request, response);
		
	}

}
