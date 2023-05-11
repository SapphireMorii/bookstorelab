package bookstore.web.servlet.client;

import bookstore.domain.photo;
import bookstore.service.PhotoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;






/**
 * Servlet implementation class ShowLoginServlet
 */
public class ShowLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowLoginServlet() {
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
	
		//查询最近上传图片，传递到index.jsp页面进行展示
				PhotoService pService = new PhotoService();
				List<photo> photos = pService.getselectPhotos();
				/*for(photo p:photo){
					System.out.println(p);
				}*/
				Double randomNum=Math.random();
				request.setAttribute("randomNum", randomNum);
				System.out.println(photos.size());
				System.out.println(request.getContextPath().toString());
				if(photos.size()>=5) {
					request.setAttribute("pho", photos);
					//请求转发
					request.getRequestDispatcher("/client/index.jsp").forward(request, response);
				}else if(photos.size()==0) {
					//ResultSet rs = null;
					for(int i=0;i<5;i++)
					{
							//rs.next();
							photo photo=new photo();
							String img="/client/ad/index_ad["+i+"].jpg";
							photo.setImg(img);
							photos.add(photo);	
					}
					request.setAttribute("pho", photos);
					//请求转发
					request.getRequestDispatcher("/client/index.jsp").forward(request, response);
				}else {
						for(int i=photos.size()+1;i<=5;i++) {
							photo photo=new photo();
							String img="/client/ad/index_ad["+i+"].jpg";
							photo.setImg(img);
							photos.add(photo);
						}
					request.setAttribute("pho", photos);
					//请求转发
					request.getRequestDispatcher("/client/index.jsp").forward(request, response);
					}
			}
}




