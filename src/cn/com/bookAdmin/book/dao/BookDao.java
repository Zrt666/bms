package cn.com.bookAdmin.book.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.bookAdmin.book.model.Book;
import cn.com.bookAdmin.category.model.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	
	private QueryRunner qr=new TxQueryRunner();
	/*
	 * 查询所有图书
	 */
	public List<Book> findAll(){
		try {
			String sql="select * from book";
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 按分类查询图书
	 */
	public List<Book> findByCategory(String cid) {
		try {
			String sql="select * from book where cid=?";
			return qr.query(sql, new BeanListHandler<Book>(Book.class), cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过图书id加载图书详细信息
	 */
	public Book findByBid(String bid){
		try {
			/*
			 * 我们需要在book对象中保存category信息
			 */
			String sql="select * from book where bid=?";
			Map<String, Object> map = qr.query(sql, new MapHandler(), bid);
			/*
			 * 使用一个map映射出两个对象，
			 * 然后给两个对象建立关系
			 */
			Category category = CommonUtils.toBean(map, Category.class); 
			Book book = CommonUtils.toBean(map, Book.class);
			book.setCategory(category);
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过分类id获得图书的本数
	 */
	public int getCountByCid(String cid) {
		try {
			String sql="select count(*) from book where cid=?";
			Number num=(Number)qr.query(sql, new ScalarHandler(), cid);
			return num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 添加图书
	 */
	public void add(Book book) {
		try {
			String sql="insert into book values (?,?,?,?,?,?)";
			Object param[]={book.getBid(),book.getBname(),book.getPress(),
					book.getImage(),book.getStatus(),book.getCategory().getCid()};
			qr.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/*
	 * 删除图书（数据库中为假删除，即修改del为ture）
	 */
	public void delete(String bid){
		try {
			String sql="delete from book where bid=?";
			qr.update(sql, bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 修改图书
	 */
	public void modify(Book book) {
		try {
			String sql="update book set bname=?,press=?,image=?,status=?,cid=? where bid=?";
			Object param[]={book.getBname(),book.getPress(),
					book.getImage(),book.getStatus(),book.getCategory().getCid(),book.getBid()};
			qr.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
