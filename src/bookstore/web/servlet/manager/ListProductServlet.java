package bookstore.web.servlet.manager;

import bookstore.domain.PageBean;
import bookstore.domain.products;
import bookstore.exception.ListProductException;
import bookstore.service.PagebeanService;
import bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * 后台系统
 * 查询所有商品信息的servlet
 */
public class ListProductServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.创建service层的对象
				ProductService service = new ProductService();
				try {
					// 2.调用service层用于查询所有商品的方法
					List<products> ps = service.listAll();
					// 3.将查询出的所有商品放进request域中
					request.setAttribute("ps", ps);
					// 4.将查询出的所有商品放进bean中封装
					/**************************/	
					String CurrentPage =request.getParameter("currentPage")==null?"1":request.getParameter("currentPage") ;
					int    CurrentPage1 = Integer.parseInt(CurrentPage);
					PageBean bean = new PageBean();
					PagebeanService pgservice = new PagebeanService();
					bean = pgservice.FindProductByPage(CurrentPage1, ps);
					request.setAttribute("bean", bean);
					/**************************/	
					// 5.重定向到list.jsp页面
					request.getRequestDispatcher("/admin/products/list.jsp").forward(
							request, response);
					return;
				} catch (ListProductException e) {
					e.printStackTrace();
					response.getWriter().write(e.getMessage());
					return;
				}
	}
}
