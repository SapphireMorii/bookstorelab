package bookstore.web.servlet.client;

import bookstore.domain.comment;
import bookstore.domain.user;
import bookstore.exception.FindProductByIdException;
import bookstore.service.CommentService;
import bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class FindCommentByProductidServlet
 */
public class FindCommentByProductidServlet extends HttpServlet {
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
		//获取商品号
		String pid = request.getParameter("pid");
		CommentService cservice = new CommentService();
		UserService uservice = new UserService();
		List<comment> comments = cservice.findCommentByPid(pid);
		//4.查询出的商品信息	
		List<user> users = new ArrayList<user>();
		user user = new user();
		for(int i=0;i<comments.size();i++){
			int uid = comments.get(i).getUserId();
			try {
				user = uservice.findUserByUserid(uid);
				users.add(user);
			} catch (FindProductByIdException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Map<comment,user> map = new HashMap<comment,user>();	
		for(int i=0;i<comments.size();i++){
			map.put(comments.get(i), users.get(i));
			//System.out.println(map.get(comments.get(i)));
			//System.out.println(map.get(products.get(i)));
		}
		//5.将map添加到request作用域中
		request.setAttribute("map", map);
		//request.setAttribute("cs", comments);
		request.getRequestDispatcher("/client/info.jsp").forward(request, response);
	}

}
