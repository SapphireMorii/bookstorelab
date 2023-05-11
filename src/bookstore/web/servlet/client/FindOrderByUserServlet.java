package bookstore.web.servlet.client;

import bookstore.domain.orders;
import bookstore.domain.user;
import bookstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
public class FindOrderByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取名为“user”的session
		user user = (user) request.getSession().getAttribute("user");
		// 调用service中的方法,根据用户信息查找订单
		OrderService service = new OrderService();
		List<orders> orders = service.findOrderByUser(user);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/client/orderlist.jsp").forward(request, response);
	}
}
