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
@WebServlet(name="/BookTypeServlet",
	value={"/admin/addBookType.ado","/admin/queryBookType.ado",
		"/admin/updateBookType.ado","/admin/showBookType.ado"})
public class BookTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	BookTypeService service = new BookTypeService();

	public BookTypeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if ("/admin/queryBookType.ado".equals(path)) {
			List<BookType> list = service.queryAllBookType();
			String jsonString = JSON.toJSONString(list);
			response.getWriter().write(jsonString);
		}else if("/admin/showBookType.ado".equals(path)){
			List<BookType> list = service.queryAllBookType();
			request.setAttribute("booktypes", list);
			request.getRequestDispatcher("jsp/showBookType.jsp").forward(request, response);
		}else if("/admin/updateBookType.ado".equals(path)){
			String idStr = request.getParameter("id");
			if(idStr!=null&& !"".equals(idStr)){
				BookType type = service.queryBookType(Integer.parseInt(idStr));
				request.setAttribute("type", type);
				request.getRequestDispatcher("jsp/updateBookType.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "参数错误");
				request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String message = "添加失败";
		if ("/admin/addBookType.ado".equals(path)) {
			String typename = request.getParameter("typename");
			boolean addType = service.addType(new BookType(0, typename));
			if (addType) {
				message="添加成功";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("jsp/addBookType.jsp").forward(request, response);
		}else if("/admin/updateBookType.ado".equals(path)){
			String idStr = request.getParameter("id");
			String typename = request.getParameter("typename");
			if(idStr!=null&& !"".equals(idStr)){
				BookType type = new BookType(Integer.parseInt(idStr), typename);
				if(service.updateType(type)){
					message="添加成功";
				}
				
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("jsp/updateBookType.jsp").forward(request, response);
		}
	}

}
