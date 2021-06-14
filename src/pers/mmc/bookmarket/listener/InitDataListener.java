package pers.mmc.bookmarket.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import pers.mmc.bookmarket.entity.BookQParam;
import pers.mmc.bookmarket.service.BookService;
import pers.mmc.bookmarket.service.BookTypeService;

/**
 * Application Lifecycle Listener implementation class InitDataListener
 *
 */
@WebListener
public class InitDataListener implements ServletContextListener {

	BookService service;
	BookTypeService typeService;
    /**
     * Default constructor. 
     */
    public InitDataListener() {
    	service=new BookService();
    	typeService = new BookTypeService();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent param)  {
    	ServletContext context = param.getServletContext();
    	context.setAttribute("newBooks", service.queryBooksByParam(new BookQParam(1,8L)));
    	context.setAttribute("newBooksRank", service.queryBooksByParam(new BookQParam(10,10L)));
    	context.setAttribute("books", service.queryBooksByParam(new BookQParam(20,8L)));
    	context.setAttribute("booksRank", service.queryBooksByParam(new BookQParam(30,10L)));
    	context.setAttribute("bookTypes", typeService.queryAllBookType());
    }
	
}
