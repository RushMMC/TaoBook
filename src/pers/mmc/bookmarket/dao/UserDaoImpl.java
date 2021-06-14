package pers.mmc.bookmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import pers.mmc.bookmarket.entity.User;
import pers.mmc.bookmarket.util.DBCPUtil;

public class UserDaoImpl implements UserDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public boolean addUser(User user) {
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn
					.prepareStatement("insert into user(username,password,telephone) values(?,?,?)");
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getTelephone());
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return false;
	}

	@Override
	public User findUserById(int id) {
		User user = null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement("select * from user where id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			user = new BeanHandler<>(User.class).handle(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return user;
	}

	@Override
	public User findUserByName(String username) {
		User user = null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn
					.prepareStatement("select * from user where username=?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			user = new BeanHandler<>(User.class).handle(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return user;
	}

}
