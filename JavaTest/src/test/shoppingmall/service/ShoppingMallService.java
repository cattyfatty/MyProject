package test.shoppingmall.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import test.shoppingmall.ConnectionManager;
import test.shoppingmall.dao.CartDao;
import test.shoppingmall.dao.ProductDao;
import test.shoppingmall.vo.Cart;
import test.shoppingmall.vo.Member;
import test.shoppingmall.vo.Product;

public class ShoppingMallService {

	public void showCart(String memberId) {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			CartDao cartDao = new CartDao(conn);
			List<Cart> list = cartDao.selectAll(memberId);
			for (Cart cart : list) {
				System.out.print("��ǰ��ȣ : " + cart.getProductNo() + "\t");
				System.out.print("��ǰ�� : " + cart.getProductName() + "\t");
				System.out.print("���� : " + cart.getCartCount() + "\t");
				System.out.println("���� : " + cart.getCartPrice());
			}

		} catch (Exception e) {
			System.out.println("�������");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	public void insertCart(String memberId) {
		Connection conn = null;
		Scanner sc = new Scanner(System.in);
		try {
			conn = ConnectionManager.getConnection();
			// conn.setAutoCommit(false);
			CartDao cartDao = new CartDao(conn);
			ProductDao productDao = new ProductDao(conn);

			Cart cart = new Cart();

			// �ֹ���
			cart.setMemberId(memberId);
			List<Cart> list = cartDao.selectAll(memberId);
			List<Cart> pro = cartDao.selectProductNo(memberId);
			// �ֹ��� ��ǰ ��ȣ
			System.out.print("�ֹ��� ��ǰ ��ȣ : ");
			Integer productNo = Integer.parseInt(sc.nextLine());
			Product product = productDao.selectByProductNo(productNo);
			
			if (product != null) {
				cart.setProductNo(productNo);
			} else {
				System.out.println("��ǰ ����� �ٽú��ð� ��ǰ��ȣ�� �Է����ּ���");
				return; // return�� �ϴ��� finally�� ����ȴ�.
			}

			// �ֹ�����
			System.out.print("���� : ");
			Integer cartCount = Integer.parseInt(sc.nextLine());
			cart.setCartCount(cartCount);
			cart.setCartPrice(cartCount * product.getpPrice());
			
			
			//update�� �Ѿ��
			for(int i=0;i<pro.size();i++){
				if(productNo==cart.getProductNo())
					updateCart(memberId,productNo,cartCount);
				else{
					Integer pk = cartDao.insert(cart);
					System.out.println(pk + " �� ��ǰ ��ٱ��� ����");
				}
			}
		} catch (Exception e) {
			System.out.println("��Ȯ�� ��ǰ ��ȣ�� �Է����ּ���.");
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	public void updateCart(String memberId,int productNo, int count) {
		Connection conn = null;
		try {
			Cart cart = new Cart();
			conn = ConnectionManager.getConnection();
//			conn.setAutoCommit(false);
			CartDao cartDao = new CartDao(conn);
			int rows = cartDao.update(cart);
			
			if (rows == 1) {
				System.out.println(cart.getCartNo() + " �� ��ǰ�� �����Ǿ����ϴ�!");
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	public void deleteOneCart() {
		Connection conn = null;
		Scanner sc = new Scanner(System.in);
		try {
			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			CartDao cartDao = new CartDao(conn);
			System.out.print("������ ��ǰ��ȣ �Է� : ");
			int productNo = Integer.parseInt(sc.nextLine());
			String memberId = sc.nextLine();
			int rows = cartDao.deleteOne(productNo, memberId);
			if (rows == 1) {
				System.out.println(productNo + " �� ��ǰ�� �����Ǿ����ϴ�!");
			} else {
				System.out.println(productNo + " �� ��ǰ�� �������� �ʽ��ϴ�!");
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	public void deleteAllCart() {
		Connection conn = null;
		Member member = new Member();
		try {
			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			CartDao cartDao = new CartDao(conn);
			cartDao.deleteAll(member.getId());
			System.out.println("��ٱ��ϸ� ������ϴ�.");
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
