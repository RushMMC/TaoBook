package pers.mmc.bookmarket.service;

import java.util.List;

import pers.mmc.bookmarket.dao.ShoppingCartDao;
import pers.mmc.bookmarket.dao.ShoppingCartDaoImpl;
import pers.mmc.bookmarket.entity.CartItem;

public class ShoppingCarttService {

	ShoppingCartDao dao = new ShoppingCartDaoImpl();
	
	public boolean addItem(int userId,int bookId,int quantity){
		CartItem item = new CartItem();
		item.setUserId(userId);
		item.setBookId(bookId);
		item.setQuantity(quantity);
		return dao.addItem(item);
	}
	
	public boolean addItems(List<CartItem> list,int userId){
		dao.deleteItemByUserId(userId);
		return dao.addItems(list);
	}
	
 	public boolean deleteItem(int id){
		return dao.deleteItemByUserId(id);
	}
	public List<CartItem> queryItemsByUserId(int id){
		return dao.queryItemsByUserId(id);
	}
	
	public boolean updateItem(int userId,int bookId,int quantity){
		CartItem item = new CartItem();
		item.setUserId(userId);
		item.setBookId(bookId);
		item.setQuantity(quantity);
		return dao.updateItem(item);
	}
}
