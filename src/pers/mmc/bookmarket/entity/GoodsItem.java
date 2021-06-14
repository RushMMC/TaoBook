package pers.mmc.bookmarket.entity;

import java.io.Serializable;

public class GoodsItem implements Serializable {

	private static final long serialVersionUID = 2L;
	private Book book; // 商品信息
	private int quantity; // 商品数量

	public GoodsItem(Book book) {
		this.book = book;
		quantity = 1;
	}

	public GoodsItem(Book book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	}

	// 属性的getter方法和setter方法
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
