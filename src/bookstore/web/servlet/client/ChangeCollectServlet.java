package bookstore.web.servlet.client;

import bookstore.domain.collection;
import bookstore.exception.FindProductByIdException;
import bookstore.service.CollectionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ChangeCollectServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 得到商品id
		String id = request.getParameter("id");
		System.out.println(id);
		// 从session中获取收藏品.
		HttpSession session = request.getSession();
		List<collection> collect = (List<collection>) session.getAttribute("collect");
		//products product = new products();
		//product.setId(id);
		collection c = new collection();
		CollectionService service = new CollectionService();
		try {
			c = service.findCollectByProductId(id);
			if(c!=null){
				service.deleteCollection(id);
				//collect.remove(product);
			}else{
				System.out.println("收藏品不存在！");
			}
		} catch (FindProductByIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		response.sendRedirect(request.getContextPath() + "/showCollection");
	}
}
