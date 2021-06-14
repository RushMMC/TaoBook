package pers.mmc.bookmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import pers.mmc.bookmarket.entity.CartItem;
import pers.mmc.bookmarket.util.DBCPUtil;

public class ShoppingCartDaoImpl implements ShoppingCartDao{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public boolean addItem(CartItem item) {
		try {
			conn = DBCPUtil.getConnection();
			String sql = "INSERT INTO shopping_cart (userId,bookId,quantity) VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item.getUserId());
			pstmt.setInt(2, item.getBookId());
			pstmt.setInt(3, item.getQuantity());
			int count = pstmt.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt);
		}
		return false;
	}

	@Override
	public boolean deleteItemByUserId(int userId) {
		try {
			conn = DBCPUtil.getConnection();
			String sql = "delete from shopping_cart where userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			int count = pstmt.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt);
		}
		return false;
	}

	@Override
	public List<CartItem> queryItemsByUserId(int id) {
		String sql = "SELECT * FROM shopping_cart where userid=?";
		List<CartItem> list = null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			list = new BeanListHandler<>(CartItem.class).handle(rs);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public boolean updateItem(CartItem item) {
		return false;
	}

	@Override
	public boolean deleteItemsByUserId(int userId) {
		try {
			conn = DBCPUtil.getConnection();
			String sql = "delete from shopping_cart where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			int count = pstmt.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt);
		}
		return false;
	}

	
	@Override
	public boolean addItems(List<CartItem> list) {
		if(list==null||list.size()==0){
			return false;
		}
		try {
			conn = DBCPUtil.getConnection();
			String sql = "INSERT INTO shopping_cart (userId,bookId,quantity) VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for (CartItem item : list) {
				pstmt.setInt(1, item.getUserId());
				pstmt.setInt(2, item.getBookId());
				pstmt.setInt(3, item.getQuantity());
				pstmt.addBatch();
			}
			return pstmt.executeBatch().length> 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt);
		}
		return false;
	}

}
