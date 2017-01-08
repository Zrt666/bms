package cn.com.bookAdmin.book.service;

import java.util.List;

import cn.com.bookAdmin.book.dao.BookDao;
import cn.com.bookAdmin.book.model.Book;

public class BookService {
	
	private BookDao bookDao=new BookDao();
	/*
	 * 查询所有图书
	 */
	public List<Book> findAll(){
		return bookDao.findAll();
	}
	/*
	 * 按分类查询图书
	 */
	public List<Book> findByCategory(String cid) {
		return bookDao.findByCategory(cid);
	}
	/*
	 * 通过图书BID加载图书详细信息
	 */
	public Book findByBid(String bid){
		return bookDao.findByBid(bid);
	}
	/*
	 * 添加图书
	 */
	public void add(Book book) {
		bookDao.add(book);
		
	}
	/*
	 * 删除图书
	 */
	public void delete(String bid){
		bookDao.delete(bid);
	}
	/*
	 * 修改图书
	 */
	public void modify(Book book) {
		bookDao.modify(book);
	}


}
