package bookstore.web.servlet.client;

import bookstore.domain.orders;
import bookstore.domain.orderitem;
import bookstore.domain.products;
import bookstore.domain.user;
import bookstore.service.OrderService;
import bookstore.util.IdUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
//生成订单
public class CreateOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.得到当前用户
		HttpSession session = request.getSession();
		user user = (user) session.getAttribute("user");
		// 2.从购物车中获取商品信息
		Map<products, Integer> cart = (Map<products, Integer>)session.getAttribute("cart");
		// 3.将数据封装到订单对象中
		orders order = new orders();
		try {
			BeanUtils.populate(order, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		order.setId(IdUtils.getUUID());// 封装订单id
		order.setUser(user);// 封装用户信息到订单.
		for (products p : cart.keySet()) {
			orderitem item = new orderitem();
			item.setOrder(order);
			item.setBuynum(cart.get(p));
			item.setP(p);
			order.getOrderItems().add(item);
		}
		System.out.println(order);
		// 4.调用service中添加订单操作.
		OrderService service = new OrderService();
		service.addOrder(order);
		session.setAttribute("order1", order);
//		request.getRequestDispatcher("/client/orderlist.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/client/createOrderSuccess.jsp");
	}

}
