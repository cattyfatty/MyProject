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
					System.out.print("�ֹ���ȣ: " + orderitem.getOrderNo());
					System.out.print("��ǰ��ȣ: " + orderitem.getProductNo());
					System.out.print("��ǰ����: " + orderitem.getOrderItemPrice());
					System.out.print("��ǰ����: " + orderitem.getOrderItemCount());
					System.out.println("��ǰ�̸�: " + orderitem.getProductName());
				}
			} else {
				System.out.println("��ȣ�� �߸� �Է� �Ͽ����ϴ�.");
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
					System.out.println("��ǰ��ȣ :" + product.getpNo());
					System.out.println("��ǰ�̸� :" + product.getpName());
					System.out.println("��ǰ���� :" + product.getpPrice());
				}
			}else{
				System.out.println("��ǰ�� �����ϴ�.");
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
