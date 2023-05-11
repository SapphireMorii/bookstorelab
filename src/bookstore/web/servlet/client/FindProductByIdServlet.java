package bookstore.web.servlet.client;

import bookstore.domain.products;
import bookstore.exception.FindProductByIdException;
import bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * 根据商品id查找指定商品信息的servlet
 */
public class FindProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到商品的id
		String id = request.getParameter("id");
		// 获取type参数值，此处的type用于区别普通用户和超级用户
		String type = request.getParameter("type");		
		ProductService service = new ProductService();		
		try {
			// 调用service层方法，通过id查找商品
			products p = service.findProductById(id);
			//---------------
			HttpSession session = request.getSession();
			session.setAttribute("p", p);
			//----------------
			//request.setAttribute("p", p);
			// 普通用户默认不传递type值，会跳转到info.jsp页面
			if (type == null) {
				request.getRequestDispatcher("/client/info.jsp").forward(request,response);
				return;
			}						
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}
}
