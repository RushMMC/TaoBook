package pers.mmc.bookmarket.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import pers.mmc.bookmarket.entity.Book;
import pers.mmc.bookmarket.entity.BookQParam;
import pers.mmc.bookmarket.service.BookService;

@MultipartConfig
@WebServlet(name="AdminBookServlet",
	value={"/admin/addBook.ado","/admin/queryBook.ado",
		"/admin/updateBook.ado","/admin/downBook.ado"})
public class BookServlet extends HttpServlet{

	private static final long serialVersionUID = -4771047501781828984L;
	BookService service=new BookService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println(path);
		if("/admin/updateBook.ado".equals(path)){
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			Book book = service.queryBookById(id);
			request.setAttribute("book", book);
			request.getRequestDispatcher("jsp/updateBook.jsp").forward(request, response);
		}else if("/admin/queryBook.ado".equals(path)){
			queryBook(request,response);
		}else if("/admin/downBook.ado".equals(path)){
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			Book book = service.queryBookById(id);
			book.setIsSell(false);
			service.updateBook(book);
			request.setAttribute("message", "下架成功");
			request.getRequestDispatcher("queryBook.ado").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if("/admin/addBook.ado".equals(path)){
			addBook(request, response);
		}else if("/admin/queryBook.ado".equals(path)){
			queryBook(request,response);
		}else if("/admin/updateBook.ado".equals(path)){
			updateBook(request, response);
		}
	}
		
	private void updateBook(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String idStr = request.getParameter("id");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String priceStr = request.getParameter("price");
		String typeStr = request.getParameter("type");
		String quantityStr = request.getParameter("quantity");
		String message="";
		float price = 0;
		int type = 0,quantity = 0,id = 0;
		try{
			if(idStr!=null){
				id=Integer.parseInt(idStr);
			}
			if(priceStr!=null){
				price=Float.parseFloat(priceStr);
			}
			if(typeStr!=null){
				type=Integer.parseInt(typeStr);
			}
			if(quantityStr!=null){
				quantity=Integer.parseInt(quantityStr);
			}
		}catch(NumberFormatException e){
			request.setAttribute("message", "参数错误");
			request.getRequestDispatcher("jsp/updateBook.jsp").forward(request, response);
			return ;
		}
		String imgPath="images/category"+type+"/"+System.currentTimeMillis()+".jpg";
		Part part = request.getPart("picture");
		String realPath = request.getServletContext().getRealPath("/");
		
		if(part.getSize()>1024*1024*2){
			message="图片大于2Mb，不能上传。";
		}else{
			Book old = service.queryBookById(id);
			System.out.println(old);
			Book book = new Book(title, author, isbn, price, quantity, type, old.getImgPath());
			book.setId(id);
			if(part.getSize()!=0){
				part.write(realPath+imgPath);
				book.setImgPath(imgPath);
				File file = new File(realPath+old.getImgPath());
				if(file.exists()){
					file.delete();
				}
			}
			if(service.updateBook(book)){
				message="更新成功";
				request.setAttribute("book", book);
			}else{
				request.setAttribute("book", old);
			}
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("jsp/updateBook.jsp").forward(request, response);
	}

	private void queryBook(HttpServletRequest request, HttpServletResponse resp)
			throws IOException, ServletException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String type = request.getParameter("type");
		String start = request.getParameter("start");
		String offset = request.getParameter("offset");
		BookQParam param = new BookQParam();
		param.setTitle(title);
		param.setAuthor(author);
		param.setIsbn(isbn);
		if(type!=null&&!"".equals(type)){
			param.setType(Integer.parseInt(type));
		}
		if(start!=null&&!"".equals(start)){
			param.setStart((Integer.parseInt(start)-1)*15);
		}else{
			param.setStart(0);
		}
		if(offset!=null&&!"".equals(offset)){
			param.setOffset(Long.parseLong(offset));
		}else{
			param.setOffset(15L);
		}
		int total = service.queryBooksTotalByParam(param);
		List<Book> queryBooksByParam = service.queryBooksByParam(param);
		request.setAttribute("queryBooks", queryBooksByParam);
		request.setAttribute("pageTotal", total/15+1);
		System.out.println(total/15+1);
		int[] pageList = new int[5];
		int page=param.getStart()/15-1;
		for (int i = 0; i < pageList.length; ) {
			if (page>0) {
				pageList[i++]=page++;
			}else{
				page++;
			}
		}
		System.out.println(Arrays.toString(pageList));
		request.setAttribute("param", param);
		request.setAttribute("pageList", pageList);
		request.setAttribute("currentPage", param.getStart()/15+1);
		System.out.println(request.getRequestURL().toString());
		request.setAttribute("queryUrl", request.getRequestURL().toString());
		request.getRequestDispatcher("jsp/queryBook.jsp").forward(request, resp);
	}

	private void addBook(HttpServletRequest request, HttpServletResponse resp)
			throws IOException, ServletException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String priceStr = request.getParameter("price");
		String typeStr = request.getParameter("type");
		String quantityStr = request.getParameter("quantity");
		float price = 0;
		int type = 0,quantity = 0;
		try{
			if(priceStr!=null){
				price=Float.parseFloat(priceStr);
			}
			if(typeStr!=null){
				type=Integer.parseInt(typeStr);
			}
			if(quantityStr!=null){
				quantity=Integer.parseInt(quantityStr);
			}
		}catch(NumberFormatException e){
			return ;
		}
		String imgPath="images/category"+type+"/"+System.currentTimeMillis()+".jpg";
		Part part = request.getPart("picture");
		String realPath = request.getServletContext().getRealPath("/");
		String message="";
		if(part.getSize()>1024*1024*2){
			message="图片大于2Mb，不能上传。";
		}else{
			part.write(realPath+imgPath);
			Book book = new Book(title, author, isbn, price, quantity, type, imgPath);
			if(service.addBook(book)){
				message="添加成功";
			}
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("jsp/addBook.jsp").forward(request, resp);
	}
	

}
