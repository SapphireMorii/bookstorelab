package bookstore.web.servlet.client;


import bookstore.domain.collection;
import bookstore.domain.products;
import bookstore.domain.user;
import bookstore.exception.AddProductException;
import bookstore.exception.FindProductByIdException;
import bookstore.service.CollectionService;
import bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddCollectServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//int flag=0;
		// 1.得到商品id
		String id = request.getParameter("id");
		//System.out.println(id);
		// 2.调用service层方法，根据id查找商品
		ProductService service = new ProductService();
		CollectionService cService = new CollectionService();
		//获得session对象的user属性
		HttpSession session = request.getSession();
		user user = (user) session.getAttribute("user");
		if(user==null){
			response.sendRedirect(request.getContextPath() + "/client/error/privilege.jsp");
		}else{
			//定义一个收藏品对象
			collection c ;
			try {
				//根据product_id和user_id查找藏品		
				c = cService.findCollectById(id,user.getId());
				//如果没有收藏过该书
				if(c==null){
					//flag=0;
					c = new collection();
					products product = service.findProductById(id);
					//System.out.println(product);
					c.setProduct(product);		
					c.setUser(user);
					//添加收藏品
					cService.addCollection(c);			
					//如果收藏夹为null，创建收藏夹
					/*if(collections==null){
						collections = new ArrayList<collection>();
					}
					for(collection collection : collections){
						System.out.println(collection.getProduct().getImgurl());
					}*/				
					//将商品加入收藏夹
					//collections.add(c);
					//session.setAttribute("collect", collections);
					
				}/*else{
					flag=1;
					session.setAttribute("flag", flag);
					response.sendRedirect(request.getContextPath() + "/client/info.jsp");
				}*/
				response.sendRedirect(request.getContextPath() + "/showCollection");
				
			} catch (FindProductByIdException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AddProductException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
