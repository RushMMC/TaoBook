package pers.mmc.bookmarket.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.mmc.bookmarket.entity.OrderDetail;
import pers.mmc.bookmarket.service.OrderService;

@WebServlet(name="OrderServlet",value={"/admin/queryOrder.ado","/admin/deleteOrder.ado"})
public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 6125011062201967855L;

	OrderService service = new OrderService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if("/admin/deleteOrder.ado".equals(path)){
			String idStr = request.getParameter("userId");
			if(idStr!=null&& !"".equals(idStr)){
				boolean deleteItem = service.deleteItem(Integer.parseInt(idStr));
				if(deleteItem){
					response.getWriter().write("alert('删除成功')");
				}
			}
		}
	}



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if("/admin/queryOrder.ado".equals(path)){
			String idStr = request.getParameter("userId");
			if(idStr!=null&& !"".equals(idStr)){
				List<OrderDetail> orders = service.queryOrderByUserId(Integer.parseInt(idStr));
				request.setAttribute("orders",orders);
				request.getRequestDispatcher("jsp/queryOrder.jsp").forward(request, response);
			}
		}else if("/admin/deleteOrder.ado".equals(path)){
			String idStr = request.getParameter("userId");
			if(idStr!=null&& !"".equals(idStr)){
				boolean deleteItem = service.deleteItem(Integer.parseInt(idStr));
				if(deleteItem){
					response.getWriter().write("alert('删除成功')");
				}
			}
		}
	}

}
