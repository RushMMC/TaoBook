package pers.mmc.bookmarket.dao;

import java.util.List;

import pers.mmc.bookmarket.entity.CartItem;

public interface ShoppingCartDao {

	boolean addItem(CartItem item);
	boolean deleteItemByUserId(int id);
	List<CartItem> queryItemsByUserId(int id);
	boolean updateItem(CartItem item);
	boolean deleteItemsByUserId(int userId);
	boolean addItems(List<CartItem> list);
}
