package bookstore.web.servlet.client;

import bookstore.domain.comment;
import bookstore.domain.orders;
import bookstore.domain.user;
import bookstore.service.CommentService;
import bookstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeOrderStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得订单号数据
		String orderid = request.getParameter("orderid");
		String paySuccess = "恭喜您支付成功！";
		//---------------------------------------
		comment comment = new comment();
		//获取session对象
		HttpSession session = request.getSession();
		user user = (user) session.getAttribute("user");
		comment.setUserId(user.getId());
		if (null != orderid) {
				OrderService service = new OrderService();
				// 根据订单号修改订单状态
				try {
					boolean flag = service.updateState(orderid);
					if(flag) {
						//修改订单状态为‘1’后-------
						//根据orderid查找已被支付的商品
						orders order = service.findOrderById(orderid);
						double score= order.getMoney()*10;
						service.updateOrderScore(score, orderid);
						order.setScore(score);
						
						comment.setOrder(order);
						CommentService cservice = new CommentService();
						cservice.addComment(comment);
						//------------------------------------
						request.setAttribute("paySuccess", paySuccess);
						request.getRequestDispatcher("/findOrderByUser").forward(request, response);
						//response.sendRedirect(request.getContextPath() + "/client/paysuccess.jsp");
					    //request.getRequestDispatcher("/client/paysuccess.jsp").forward(request, response);
						//request.getRequestDispatcher("/client/orderlist.jsp").forward(request, response);
					}else {
						response.getWriter().write("修改订单状态失败");
					}
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("修改订单状态失败");
				}
			}
	}
}
