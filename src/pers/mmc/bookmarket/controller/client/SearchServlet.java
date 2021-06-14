package pers.mmc.bookmarket.controller.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.mmc.bookmarket.entity.Book;
import pers.mmc.bookmarket.entity.BookQParam;
import pers.mmc.bookmarket.service.BookService;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name="/SearchServlet",value={"/search.do","/request.do"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    BookService service = new BookService();
    
    public SearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String price = request.getParameter("price");
		String start = request.getParameter("start");
		String offset = request.getParameter("offset");
		String type = request.getParameter("type");
		BookQParam param = new BookQParam();
		param.setTitle(title);
		param.setAuthor(author);
		if(price!=null&&!"".equals(price)){
			param.setPrice(Float.parseFloat(price));
		}
		if(type!=null&&!"".equals(type)){
			param.setType(Integer.parseInt(type));
		}
		if(start!=null&&!"".equals(start)){
			param.setStart((Integer.parseInt(start)-1)*15);
		}else{
			param.setStart(0);
		}
		System.out.println(param.getStart());
		if(offset!=null&&!"".equals(offset)){
			param.setOffset(Long.parseLong(offset));
		}else{
			param.setOffset(15L);
		}
		int total = service.queryBooksTotalByParam(param);
		List<Book> queryBooksByParam = service.queryBooksByParam(param);
		String servletPath = request.getServletPath();
		if("/search.do".equals(servletPath)) {
			request.setAttribute("searchBooks", queryBooksByParam);
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
			request.getRequestDispatcher("client/jsp/searchResult.jsp").forward(request, response);
		}else if("/request.do".equals(servletPath)){
			String json = JSON.toJSONString(queryBooksByParam);
			response.getWriter().write(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
