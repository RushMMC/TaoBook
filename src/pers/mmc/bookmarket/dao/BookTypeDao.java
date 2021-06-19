package pers.mmc.bookmarket.dao;

import java.util.List;

import pers.mmc.bookmarket.entity.BookType;

public interface BookTypeDao {
	
	boolean addType(BookType type);
	
	List<BookType> queryAllBookType();

	boolean updateBookType(BookType type);

	BookType queryBookType(int id);
}
