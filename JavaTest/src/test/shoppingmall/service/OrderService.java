package test.shoppingmall.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import test.shoppingmall.ConnectionManager;
import test.shoppingmall.dao.CartDao;
import test.shoppingmall.dao.OrderDao;
import test.shoppingmall.dao.OrderItemDao;
import test.shoppingmall.vo.Cart;
import test.shoppingmall.vo.Order;
import test.shoppingmall.vo.OrderItem;

public class OrderService {

	public void cartToOrder(String memberId) {
		
	Connection conn = null;
	
	try {
		
		conn=ConnectionManager.getConnection();
		
		//transaction start (from cart to order);
		
		conn.setAutoCommit(false);
		
		
			// selectAll from cart
		
			CartDao cartDao = new CartDao(conn);
			OrderItemDao orderitemDao = new OrderItemDao(conn);
			OrderItem orderitem=new OrderItem();
			
			List<Cart> list =cartDao.selectByMemberId(memberId);
			
			int total = 0; //total orderPrice
			
			for(Cart cart:list) {
				total += cart.getCartPrice()*cart.getCartCount();
			}
			
			Order order = new Order();
			order.setMemberId(memberId);
			order.setOrderPrice(total);
			OrderDao orderDao = new OrderDao (conn);
			Integer orderNo=orderDao.insert(order);
			
			for(Cart cart:list) {
				orderitem.setOrderItemCount(cart.getCartCount());
				orderitem.setOrderItemPrice(cart.getCartPrice());
				orderitem.setProductNo(cart.getProductNo());
				orderitem.setProductName(cart.getProductName());
				orderitem.setOrderNo(orderNo);
				orderitemDao.insert(orderitem);
			}
			
			
			// delete in cart
			cartDao.deleteAll(memberId);
			
			conn.commit();
			conn.close();
		
		
	} catch (Exception e) {
		try{
			conn.rollback();
		}catch (SQLException e1){
		}
		System.out.println("cart to order fail");
		e.printStackTrace();
	}finally{
		try{
			conn.close();
		}catch(Exception e){
			
		}
	}
	

	
	}
}
