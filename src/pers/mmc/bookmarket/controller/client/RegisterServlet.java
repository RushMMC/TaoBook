package pers.mmc.bookmarket.controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.mmc.bookmarket.entity.User;
import pers.mmc.bookmarket.service.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name="RegisterServlet",
			value={"/register","/validateUsername"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService service;
	
    public RegisterServlet() {
        service=new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=utf-8");
    	String requestURI = request.getServletPath();
    	String username = request.getParameter("username");
    	String message;
		if("/validateUsername".equals(requestURI)) {
			boolean validate = service.validateUserName(username);
			if(validate){
				message="用户名已存在";
			}else{
				message="用户名可以使用";
			}
			response.getWriter().write(message);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String requestURI = request.getServletPath();
		String message;
		if ("/register".equals(requestURI)) {
			String username = request.getParameter("username");
			String password=request.getParameter("password");
			String telephone=request.getParameter("telephone");
			boolean validate = service.validateUserName(username);
			if(validate){
				message="用户名已存在";
				request.setAttribute("message", message);
				request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
			}else{
				boolean addUser = service.addUser(new User(username, password, telephone));
				System.out.println(addUser);
				if(addUser){
					response.getWriter().print("注册成功，3秒后跳到登录页");
					response.setHeader("refresh", "3;url=client/jsp/login.jsp");
				}else{
					response.sendRedirect("client/jsp/error.jsp");
				}
			}
		}
	}

}
