package bookstore.web.servlet.manager;

import bookstore.domain.PageBean;
import bookstore.domain.products;
import bookstore.service.PagebeanService;
import bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
public class FindProductByManyConditionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.获取表单数据
		String id = request.getParameter("id"); // 商品id
		String name = request.getParameter("name"); // 商品名称
		String category = request.getParameter("category"); // 商品类别
		HttpSession session=request.getSession();
		String minprice = null; // 最小价格
		String maxprice = null; // 最大价格
		minprice = request.getParameter("minprice");
		maxprice = request.getParameter("maxprice"); 
		session.setAttribute("minprice",minprice);
		session.setAttribute("maxprice",maxprice);
		// 2.创建ProductService对象
		ProductService service = new ProductService();
		// 3.调用service层用于条件查询的方法
		List<products> ps = service.findProductByManyCondition(id, name,
				category, minprice, maxprice);
		// 4.将条件查询的结果放进request域中
// 		request.setAttribute("ps", ps);
		/*****************/
		//第一次调用为1，以后更新为新的数值
		String CurrentPage =request.getParameter("currentPage")==null?"1":request.getParameter("currentPage") ;
		int    CurrentPage1 = Integer.parseInt(CurrentPage);
		PageBean beansc = new PageBean();
		PagebeanService pgservice = new PagebeanService();
		beansc = pgservice.FindProductByPage(CurrentPage1, ps);
		request.setAttribute("bean", beansc);
		/*****************/
		// 5.请求重定向到商品管理首页searchlist.jsp页面
		request.getRequestDispatcher("/admin/products/searchlist.jsp").forward(
				request, response);
	}
}
