package pers.mmc.bookmarket.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.mmc.bookmarket.entity.Admin;
import pers.mmc.bookmarket.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(name="/AdminServlet",value="/admin/addAdmin.ado")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminService service = new AdminService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String requestURI = request.getServletPath();
		String message;
		if ("/admin/addAdmin.ado".equals(requestURI)) {
			String username = request.getParameter("adname");
			String password=request.getParameter("adpass");
			String telephone=request.getParameter("telephone");
			boolean validate = service.validateUserName(username);
			if(validate){
				message="用户名已存在";
				request.setAttribute("message", message);
				request.getRequestDispatcher("jsp/addAdmin.jsp").forward(request,response);
			}else{
				boolean addUser = service.addAdmin(new Admin(username, password, telephone));
				System.out.println(addUser);
				if(addUser){
					request.setAttribute("message", "添加成功");
					request.getRequestDispatcher("jsp/addAdmin.jsp").forward(request,response);
				}else{
					response.sendRedirect("jsp/error.jsp");
				}
			}
		}
	}

}
