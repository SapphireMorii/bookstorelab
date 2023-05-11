package bookstore.web.servlet.manager;

import bookstore.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class ListCommentServlet
 */
public class ListCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommentService cService = new CommentService();
		Map<String,Object[]> map = new HashMap<String,Object[]>();
		List<Object[]> os = cService.getAllComments();
		Object[] arr = {};
		for(int i=0;i<os.size();i++){
			arr = os.get(i);
			map.put("arrs"+i, arr);
			for(int j=0;j<3;j++){
				System.out.println(arr[j]);
			}
			//map.put(a, value)
		}				
		request.setAttribute("map", map);
		request.getRequestDispatcher("/admin/comments/list.jsp").forward(request, response);
	}

}
