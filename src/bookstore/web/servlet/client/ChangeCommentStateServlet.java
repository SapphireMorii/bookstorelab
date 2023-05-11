package bookstore.web.servlet.client;

import bookstore.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ChangeCommentStateServlet
 */
public class ChangeCommentStateServlet extends HttpServlet {
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
		// 获得订单号数据
		String orderid = request.getParameter("orderid");
		System.out.println(orderid);
		// 获取商品号数据
		String productid = request.getParameter("productid");
		System.out.println(productid);
		// 获取评论内容
		String content = request.getParameter("scontent");
		System.out.println("content"+content);
		if (null != orderid&&null != productid) {
			CommentService cservice = new CommentService();
			boolean flag=cservice.updateState(orderid, productid, content);
			if(flag){
				request.getRequestDispatcher("/findCommentByUser").forward(request, response);
			}else{
				response.getWriter().write("修改评论状态和内容失败");
			}
		}else{
			response.getWriter().write("修改评论状态和内容失败");
		}
	}

}
