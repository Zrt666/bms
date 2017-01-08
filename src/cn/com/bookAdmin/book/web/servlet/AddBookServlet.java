package cn.com.bookAdmin.book.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.com.bookAdmin.book.model.Book;
import cn.com.bookAdmin.book.service.BookService;
import cn.com.bookAdmin.category.model.Category;
import cn.com.bookAdmin.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet{

	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
			
		//DiskFileItemFactory factory = new DiskFileItemFactory(15*1024,new File("F:/f/temp"));
		//得到解析器
		//ServletFileUpload sfu = new ServletFileUpload(factory);
		//设置单个文件大小为15KB
		//sfu.setFileSizeMax(20*1024);
		
		try {						
			//List<FileItem> fileItemList = (List<FileItem>)sfu.parseRequest(req);
			Map<String, String> map = new HashMap<String, String>();
			/*for(FileItem fileItem : fileItemList){
				if(!fileItem.isFormField()){
					map.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
				}
			}*/
			Book book = CommonUtils.toBean(map, Book.class);
			//为book指定bid
			book.setBid(CommonUtils.uuid());
			book.setStatus(0);

			Category category = CommonUtils.toBean(map, Category.class);
			book.setCategory(category);
			
			/*
			 * 2、保存上传的文件
			 *  >要保存的路径
			 *  >要保存的文件名称
			 */
			//得到保存的目录
			//String savepath = this.getServletContext().getRealPath("/book_img");
			//得到文件名称，给原来的文件名添加uuid，放置文件名冲突
			//String filename = CommonUtils.uuid()+"_"+fileItemList.get(1).getName();
			
			//使用目录和文件名创建文件
			//File destFile = new File(savepath, filename);
			//保存上传文件到目标文件位置
			//fileItemList.get(1).write(destFile);			
		
			book.setImage("book_img/" + CommonUtils.uuid());

			bookService.add(book);
			
			req.getRequestDispatcher("/BookServlet?method=findAll")
				.forward(req, resp);
		} catch (Exception e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException){
				req.setAttribute("msg", "您上传的文件超出了15KB！");
				req.setAttribute("categoryList", categoryService.findAll());
				req.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(req, resp);
			}
			//System.out.println(e.getMessage());
		}
	}
}
