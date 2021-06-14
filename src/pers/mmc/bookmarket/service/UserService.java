package pers.mmc.bookmarket.service;

import pers.mmc.bookmarket.dao.UserDao;
import pers.mmc.bookmarket.dao.UserDaoImpl;
import pers.mmc.bookmarket.entity.User;

public class UserService {
	
	UserDao userDao;
	
	public UserService(){
		userDao = new UserDaoImpl();
	}
	
	public boolean addUser(User user){
		return userDao.addUser(user);
	}
	
	public boolean login(User user){
		User user2 = userDao.findUserByName(user.getUsername());
		if(user2!=null && user2.getPassword().equals(user.getPassword())){
			return true;
		}
		return false;
	}
	
	public boolean validateUserName(String username){
		return userDao.findUserByName(username)!=null;
	}
	
	public User findUserByUserName(String username){
		return userDao.findUserByName(username);
	}
}
