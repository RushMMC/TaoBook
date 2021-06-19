package pers.mmc.bookmarket.service;

import java.util.List;

import pers.mmc.bookmarket.dao.OrderDao;
import pers.mmc.bookmarket.dao.OrderDaoImpl;
import pers.mmc.bookmarket.entity.OrderDetail;
import pers.mmc.bookmarket.entity.OrderItem;

public class OrderService {

	OrderDao dao = new OrderDaoImpl();
	
	public boolean addItem(int userId,int bookId,int quantity){
		OrderItem item = new OrderItem();
		item.setUserId(userId);
		item.setBookId(bookId);
		item.setQuantity(quantity);
		return dao.addItem(item);
	}
	
	public boolean addItems(List<OrderItem> list,int userId){
		dao.deleteItemByUserId(userId);
		return dao.addItems(list);
	}
	
 	public boolean deleteItem(int id){
		return dao.deleteItemByUserId(id);
	}
	public List<OrderItem> queryItemsByUserId(int id){
		return dao.queryItemsByUserId(id);
	}
	
	public boolean upDateOrderPayState(List<Integer> idList){
		return dao.upDateOrderPayState(idList);
	}
	
	public List<OrderDetail> queryOrderByUserId(int id){
		return dao.queryOrderByUserId(id);
	}
	
	public boolean updateItem(int userId,int bookId,int quantity){
		OrderItem item = new OrderItem();
		item.setUserId(userId);
		item.setBookId(bookId);
		item.setQuantity(quantity);
		return dao.updateItem(item);
	}
}
