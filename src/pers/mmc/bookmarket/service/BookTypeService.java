package pers.mmc.bookmarket.service;

import java.util.List;

import pers.mmc.bookmarket.dao.BookTypeDao;
import pers.mmc.bookmarket.dao.BookTypeDaoImpl;
import pers.mmc.bookmarket.entity.BookType;

public class BookTypeService {

	BookTypeDao dao;
	public BookTypeService() {
		dao = new BookTypeDaoImpl();
	}
	
	public boolean addType(BookType type){
		return dao.addType(type);
	}
	
	public BookType queryBookType(int id){
		return dao.queryBookType(id);
	}
	public boolean updateType(BookType type){
		return dao.updateBookType(type);
	}
	
	public List<BookType> queryAllBookType(){
		return dao.queryAllBookType();
	}
}
