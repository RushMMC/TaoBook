package pers.mmc.bookmarket.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import pers.mmc.bookmarket.entity.Book;
import pers.mmc.bookmarket.entity.BookQParam;
import pers.mmc.bookmarket.util.DBCPUtil;

public class BookDaoImpl implements BookDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public boolean addBook(Book book) {
		try {
			conn = DBCPUtil.getConnection();
			String sql = "INSERT INTO book (title,author,price,imgPath,isbn,quantity,type) VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setFloat(3, book.getPrice());
			pstmt.setString(4, book.getImgPath());
			pstmt.setString(5, book.getIsbn());
			pstmt.setInt(6, book.getQuantity());
			pstmt.setInt(7, book.getType());
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
	public Book queryBookById(int id) {
		String sql = "SELECT * FROM book WHERE id =?";
		Book book = null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			book = new BeanHandler<>(Book.class).handle(rs);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return book;
	}

	@Override
	public List<Book> queryAllBook() {
		String sql = "SELECT * FROM book";
		List<Book> list = null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new BeanListHandler<>(Book.class).handle(rs);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public boolean deleteBookById(int id) {
		String sql = "DELETE FROM book WHERE id=?";
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt);
		}
		return false;
	}

	@Override
	public List<Book> queryBooksByBook(BookQParam param) {
		String sql = createSql("SELECT * FROM book ",param);
		System.out.println(sql);
		List<Book> list = null;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			setSqlValue(pstmt, param);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			list = new BeanListHandler<>(Book.class).handle(rs);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		return list;
	}

	public int queryBooksTotalByParam(BookQParam param){
		long offset=param.getOffset();
		int start=param.getStart();
		param.setOffset(Long.MAX_VALUE);
		param.setStart(0);
		String sql = createSql("SELECT count(*) FROM book ",param);
		int total=0;
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			setSqlValue(pstmt, param);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				total=rs.getInt(1);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt, rs);
		}
		param.setOffset(offset);
		param.setStart(start);
		return total;
	}
	
	private void setSqlValue(PreparedStatement ps, BookQParam param) {
		int index = 1;
		try {
			if (param.getTitle() != null) {
				ps.setString(index++,'%'+param.getTitle()+'%');
			}
			if (param.getAuthor() != null) {
				ps.setString(index++, '%'+param.getAuthor()+'%');
			}
			if (param.getIsbn() != null) {
				ps.setString(index++, '%'+param.getIsbn()+'%');
			}
			if (param.getPrice() != null) {
				ps.setFloat(index++, param.getPrice());
			}
			if (param.getType() != null) {
				ps.setInt(index++, param.getType());
			}
			if (param.getCreateDate() != null) {
				ps.setDate(index++, param.getCreateDate());
			}
			if (param.getUpdateDate() != null) {
				ps.setDate(index++, param.getUpdateDate());
			}
			ps.setInt(index++, param.getStart() == null ? 0 : param.getStart());
			if (param.getOffset() != null) {
				ps.setLong(index, param.getOffset());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String createSql(String sql,BookQParam param) {
		boolean isFirst = false;
		if (param.isWhere()) {
			sql += "where ";
		}
		if (param.getTitle() != null) {
			isFirst = true;
			sql += "title like ? ";
		}
		if (param.getAuthor() != null) {
			if (isFirst) {
				sql += "and ";
			}
			sql += "author like ? ";
			isFirst = true;
		}
		if (param.getIsbn() != null) {
			if (isFirst) {
				sql += "and ";
			}
			sql += "isbn like ? ";
			isFirst = true;
		}
		if (param.getPrice() != null) {
			if (isFirst) {
				sql += "and ";
			}
			sql += "price=? ";
			isFirst = true;
		}
		if (param.getType() != null) {
			if (isFirst) {
				sql += "and ";
			}
			sql += "type=? ";
			isFirst = true;
		}
		if (param.getCreateDate() != null) {
			if (isFirst) {
				sql += "and ";
			}
			sql += "createDate = ? ";
			isFirst = true;
		}
		if (param.getUpdateDate() != null) {
			if (isFirst) {
				sql += "and ";
			}
			sql += "updateDate = ? ";
			isFirst = true;
		}
		sql += "limit ?";
		if (param.getOffset() != null) {
			sql += ",?";
		}
		return sql;
	}

	
	@Override
	public boolean updateBook(Book book) {
		try {
			conn = DBCPUtil.getConnection();
			String sql = "update book set title=?,author=?,price=?,imgPath=?,isbn=?,quantity=?,type=?,isSell=?,updateDate=? where id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setFloat(3, book.getPrice());
			pstmt.setString(4, book.getImgPath());
			pstmt.setString(5, book.getIsbn());
			pstmt.setInt(6, book.getQuantity());
			pstmt.setInt(7, book.getType());
			pstmt.setBoolean(8, book.getIsSell());
			pstmt.setDate(9, new Date(System.currentTimeMillis()));
			pstmt.setInt(10, book.getId());
			System.out.println(pstmt);
			int count = pstmt.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.release(conn, pstmt);
		}
		return false;
	}

}
