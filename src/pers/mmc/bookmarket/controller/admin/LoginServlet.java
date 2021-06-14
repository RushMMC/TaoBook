package pers.mmc.bookmarket.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.mmc.bookmarket.entity.Admin;
import pers.mmc.bookmarket.service.AdminService;

@WebServlet(name="AdminLoginServlet",value={"/admin/login","/admin/logout.ado"})
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = -9011081023387286302L;
	AdminService service=new AdminService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    	String requestURI = req.getServletPath();
    	System.out.println(requestURI);
		if("/admin/logout.ado".equals(requestURI)) {
			req.getSession().invalidate();
			Cookie nameCookie = new Cookie("adname", "");
			Cookie passCookie = new Cookie("adpass", "");
			nameCookie.setMaxAge(0);
			passCookie.setMaxAge(0);
			resp.addCookie(nameCookie);
			resp.addCookie(passCookie);
			req.getSession().removeAttribute("adname");
			req.getSession().removeAttribute("adpass");
			resp.sendRedirect(getServletContext().getContextPath()+"/admin/jsp/index.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("adname");
		String password= req.getParameter("adpass");
		String autoStr = req.getParameter("autologin");
		boolean login = service.login(new Admin(username, password, ""));
		if(login){
			if(autoStr!=null &&"true".equals(autoStr)){
				Cookie nameCookie = new Cookie("adname", username);
				Cookie passCookie = new Cookie("adpass", password);
				nameCookie.setMaxAge(3600*24*7);
				passCookie.setMaxAge(3600*24*7);
				resp.addCookie(nameCookie);
				resp.addCookie(passCookie);
			}
			req.getSession().setAttribute("adname", username);
			req.getSession().setAttribute("adminId", service.queryAdminByName(username).getId());
			System.out.println("登录成功");
			resp.sendRedirect("jsp/index.jsp");
		}else{
			System.out.println("登录失败");
			req.setAttribute("message", "用户名或密码错误");
			req.getRequestDispatcher("jsp/login.jsp").forward(req,resp);
		}
	}
}
