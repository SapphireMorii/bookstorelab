package bookstore.web.servlet.manager;

import bookstore.domain.products;
import bookstore.exception.FindProductByIdException;
import bookstore.service.ProductService;
import bookstore.util.ChartPie1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 按条件查询销量
 * @author admin
 *
 */
public class FindSellByManyConditions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		System.out.println(year+"---"+month+"---"+name+"............"+category);
		
		ProductService service = new ProductService();
		//第一种：可以按照指定时间范围，查询商品总体销售状况
		Map<products,String> map = new LinkedHashMap<products,String>();
		List<Object[]> ps = service.downloadByManyConditions(year, month, name, category);
		if(ps==null){
			request.setAttribute("map", map);
			System.out.println("=========未输入条件");
		}
		
		for (int i = 0; i < ps.size(); i++) {
			Object[] arr=ps.get(i);
			try {
				System.out.println(arr[0]+"--"+arr[1]);
				map.put(service.findProductByName(String.valueOf(arr[0])),String.valueOf(arr[1]));
				//以商品名找到商品的属性，封装在map 里
			} catch (FindProductByIdException e) {
				e.printStackTrace();
				System.out.println("找不到");
				}
			}
		int flag=0;
		if(map.size()>1){
			ChartPie1 chart= new ChartPie1();
			chart.chart(map);
//			chart.chart(map);
			flag=1;
		}
		request.setAttribute("display", flag);
		request.setAttribute("map", map);
		//解决jsp缓存问题
		double randomNum = Math.random();
		request.setAttribute("randomNum", randomNum);
		request.getRequestDispatcher("/admin/products/download.jsp").forward(request, response);
	}

}
