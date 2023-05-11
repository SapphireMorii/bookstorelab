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
/**
 * 按条件查询订单
 * @author admin
 *
 */
public class FindOrderByManyConditionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取订单编号和收件人名称
				String id = request.getParameter("id");
				String receiverName = request.getParameter("receiverName");
		        //创建Service层对象
				OrderService service = new OrderService();
				//调用Service层OrderService类的findOrderByManyCondition()方法查询数据
				List<orders> ps = service.findOrderByManyCondition(id, receiverName);
		        //将查询结果添加到request作用域中
				//request.setAttribute("orders", orders);
				
				/*****************/
				//第一次调用为1，以后更新为新的数值
				String CurrentPage =request.getParameter("currentPage")==null?"1":request.getParameter("currentPage") ;
				int    CurrentPage1 = Integer.parseInt(CurrentPage);
				PageBeanOrder bean = new PageBeanOrder();
				PagebeanService pgservice = new PagebeanService();
				bean = pgservice.FindOrderByPage(CurrentPage1, ps);
				System.out.println(bean.getPs().size());
				request.setAttribute("bean", bean);
				/*****************/
				
		        //请求转发到list.jsp页面，并将request请求和response响应也转发到该页面中
				request.getRequestDispatcher("/admin/orders/searchlist.jsp").forward(request,
						response);
	}
}
