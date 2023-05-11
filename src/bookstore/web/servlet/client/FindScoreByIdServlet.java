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

/**
 * Servlet implementation class FindScoreByIdServlet
 */
public class FindScoreByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取名为“user”的session
				user user = (user) request.getSession().getAttribute("user");
				// 调用service中的方法,根据用户信息查找订单
				OrderService service = new OrderService();
				List<orders> orders = service.findOrderByUser(user);
				double total = 0;
				for(orders order : orders){  // 未结账则积分为0
					if(order.getPaystate()==0){
						order.setScore(0);
					}
					total += order.getScore();
				}
				request.setAttribute("orscore", orders);
				request.setAttribute("totalscore", total);
				request.getRequestDispatcher("/client/accountscore.jsp").forward(request, response);
	}
}
