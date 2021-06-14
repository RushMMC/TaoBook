package pers.mmc.bookmarket.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import pers.mmc.bookmarket.entity.BookType;
import pers.mmc.bookmarket.service.BookTypeService;

/**
 * Servlet implementation class BookTypeServlet
 */
@WebServlet(name="/BookTypeServlet",value={"/admin/addBooktype.ado","/admin/queryBooktype.ado"})
public class BookTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	BookTypeService service = new BookTypeService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if ("/admin/queryBooktype.ado".equals(path)) {
			List<BookType> list = service.queryAllBookType();
			String jsonString = JSON.toJSONString(list);
			response.getWriter().write(jsonString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
