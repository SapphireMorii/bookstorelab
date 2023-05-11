package bookstore.web.servlet.manager;

import bookstore.domain.photo;
import bookstore.service.PhotoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ListPhotoServlet
 */
public class ListPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		// 1.创建service层的对象
				PhotoService service = new PhotoService();
				try {
					// 2.调用service层用于查询所有商品的方法
					List<photo> pt = service.getAllPhotos();
					// 3.将查询出的所有商品放进request域中
					request.setAttribute("pt", pt);
					// 4.重定向到show.jsp页面
					request.getRequestDispatcher("/admin/photo/show.jsp").forward(
							request, response);
					return;
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write(e.getMessage());
					return;
				}
			}

}
