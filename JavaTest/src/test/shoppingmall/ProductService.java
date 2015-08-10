package test.shoppingmall;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {
	private Connection conn;
			
	public void insertProduct() {
		try {
			conn = ConnectionManager.getConnection();
			Scanner sc = new Scanner(System.in);
			Product product = new Product();
			ProductDao productDao = new ProductDao(conn);
			int rows = 0;
			
			conn.setAutoCommit(false);
			while(rows==0){
				System.out.print("상품 이름: ");
				product.setpName(sc.nextLine());
				System.out.print("상품 가격: ");
				product.setpPrice(Integer.parseInt(sc.nextLine()));
				try {
					rows = productDao.insert(product);
					System.out.println(product.getpName() + "이 등록 되었습니다.");
				} catch (Exception e) {
					System.out.println("상품 가격에 숫자만 입력하세요");
					continue;
				}
			}
			conn.commit();
			sc.close();
			
		} catch (Exception e) {
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			System.out.println("입력 실패... 다시 시도해 주세요");
		} finally {
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	public void showProduct() {
		try {
			conn = ConnectionManager.getConnection();
			Scanner sc = new Scanner(System.in);
			List<Product> list = new ArrayList<Product>();
			ProductDao productDao = new ProductDao(conn);
			int pageNo = 1;
			int rowsPerPage = 5;
			boolean exit = false;
			
			while(!exit) {
				list = productDao.selectAllByPage(pageNo, rowsPerPage);
				if(list != null) {
					System.out.println("----------------------------------------------------------------------");
					System.out.format(" %-5s| \t     %-4s     \t| %-7s\n", "상품번호","상품명","상품 단가");
					System.out.println("----------------------------------------------------------------------");
					for(Product product : list) {
						System.out.format("%5d%25s%10d\n",product.getpNo(),product.getpName(),product.getpPrice());
					}
					System.out.println("----------------------------------------------------------------------");
					System.out.println("현재 페이지: " + pageNo);
					System.out.println("----------------------------------------------------------------------");
					System.out.println("입력(ex: 이전페이지: <, 다음페이지: > | 보기종료: q");
					System.out.print(": ");
					String choice = sc.nextLine();
					if(choice.equals("<")) {
						pageNo -= 1;
						continue;
					} else if(choice.equals(">")) {
						pageNo += 1;
						continue;
					} else if(choice.equals("q")) {
						exit = true;
					} else {
						try {
							pageNo = Integer.parseInt(choice);
							continue;
						} catch (NumberFormatException e) {
							System.out.println("다시 입력하세요.");
							continue;
						}
					}
				} else {
					System.out.println("등록된 상품이 없습니다.");
					exit = true;
				}
			}
			sc.close();
		} catch (Exception e) {
		} finally {
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	public void updateProduct() {
		
	}
}
