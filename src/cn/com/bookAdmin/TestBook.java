package cn.com.bookAdmin;

import org.junit.Test;

import cn.com.bookAdmin.book.dao.BookDao;
import cn.com.bookAdmin.book.model.Book;
import cn.com.bookAdmin.book.service.BookService;
import cn.com.bookAdmin.category.model.Category;
import cn.itcast.commons.CommonUtils;

public class TestBook {
	private BookService bookService = new BookService();
	private BookDao bookDao = new BookDao();
	@Test
	public void testAddBook() {
		Book book = new Book();
		Category c = new Category();
		c.setCid("4");
		c.setCname("cc");
		book.setBid(CommonUtils.uuid());
		book.setBname("aa");
		book.setCategory(c);
		book.setImage("aa");
		book.setPress("sadf");
		book.setStatus(0);
		bookService.add(book);
		//bookDao.add(book);
		//System.out.println(book.getCategory().getCid());
	}

}
