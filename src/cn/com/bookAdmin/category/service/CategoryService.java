package cn.com.bookAdmin.category.service;

import java.util.List;

import cn.com.bookAdmin.book.dao.BookDao;
import cn.com.bookAdmin.category.dao.CategoryDao;
import cn.com.bookAdmin.category.model.Category;


public class CategoryService {
	
	private CategoryDao categoryDao=new CategoryDao();
	private BookDao bookDao=new BookDao();

	/*
	 * 查询所有分类
	 */
	public List<Category> findAll() {		
		return categoryDao.findAll();
	}
	
	/*
	 * 添加分类
	 */
	public void add(Category category) {
		categoryDao.add(category);	
	}

	/*
	 * 删除分类
	 */
	public void delete(String cid) throws CategoryException {
		//获取该图书下的分类的本数
		int count=bookDao.getCountByCid(cid);
		//如果该分类下存在图书，不能删除，则抛出异常
		if(count>0) throw new CategoryException("该分类下还有图书，不能删除");
		//删除该分类
		categoryDao.delete(cid);
	}

	/*
	 * 加载分类
	 */
	public Category load(String cid) {
		return categoryDao.load(cid);
	}

	/*
	 * 修改分类
	 */
	public void edit(Category category) {
		categoryDao.edit(category);
		
	}

}
