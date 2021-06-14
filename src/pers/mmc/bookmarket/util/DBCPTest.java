package pers.mmc.bookmarket.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class DBCPTest {

	public String generateISBN(){
		String isbn="";
		Random r = new Random();
		for(int i=0;i<13;i++){
			isbn+=r.nextInt(9);
		}
		return isbn; 
	}
	
	static int[] total = {0, 10, 16, 14, 12, 15, 10, 13, 13, 21, 9, 15, 12, 12, 17, 23, 13, 11, 11, 8, 11, 19};
	
	public static String copyPic(String sou,int type){
		String path="category"+type+"/pic"+(++total[type])+".jpg";
		String des="C:/Users/Administrator/workspace/BookStore/WebContent/images/"+path;
//		File file = new File("C:/Users/Administrator/Desktop/images3/"+sou);
		File file = new File("C:/Users/Administrator/workspace/BookStore/WebContent/"+sou);
		File file2 = new File(des);
		if(!file2.getParentFile().exists()){
			file2.getParentFile().mkdirs();
		}
		try(InputStream is = new FileInputStream(file);OutputStream os = new FileOutputStream(file2)){
			byte[] buffer = new byte[1024];
			int has=0;
			while((has=is.read(buffer))!=-1){
				os.write(buffer, 0, has);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public void displosal() throws SQLException{
		Random random = new Random(System.currentTimeMillis());
		Connection conn = DBCPUtil.getConnection();
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(
				"select title,author,price,imgPath from book2");
		System.out.println(result.getRow());
		conn.setAutoCommit(false);
		int type=17;
		PreparedStatement statement2 = conn.prepareStatement(
				"insert into bookisbn (title,author,isbn,price,type,quantity,imgPath) values(?,?,?,?,?,?,?)");
		System.out.println(statement2);
		while(result.next()){
			type=random.nextInt(21)+1;
			statement2.setString(1, result.getString(1));
			statement2.setString(2, result.getString(2));
			statement2.setString(3, generateISBN());
			statement2.setFloat(4, result.getFloat(3));
			statement2.setInt(5, type);
			statement2.setInt(6, random.nextInt(200)+10);
			String path=copyPic(result.getString(4), type);
			statement2.setString(7, path);
			statement2.addBatch();
//			System.out.println(statement2);
		}
		statement2.executeBatch();
		conn.commit();
	}
	
	public static void main(String[] args) throws SQLException {
//		new DBCPTest().displosal();
//		System.out.println(Arrays.toString(total));
//		Connection conn = DBCPUtil.getConnection();
//		DatabaseMetaData metaData = conn.getMetaData();
//		System.out.format("URL=%s%nusername=%s%nDriverName=%s%n",
//				metaData.getURL(),metaData.getUserName(),metaData.getDriverName());
	}

}
