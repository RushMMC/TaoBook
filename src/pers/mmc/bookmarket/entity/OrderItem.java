package pers.mmc.bookmarket.entity;

public class OrderItem {

	private Integer id;
	private Integer userId;
	private Integer bookId;
	private Integer quantity;
	private Boolean isPaid;
	
	public OrderItem() {
		super();
	}
	public OrderItem(Integer id, Integer userId, Integer bookId, Integer quantity) {
		super();
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.quantity = quantity;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Boolean getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}
	
}
