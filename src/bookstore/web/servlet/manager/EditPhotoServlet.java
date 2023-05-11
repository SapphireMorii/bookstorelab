package bookstore.web.servlet.manager;

import bookstore.service.PhotoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class EditPhotoServlet
 */
public class EditPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		PhotoService pService = new PhotoService();
		//String t = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String id = request.getParameter("id");
		 pService.updatePhoto(id);
		//request.setAttribute("message",message);
		 response.sendRedirect(request.getContextPath()
					+ "/manager/ListPhotoServlet");
	}

}
