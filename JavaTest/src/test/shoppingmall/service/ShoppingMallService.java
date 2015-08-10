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
				System.out.print("상품번호 : " + cart.getProductNo() + "\t");
				System.out.print("상품명 : " + cart.getProductName() + "\t");
				System.out.print("수량 : " + cart.getCartCount() + "\t");
				System.out.println("가격 : " + cart.getCartPrice());
			}

		} catch (Exception e) {
			System.out.println("연결오류");
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

			// 주문자
			cart.setMemberId(memberId);
			List<Cart> list = cartDao.selectAll(memberId);
			List<Cart> pro = cartDao.selectProductNo(memberId);
			// 주문할 상품 번호
			System.out.print("주문할 상품 번호 : ");
			Integer productNo = Integer.parseInt(sc.nextLine());
			Product product = productDao.selectByProductNo(productNo);
			
			if (product != null) {
				cart.setProductNo(productNo);
			} else {
				System.out.println("상품 목록을 다시보시고 상품번호를 입력해주세요");
				return; // return을 하더라도 finally는 실행된다.
			}

			// 주문수량
			System.out.print("수량 : ");
			Integer cartCount = Integer.parseInt(sc.nextLine());
			cart.setCartCount(cartCount);
			cart.setCartPrice(cartCount * product.getpPrice());
			
			
			//update로 넘어가기
			for(int i=0;i<pro.size();i++){
				if(productNo==cart.getProductNo())
					updateCart(memberId,productNo,cartCount);
				else{
					Integer pk = cartDao.insert(cart);
					System.out.println(pk + " 번 상품 장바구니 저장");
				}
			}
		} catch (Exception e) {
			System.out.println("정확한 상품 번호를 입력해주세요.");
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
				System.out.println(cart.getCartNo() + " 번 상품이 수정되었습니다!");
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
			System.out.print("삭제할 상품번호 입력 : ");
			int productNo = Integer.parseInt(sc.nextLine());
			String memberId = sc.nextLine();
			int rows = cartDao.deleteOne(productNo, memberId);
			if (rows == 1) {
				System.out.println(productNo + " 번 상품이 삭제되었습니다!");
			} else {
				System.out.println(productNo + " 번 상품이 존재하지 않습니다!");
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
			System.out.println("장바구니를 비웠습니다.");
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
