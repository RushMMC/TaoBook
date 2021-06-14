package pers.mmc.bookmarket.controller.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pers.mmc.bookmarket.entity.Book;
import pers.mmc.bookmarket.entity.CartItem;
import pers.mmc.bookmarket.entity.GoodsItem;
import pers.mmc.bookmarket.entity.ShoppingCart;
import pers.mmc.bookmarket.service.BookService;
import pers.mmc.bookmarket.service.ShoppingCarttService;

@WebServlet(name = "ShoppingCartServlet", urlPatterns = { "/addToCart.do",
		"/viewProductDetails.do", "/deleteItem.do", "/showShoppingCart.do",
		"/clearShoppingCart.do" })
public class ShoppingCartServlet extends HttpServlet {

	private static final long serialVersionUID = 5504970214885302771L;
	BookService bookService = new BookService();
	ShoppingCarttService cartService = new ShoppingCarttService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if ("/viewProductDetails.do".equals(path)) {
			showProductDetails(request, response);
		} else if ("/deleteItem.do".equals(path)) {
			deleteItem(request, response);
		} else if ("/showShoppingCart.do".equals(path)) {
			showBooks(request, response);
		} else if ("/clearShoppingCart.do".equals(path)) {
			boolean result=true;
			HttpSession session = request.getSession();
			try{
				cartService.addItems(null,(int) session.getAttribute("userId"));
				ShoppingCart cart=(ShoppingCart) session.getAttribute("cart");
				cart.clear();
			}catch(Exception e){
				result=false;
			}
			response.getWriter().write(result?"购买成功":"购买失败");
		}
	}

	private void showBooks(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("userId");
		List<CartItem> items = cartService.queryItemsByUserId(id);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		for (CartItem cartItem : items) {
			cart.add(new GoodsItem(bookService.queryBookById(cartItem
					.getBookId()), cartItem.getQuantity()));
		}
		response.sendRedirect("client/jsp/shoppingCart.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将一件商品放入购物车
		int bookId = 0;
		int quantity = 0;
		String message = "添加失败";
		try {
			bookId = Integer.parseInt(request.getParameter("id"));
			quantity = Integer.parseInt(request.getParameter("quantity"));
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
		Book product = bookService.queryBookById(bookId);
		if (product != null) {
			HttpSession session = request.getSession();
			ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
			GoodsItem goodsItem = new GoodsItem(product, quantity);
			if (cart == null) {
				cart = new ShoppingCart();
				session.setAttribute("cart", cart);
			}
			if (cart.add(goodsItem)) {
				message = "添加成功";
			} else {
				message = "库存不足";
			}
		}
		response.getWriter().write(message);
	}

	// 显示商品细节并可添加到购物车
	private void showProductDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		int bookId = 0;
		try {
			bookId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			System.out.println(bookId);
			System.out.println(e);
		}
		// 根据商品号返回商品对象
		Book product = bookService.queryBookById(bookId);
		if (product != null) {
			HttpSession session = request.getSession();
			session.setAttribute("product", product);
			response.sendRedirect("showProduct.jsp");
		} else {
			// out.println("No product found");
		}
	}

	// 从购物车中删除一件商品
	private void deleteItem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			GoodsItem item = null;
			for (GoodsItem shopItem : cart.getItems()) {
				if (shopItem.getBook().getId() == id) {
					item = shopItem;
					break;
				}
			}
			cart.remove(item.getBook().getId());
		} catch (NumberFormatException e) {
			System.out.println("发生异常：" + e.getMessage());
		}
		response.sendRedirect("client/jsp/shoppingCart.jsp");
	}

}
