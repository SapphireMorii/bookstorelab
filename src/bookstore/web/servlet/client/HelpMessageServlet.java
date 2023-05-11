package bookstore.web.servlet.client;

import bookstore.domain.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet implementation class HelpMessageServlet
 */
public class HelpMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelpMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// 将表单提交的数据封装到javaBean
		doPost(request, response);					
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				// 获取名为“user”的session
				user user = (user) request.getSession().getAttribute("user");
				if(user==null)
				{
					response.sendRedirect(request.getContextPath() + "/client/error/privilege.jsp");
				}
				else{
					response.sendRedirect(request.getContextPath() + "/client/helpmessage.jsp");
				}
					
			}
		

}
