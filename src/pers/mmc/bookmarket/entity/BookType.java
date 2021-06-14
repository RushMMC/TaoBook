package pers.mmc.bookmarket.entity;

public class BookType {

	private Integer id;
	private String typename;

	public BookType() {
	}

	public BookType(Integer id, String typename) {
		super();
		this.id = id;
		this.typename = typename;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

}
