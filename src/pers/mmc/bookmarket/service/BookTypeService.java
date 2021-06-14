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
	
	public List<BookType> queryAllBookType(){
		return dao.queryAllBookType();
	}
}
