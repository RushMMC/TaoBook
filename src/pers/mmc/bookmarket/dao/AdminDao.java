package pers.mmc.bookmarket.dao;

import java.util.List;

import pers.mmc.bookmarket.entity.Admin;

public interface AdminDao {

	boolean addAdmin(Admin admin);

	Admin queryAdminById(int id);

	List<Admin> queryAllAdmins();

	boolean deleteAdminById(int id);
	
	boolean updateAdmin(Admin admin);

	Admin queryAdminByName(String name);
	
}
