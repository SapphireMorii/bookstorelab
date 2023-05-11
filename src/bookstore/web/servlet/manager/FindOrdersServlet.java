package bookstore.web.servlet.manager;

import bookstore.domain.orders;
import bookstore.domain.PageBeanOrder;
import bookstore.service.OrderService;
import bookstore.service.PagebeanService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
//查找所有订单
public class FindOrdersServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 创建Service层对象
				OrderService service = new OrderService();
				// 调用Service层对象的findAllOrder()方法查询订单列表
				List<orders> ps = service.findAllOrder();
				
				/**************************/	
				//将查询到的订单信息添加到request作用域
				String CurrentPage =request.getParameter("currentPage")==null?"1":request.getParameter("currentPage") ;
				int    CurrentPage1 = Integer.parseInt(CurrentPage);
				PageBeanOrder bean = new PageBeanOrder();
				PagebeanService pgservice = new PagebeanService();
				bean = pgservice.FindOrderByPage(CurrentPage1, ps);
				request.setAttribute("bean", bean);
				/**************************/	
				// 将请求转发到list.jsp页面
				request.getRequestDispatcher("/admin/orders/list.jsp").forward(request,response);
			}
}
