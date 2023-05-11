package bookstore.web.servlet.client;

import bookstore.domain.PageBeanCollection;
import bookstore.domain.user;
import bookstore.service.CollectionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowCollectionByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		user user = (user) session.getAttribute("user");		
		if(user==null){
			response.sendRedirect(request.getContextPath() + "/client/error/privilege.jsp");
		}else{
			int id = user.getId();
			// 1.定义当前页码，默认为1
			int currentPage = 1;
			String _currentPage = request.getParameter("currentPage");		
			if (_currentPage != null) {
				currentPage = Integer.parseInt(_currentPage);
			}
			System.out.println(currentPage);
			// 2.定义每页显示条数,默认为4
			int currentCount = 4;
			String _currentCount = request.getParameter("currentCount");		
			if (_currentCount != null) {
				currentCount = Integer.parseInt(_currentCount);
			}
			System.out.println(currentCount);
			// 3.调用service，完成获取当前页分页Bean数据.
			CollectionService service = new CollectionService();
			PageBeanCollection pbc = service.findCollectionByPage(currentPage, currentCount,id);
			// 将数据存储到request范围，跳转到product_list.jsp页面展示
			request.setAttribute("pbc", pbc);
			System.out.println(pbc.getTotalCount());
			System.out.println(pbc.getTotalPage());
			request.getRequestDispatcher("/client/collect.jsp").forward(request, response);
			return;
	    }
	}
}
