package bookstore.web.servlet.client;

import bookstore.domain.products;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
/**
 * 购物车内容变更
 * @author admin
 *
 */
public class ChangeCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.得到商品id
		String id = request.getParameter("id");
		// 2.得到要修改的数量
		int count = Integer.parseInt(request.getParameter("count"));
		// 3.从session中获取购物车.
		HttpSession session = request.getSession();
		Map<products, Integer> cart = (Map<products, Integer>) session.getAttribute("cart");
		products p = new products();
		p.setId(id);
		if (count != 0) {
			cart.put(p, count);
		} else {
			cart.remove(p);
		}
		response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
		return;
	}
}
