package pers.mmc.bookmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import pers.mmc.bookmarket.entity.Book;
import pers.mmc.bookmarket.entity.OrderDetail;
import pers.mmc.bookmarket.entity.OrderItem;
import pers.mmc.bookmarket.entity.User;
import pers.mmc.bookmarket.util.DBCPUtil;

public class OrderDaoImpl implements OrderDao{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public boolean addItem(OrderItem item) {
		try {
			conn = DBCPUtil.getConnection();
			String sql = "INSERT INTO `order` (userId,bookId,quantity) VALUES(?,?,?)";
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
			String sql = "delete from `order` where userId=?";
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
	public List<OrderItem> queryItemsByUserId(int id) {
		String sql = "SELECT * FROM `order` where userid=? and isPaid=0";
		List<OrderItem> list = null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			list = new BeanListHandler<>(OrderItem.class).handle(rs);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public boolean updateItem(OrderItem item) {
		try {
			conn = DBCPUtil.getConnection();
			String sql = "update `order` set quantity=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item.getQuantity());
			pstmt.setInt(2, item.getId());
			return pstmt.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt);
		}
		return false;
	}
	
	@Override
	public boolean upDateOrderPayState(List<Integer> idList){
		try {
			conn = DBCPUtil.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("update `order` set isPaid=1 where id in (");
			for(Integer id:idList){
				sb.append(id).append(',');
			}
			sb.delete(sb.length()-1, sb.length());
			sb.append(')');
			return conn.createStatement().executeUpdate(sb.toString())>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt);
		}
		return false;
	}
	
	@Override
	public boolean deleteItemsByUserId(int userId) {
		try {
			conn = DBCPUtil.getConnection();
			String sql = "delete from `order` where userid=?";
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
	public boolean addItems(List<OrderItem> list) {
		if(list==null||list.size()==0){
			return false;
		}
		try {
			conn = DBCPUtil.getConnection();
			String sql = "INSERT INTO `order` (userId,bookId,quantity) VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for (OrderItem item : list) {
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
	
	@Override
	public List<OrderDetail> queryOrderByUserId(int id) {
		String sql = "SELECT `order`.*,book.title FROM `order` join book on `order`.bookid=book.id where userid=?;";
		List<OrderDetail> list = new ArrayList<>();
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book=new Book();
				book.setId(rs.getInt(3));
				book.setTitle(rs.getString(6));
				OrderDetail order = new OrderDetail();
				order.setBook(book);
				order.setId(rs.getInt(1));
				User user = new User();
				user.setId(id);
				order.setUser(user);
				order.setQuantity(rs.getInt(4));
				order.setIsPaid(rs.getBoolean(5));
				list.add(order);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return list;
	}

}
