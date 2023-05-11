package bookstore.web.servlet.client;

import bookstore.domain.comment;
import bookstore.domain.products;
import bookstore.domain.user;
import bookstore.exception.FindProductByIdException;
import bookstore.service.CommentService;
import bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class FindCommentContentByIdServlet
 */
public class FindCommentContentByIdServlet extends HttpServlet {
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
		//获得session对象的user属性
		HttpSession session = request.getSession();
		user user = (user) session.getAttribute("user");
		//3.根据id查找评论
		CommentService cservice = new CommentService();
		ProductService pservice = new ProductService();
		comment comment = new comment();
		comment.setUserId(user.getId());
		List<comment> comments =cservice.findCommentByUser(comment);
		System.out.println("comments:"+comments);
		//4.查询出的商品信息
		//request.setAttribute("comments", comments);		
		List<products> products = new ArrayList<products>();
		products product = new products();
		for(int i=0;i<comments.size();i++){
			String pid = comments.get(i).getProductId();
			System.out.println("内容："+comments.get(i).getContent());
			try {
				product = pservice.findProductById(pid);
				products.add(product);
			} catch (FindProductByIdException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Map<comment,products> map = new HashMap<comment,products>();	
		for(int i=0;i<comments.size();i++){
			map.put(comments.get(i), products.get(i));
			//System.out.println(map.get(comments.get(i)));
			//System.out.println(map.get(products.get(i)));
		}
		//5.将map添加到request作用域中
		request.setAttribute("map", map);		
		request.getRequestDispatcher("/client/showComment.jsp").forward(request, response);
	}

}
