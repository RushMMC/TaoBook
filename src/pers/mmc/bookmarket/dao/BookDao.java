package pers.mmc.bookmarket.dao;

import java.util.List;

import pers.mmc.bookmarket.entity.Book;
import pers.mmc.bookmarket.entity.BookQParam;

public interface BookDao {

	boolean addBook(Book book);

	Book queryBookById(int id);

	List<Book> queryAllBook();

	boolean updateBook(Book book);
	
	boolean deleteBookById(int id);
	
	List<Book> queryBooksByBook(BookQParam param);

	int queryBooksTotalByParam(BookQParam param);

	List<Book> queryNewBook(long start,long offset);
	
}
