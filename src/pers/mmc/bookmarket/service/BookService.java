package pers.mmc.bookmarket.service;

import java.util.List;

import pers.mmc.bookmarket.dao.BookDao;
import pers.mmc.bookmarket.dao.BookDaoImpl;
import pers.mmc.bookmarket.entity.Book;
import pers.mmc.bookmarket.entity.BookQParam;

public class BookService {

	BookDao bookDao;
	public BookService() {
		bookDao = new BookDaoImpl();
	}
	
	public boolean addBook(Book book){
		return bookDao.addBook(book);
	}

	public Book queryBookById(int id){
		return bookDao.queryBookById(id);
	}

	public List<Book> queryAllBook(){
		return bookDao.queryAllBook();
	}

	boolean deleteBookById(int id){
		return bookDao.deleteBookById(id);
	}
	
	public List<Book> queryBooksByParam(BookQParam param){
		return bookDao.queryBooksByBook(param);
	}
	
	public int queryBooksTotalByParam(BookQParam param){
		return bookDao.queryBooksTotalByParam(param);
	}
	
	public boolean updateBook(Book book) {
		return bookDao.updateBook(book);
	}
	
	public List<Book> queryNewBooks(long start,long offset){
		return bookDao.queryNewBook(start,offset);
	}
}
