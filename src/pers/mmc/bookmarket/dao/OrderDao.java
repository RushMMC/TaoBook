package pers.mmc.bookmarket.dao;

import java.util.List;

import pers.mmc.bookmarket.entity.OrderDetail;
import pers.mmc.bookmarket.entity.OrderItem;

public interface OrderDao {

	boolean addItem(OrderItem item);
	boolean deleteItemByUserId(int id);
	List<OrderItem> queryItemsByUserId(int id);
	boolean updateItem(OrderItem item);
	boolean deleteItemsByUserId(int userId);
	boolean addItems(List<OrderItem> list);
	List<OrderDetail> queryOrderByUserId(int id);
	boolean upDateOrderPayState(List<Integer> idList);
}
