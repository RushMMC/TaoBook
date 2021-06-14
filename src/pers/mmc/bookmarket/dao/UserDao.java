package pers.mmc.bookmarket.dao;

import pers.mmc.bookmarket.entity.User;

public interface UserDao {
	
	boolean addUser(User user);
	
	User findUserById(int id);

	User findUserByName(String username);
	
}
