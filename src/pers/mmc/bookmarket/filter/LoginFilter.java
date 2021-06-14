package pers.mmc.bookmarket.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.mmc.bookmarket.entity.User;
import pers.mmc.bookmarket.service.UserService;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(filterName="LoginFilter",value={"*.do"})
public class LoginFilter implements Filter {

	UserService service;
    /**
     * Default constructor. 
     */
    public LoginFilter() {
    	service=new UserService();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		System.out.println("登录过滤器");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Object name = httpRequest.getSession().getAttribute("username");
		Cookie[] cookies = httpRequest.getCookies();
		String username=null,password=null;
		boolean isRedirect=true;
		// 如果获取到用户名，说明用户已经登录成功，不重定向
		if(name!=null){
			isRedirect=false;
		}else{
			// 如果session获取不到用户名，且cookie不为空，则判断是否自动登录
			if(cookies!=null){
				for (Cookie cookie : cookies) {
					if("username".equals(cookie.getName())){
						username=cookie.getValue();
					}
					if("password".equals(cookie.getName())){
						password=cookie.getValue();
					}
				}
				// 是自动登录，判断，用户名和密码是否正确，根据判断结果决定是否重定向
				if (username!=null && password!=null) {
					isRedirect = !service.login(new User(username, password, ""));
					if (!isRedirect) {
						httpRequest.getSession().setAttribute("username", username);
					}
				}
			}
		}
		if (isRedirect) {
//			System.out.println("重新登录");
			httpResponse.sendRedirect(request.getServletContext().getContextPath()+"/client/jsp/login.jsp");
		}else{
//			System.out.println("传递");
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {}

}
