package pers.mmc.bookmarket.entity;

import java.sql.Date;

public class BookQParam {

	private String title;
	private String author;
	private String isbn;
	private Float price;
	private Integer type;
	private Boolean isSell;
	private Date createDate;
	private Date updateDate;
	private Integer start;
	private Long offset;
	private boolean isWhere;

	public BookQParam() {
	}

	public BookQParam(Integer start, Long offset) {
		this.start = start;
		this.offset = offset;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title==null||"".equals(title)) {
			this.title = null;
		} else {
			this.title = title;
			isWhere = true;
		}
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		if (isbn==null||"".equals(isbn)) {
			this.isbn = null;
		} else {
			this.isbn = isbn;
			isWhere = true;
		}
	}

	
	public void setAuthor(String author) {
		if (author==null||"".equals(title)) {
			this.author = null;
		} else {
			this.author = author;
			isWhere = true;
		}
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		isWhere = true;
		this.price = price;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		isWhere=true;
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		isWhere = true;
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		isWhere = true;
		this.updateDate = updateDate;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public boolean isWhere() {
		return isWhere;
	}

	
	public Boolean getIsSell() {
		return isSell;
	}

	public void setIsSell(Boolean isSell) {
		isWhere = true;
		this.isSell = isSell;
	}


}
