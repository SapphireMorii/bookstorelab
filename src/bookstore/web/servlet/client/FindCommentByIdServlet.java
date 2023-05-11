package bookstore.web.servlet.client;

import bookstore.domain.orders;
import bookstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class FindCommentByIdServlet
 */
public class FindCommentByIdServlet extends HttpServlet {
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
		//1.获取用户类型
		String type=request.getParameter("type");		
		//2.得到要查询的订单的id
		String oid = request.getParameter("oid");
		System.out.println("oid----"+oid);
		//得到商品号
		String pid = request.getParameter("pid");
		System.out.println("pid----"+pid);
		//3.根据id查找订单
		OrderService service = new OrderService();
		orders order = service.findOrderByOPid(oid,pid);
		System.out.println("order:"+order);
		//查找商品的评论状态
		/*List<OrderItem> orderItems = order.getOrderItems();
		
		products p = new products();
		for(int i=0;i<orderItems.size();i++){
			p = orderItems.get(i).getP();
		}*/
		//4.将查询出的订单信息添加到request作用域中
		request.setAttribute("order", order);		
		//得到要商品评论的状态
		String commentState = request.getParameter("commentstate");
		request.setAttribute("commentState", commentState);
		//5.如果用户类型不为null，则请求转发到view.jsp页面，否则转发到orderInfo.jsp页面
		if(type!=null){
			request.getRequestDispatcher("/admin/orders/view.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/client/commentInfo.jsp").forward(request, response);
	}

}
