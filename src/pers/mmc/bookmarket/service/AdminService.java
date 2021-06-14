package pers.mmc.bookmarket.service;

import java.util.List;

import pers.mmc.bookmarket.dao.AdminDao;
import pers.mmc.bookmarket.dao.AdminDaoImpl;
import pers.mmc.bookmarket.entity.Admin;

public class AdminService {
	AdminDao dao = new AdminDaoImpl();
	public boolean addAdmin(Admin admin){
		return dao.addAdmin(admin);
	}

	public Admin queryAdminById(int id){
		return dao.queryAdminById(id);
	}

	public List<Admin> queryAllAdmins(){
		return dao.queryAllAdmins();
	}

	public boolean deleteAdminById(int id){
		return dao.deleteAdminById(id);
	}
	
	public boolean updateAdmin(Admin admin){
		return dao.updateAdmin(admin);
	}

	public boolean login(Admin admin){
		Admin user2 = dao.queryAdminByName(admin.getName());
		if(user2!=null && user2.getPassword().equals(admin.getPassword())){
			return true;
		}
		return false;
	}

	public Admin queryAdminByName(String name) {
		return dao.queryAdminByName(name);
	}

	
	public boolean validateUserName(String name) {
		return dao.queryAdminByName(name)!=null;
	}
}
