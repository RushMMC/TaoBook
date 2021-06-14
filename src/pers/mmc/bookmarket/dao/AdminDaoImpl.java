package pers.mmc.bookmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import pers.mmc.bookmarket.entity.Admin;
import pers.mmc.bookmarket.util.DBCPUtil;

public class AdminDaoImpl implements AdminDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	@Override
	public boolean addAdmin(Admin admin) {
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn
					.prepareStatement("insert into admin(name,password,telephone) values(?,?,?)");
			pstmt.setString(1, admin.getName());
			pstmt.setString(2, admin.getPassword());
			pstmt.setString(3, admin.getTelephone());
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return false;
	}

	@Override
	public Admin queryAdminById(int id) {
		Admin admin = null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement("select * from admin where id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			admin = new BeanHandler<>(Admin.class).handle(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return admin;
	}

	@Override
	public List<Admin> queryAllAdmins() {
		List<Admin> adminList =null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement("select * from admin");
			rs = pstmt.executeQuery();
			adminList = new BeanListHandler<>(Admin.class).handle(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return adminList;
	}

	@Override
	public boolean deleteAdminById(int id) {
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn
					.prepareStatement("delete from admin where id=?");
			pstmt.setInt(1, id);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return false;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement("update admin set password=?,telephone=? where id=?");
			pstmt.setString(1, admin.getPassword());
			pstmt.setString(2, admin.getTelephone());
			pstmt.setInt(3, admin.getId());
			return pstmt.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return false;
	}

	
	@Override
	public Admin queryAdminByName(String name) {
		Admin admin = null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement("select * from admin where name=?");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			admin = new BeanHandler<>(Admin.class).handle(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return admin;
	}

}
