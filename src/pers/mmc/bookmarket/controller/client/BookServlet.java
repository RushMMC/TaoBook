package pers.mmc.bookmarket.controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.mmc.bookmarket.entity.Book;
import pers.mmc.bookmarket.service.BookService;

@WebServlet(name="BookServlet",value={"/detailPage.do"})
public class BookServlet extends HttpServlet{

	private static final long serialVersionUID = -4771047501781828984L;
	BookService service=new BookService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idStr=req.getParameter("id");
		if(idStr!=null&&!"".equals(idStr)){
			int id=Integer.parseInt(idStr);
			Book book = service.queryBookById(id);
			if(book!=null){
				req.setAttribute("book", book);
				req.getRequestDispatcher("client/jsp/detailPage.jsp").forward(req, resp);
			}else{
				req.setAttribute("message", "该书已下架");
				req.getRequestDispatcher("client/jsp/error.jsp").forward(req, resp);
			}
		}else{
			req.setAttribute("message", "参数错误");
			req.getRequestDispatcher("client/jsp/error.jsp").forward(req, resp);
		}
	}

}
