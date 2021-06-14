package pers.mmc.bookmarket.controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.mmc.bookmarket.entity.User;
import pers.mmc.bookmarket.service.UserService;

@WebServlet(name="LoginServlet",value={"/login","/logout.do"})
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = -9011081023387286302L;
	UserService service=new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    	String requestURI = req.getServletPath();
    	System.out.println(requestURI);
		if("/logout.do".equals(requestURI)) {
			req.getSession().invalidate();
			Cookie nameCookie = new Cookie("username", "");
			Cookie passCookie = new Cookie("password", "");
			nameCookie.setMaxAge(0);
			passCookie.setMaxAge(0);
			resp.addCookie(nameCookie);
			resp.addCookie(passCookie);
			req.getSession().removeAttribute("username");
			req.getSession().removeAttribute("userId");
			resp.sendRedirect(getServletContext().getContextPath()+"/client/jsp/index.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password= req.getParameter("password");
		String autoStr = req.getParameter("autologin");
		System.out.println(username);
		boolean login = service.login(new User(username, password, ""));
		if(login){
			if(autoStr!=null &&"true".equals(autoStr)){
				Cookie nameCookie = new Cookie("username", username);
				Cookie passCookie = new Cookie("password", password);
				nameCookie.setMaxAge(3600*24*7);
				passCookie.setMaxAge(3600*24*7);
				resp.addCookie(nameCookie);
				resp.addCookie(passCookie);
			}
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("userId", service.findUserByUserName(username).getId());
			System.out.println("登录成功");
			resp.sendRedirect("client/jsp/index.jsp");
		}else{
			System.out.println("登录失败");
			req.setAttribute("message", "用户名或密码错误");
			req.getRequestDispatcher("client/jsp/login.jsp").forward(req,resp);
		}
	}
}
