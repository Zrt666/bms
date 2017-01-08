package cn.com.bookAdmin.category.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.bookAdmin.category.model.Category;
import cn.com.bookAdmin.category.service.CategoryException;
import cn.com.bookAdmin.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;


@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet{
private CategoryService categoryService = new CategoryService();
	
	/*
	 * 修改分类
	 * 1、封装表单数据
	 * 2、调用service方法完成修改工作
	 * 3、调用findAll方法返回查询结果
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category=CommonUtils.toBean(request.getParameterMap(), Category.class);
		categoryService.edit(category);
		return findAll(request, response);
	}
	
	/*
	 * 加载分类（修改分类前奏）
	 * 1、获取参数cid
	 * 2、通过cid调用service方法，得到category对象
	 * 3、保存到request域，转发到mod.jsp
	 */
	public String editPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid=request.getParameter("cid");
		request.setAttribute("category", categoryService.load(cid));
		return "f:/adminjsps/admin/category/mod.jsp";
	}
	
	/*
	 * 删除分类
	 * 1、获取参数cid
	 * 2、调用service方法，传递参数cid
	 *  >如果抛出异常，保存异常信息，转发到msg.jsp显示
	 *  >（1）如果图书分类数量大于0，抛出异常
	 *  >（2）如果图书数量等于0.删除分类
	 * 3、调用findAll方法回到查询结果
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid=request.getParameter("cid");
		try {
			categoryService.delete(cid);
			return findAll(request,response);
		} catch (CategoryException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/adminjsps/msg.jsp";
		}
	}
	
	/*
	 * 添加分类
	 * 1、封装表单数据
	 * 2、补全cid
	 * 3、调用service方法完成添加
	 * 4、调用findAll方法回到查询结果
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category=CommonUtils.toBean(request.getParameterMap(), Category.class);
		category.setCid(CommonUtils.uuid());
		categoryService.add(category);
		return findAll(request,response);
	}
	
	
	/*
	 * 查询分类
	 * 1、调用service方法，得到所有分类
	 * 2、保存到request域，转发到/adminjsps/admin/category/list.jsp
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List categoryList=categoryService.findAll();
		request.setAttribute("categoryList", categoryList);
		return "f:/adminjsps/admin/category/list.jsp";
	}

}
