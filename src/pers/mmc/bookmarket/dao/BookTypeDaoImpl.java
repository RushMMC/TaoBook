package pers.mmc.bookmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import pers.mmc.bookmarket.entity.BookType;
import pers.mmc.bookmarket.util.DBCPUtil;

public class BookTypeDaoImpl implements BookTypeDao{

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public boolean addType(BookType type) {
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn
					.prepareStatement("insert into type(typename) values(?)");
			pstmt.setString(1, type.getTypename());
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return false;
	}

	@Override
	public List<BookType> queryAllBookType() {
		String sql = "SELECT * FROM type";
		List<BookType> list = null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new BeanListHandler<>(BookType.class).handle(rs);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public boolean updateBookType(BookType type) {
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement("update type set typename=? where id=?");
			pstmt.setString(1, type.getTypename());
			pstmt.setInt(2, type.getId());
			return pstmt.executeUpdate()>0;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return false;
	}

	@Override
	public BookType queryBookType(int id) {
		BookType type=null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement("select * from type where id=?");
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			type=new BeanHandler<>(BookType.class).handle(rs);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return type;
	}

}
