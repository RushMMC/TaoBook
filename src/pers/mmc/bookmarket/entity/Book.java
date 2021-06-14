package pers.mmc.bookmarket.entity;

import java.io.Serializable;
import java.sql.Date;

public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String author;
	private String isbn;
	private Float price;
	private Integer quantity;
	private Integer type;
	private Boolean isSell=false;
	private String imgPath;
	private Date createDate;
	private Date updateDate;
	
	public Book() {}

	public Book(String title, String author, Float price, String imgPath) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.imgPath = imgPath;
	}

	public Book(String title, String author, String isbn, Float price,
			Integer quantity, Integer type, String imgPath) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.quantity = quantity;
		this.type = type;
		this.imgPath = imgPath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsSell() {
		return isSell;
	}

	public void setIsSell(Boolean isSell) {
		this.isSell = isSell;
	}

}
