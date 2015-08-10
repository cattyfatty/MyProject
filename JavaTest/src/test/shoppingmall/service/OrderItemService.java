package test.shoppingmall.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.shoppingmall.ConnectionManager;
import test.shoppingmall.dao.OrderItemDao;
import test.shoppingmall.dao.ProductDao;
import test.shoppingmall.vo.OrderItem;
import test.shoppingmall.vo.Product;



public class OrderItemService {
	
	
	public void ShowDetail(int orderNo) {

		try {
			Connection conn = ConnectionManager.getConnection();
			OrderItemDao orderitemDao = new OrderItemDao(conn);
			List<OrderItem> list = new ArrayList<OrderItem>();
			list = orderitemDao.selectByorderNo(orderNo);
			if (list != null) {
				for (OrderItem orderitem : list) {
					System.out.print("주문번호: " + orderitem.getOrderNo());
					System.out.print("상품번호: " + orderitem.getProductNo());
					System.out.print("상품가격: " + orderitem.getOrderItemPrice());
					System.out.print("상품수량: " + orderitem.getOrderItemCount());
					System.out.println("상품이름: " + orderitem.getProductName());
				}
			} else {
				System.out.println("번호를 잘못 입력 하였습니다.");
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void ShowProduct() {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			ProductDao ProductDao = new ProductDao(conn);
			List<Product> list = new ArrayList<Product>();
			list = ProductDao.selectAll();
			if(list!=null){
				for(Product product : list){
					System.out.println("상품번호 :" + product.getpNo());
					System.out.println("상품이름 :" + product.getpName());
					System.out.println("상품가격 :" + product.getpPrice());
				}
			}else{
				System.out.println("상품이 없습니다.");
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
