package bookstore.web.servlet.manager;

import bookstore.domain.notice;
import bookstore.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *	后台查询所有公告的servlet
 */
public class ListNoticeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NoticeService nService = new NoticeService();
		List<notice> notices = nService.getAllNotices();
		req.setAttribute("notices", notices);
		req.getRequestDispatcher("/admin/notices/list.jsp").forward(req, resp);
	}
}
