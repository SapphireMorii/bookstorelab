package bookstore.web.servlet.manager;

import bookstore.domain.photo;
import bookstore.service.PhotoService;
import bookstore.util.FileUploadUtils;
import bookstore.util.IdUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class AddPhotoServlet
 */
public class AddPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//将当前时间设为添加公告的时间
	//String t = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
	// 创建javaBean,将上传数据封装.
		photo p = new photo();
		Map<String,Object> map = new HashMap<String, Object>();
		// 封装商品id
		map.put("id", IdUtils.getUUID());
		//将当前时间设为添加公告的时间
	  //String t = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	  //map.put("p_time",t);
	  DiskFileItemFactory dfif = new DiskFileItemFactory();
		// 设置临时文件存储位置
		dfif.setRepository(new File(this.getServletContext().getRealPath(
				"/temp")));
		// 设置上传文件缓存大小为10m
		dfif.setSizeThreshold(1024 * 1024 * 10);
		// 创建上传组件
		ServletFileUpload upload = new ServletFileUpload(dfif);
		// 处理上传文件中文乱码
		upload.setHeaderEncoding("utf-8");
		try {
			// 解析request得到所有的FileItem
			List<FileItem> items = upload.parseRequest(request);
			// 遍历所有FileItem
			for (FileItem item : items) {
				// 判断当前是否是上传组件
				if (item.isFormField()) {
					// 不是上传组件
					String fieldName = item.getFieldName(); // 获取组件名称
					String value = item.getString("utf-8"); // 解决乱码问题
					map.put(fieldName, value);
				} else {
					// 是上传组件
					// 得到上传文件真实名称
					String fileName = item.getName();
					fileName = FileUploadUtils.subFileName(fileName);

					// 得到随机名称
					String randomName = FileUploadUtils
							.generateRandonFileName(fileName);

					// 得到随机目录
					String randomDir = FileUploadUtils
							.generateRandomDir(randomName);
					// 图片存储父目录
					String imgurl_parent = "/productImg" + randomDir;

					File parentDir = new File(this.getServletContext()
							.getRealPath(imgurl_parent));
					// 验证目录是否存在，如果不存在，创建出来
					if (!parentDir.exists()) {
						parentDir.mkdirs();
					}
					String imgurl = imgurl_parent + "/" + randomName;
					map.put("img", imgurl);
                    //item.write(new File(imgurl));
					int s=0;
                    map.put("state",s);
					IOUtils.copy(item.getInputStream(), new FileOutputStream(
							new File(parentDir, randomName)));
					item.delete();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// 将数据封装到javaBean中
			BeanUtils.populate(p, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		PhotoService service = new PhotoService();
		// 调用service完成添加商品操作
		service.addPhoto(p);
		//System.out.print(p.getImg());
		response.sendRedirect(request.getContextPath()
				+ "/ListPhotoServlet");
		return;
	}
}	
			
				
		

		          	          

	

