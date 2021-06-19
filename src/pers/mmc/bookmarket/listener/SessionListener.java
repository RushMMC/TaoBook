package pers.mmc.bookmarket.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import pers.mmc.bookmarket.entity.OrderItem;
import pers.mmc.bookmarket.entity.GoodsItem;
import pers.mmc.bookmarket.entity.ShoppingCart;
import pers.mmc.bookmarket.service.OrderService;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {

	private OrderService cartService = new OrderService();
	public SessionListener() {
    }

    public void sessionCreated(HttpSessionEvent se)  { 		
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	//在session被摧毁之前保存购物车的信息
    	HttpSession session = se.getSession();
    	int id = (int) session.getAttribute("userId");
    	ShoppingCart card = (ShoppingCart) session.getAttribute("cart");
    	List<OrderItem> list=new ArrayList<>();
    	if(card!=null){
    		for (GoodsItem item:card.getItems()) {
    			list.add(new OrderItem(null, id, item.getBook().getId(), item.getQuantity()));
    		}
    		cartService.addItems(list,id);
    	}
    }
	
}
