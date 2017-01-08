package cn.com.bookAdmin.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.bookAdmin.book.model.Book;
import cn.com.bookAdmin.book.service.BookService;
import cn.com.bookAdmin.category.model.Category;
import cn.com.bookAdmin.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet{
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	
	/*
	 * 修改图书
	 */
	public String modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		book.setCategory(category);
		bookService.modify(book);
		return findAll(request,response);
	}
	
	/*
	 * 删除图书
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid=request.getParameter("bid");
		bookService.delete(bid);
		return findAll(request,response);
	}
	
	/*
	 * 添加图书前
	 * 1、获取所有分类，保存所有分类到request域中
	 * 2、转发到/adminjsps/admin/book/add.jsp
	 */
	public String addPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/add.jsp";
	}
	
	/*
	 * 加载所有图书
	 * 1、获取参数bid
	 * 2、通过bid调用service方法，获取book对象
	 * 3、获取所有分类，保存到request域中
	 * 4、保存到request域，转发到/adminjsps/admin/book/desc.jsp
	 */
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		request.setAttribute("book", bookService.findByBid(bid));
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/desc.jsp";
	}
	
	/*
	 * 查询所有图书
	 * 1、调用service方法，得到图书信息，保存到request域中
	 * 2、转发到/adminjsps/admin/book/list.jsp
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("bookList", bookService.findAll());
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	/*
	 * 修改图书状态
	 */
	public String borrow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Book book = bookService.findByBid(bid);
		
		if(book.getStatus() == 0) 
			book.setStatus(1);
		else
			book.setStatus(0);
		
		bookService.modify(book);
		return findAll(request,response);
	}
}
