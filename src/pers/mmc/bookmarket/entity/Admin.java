package pers.mmc.bookmarket.entity;

public class Admin {
	private Integer id;
	private String name;
	private String password;
	private String telephone;

	public Admin() {
		super();
	}

	public Admin(String name, String password, String telephone) {
		super();
		this.name = name;
		this.password = password;
		this.telephone = telephone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
