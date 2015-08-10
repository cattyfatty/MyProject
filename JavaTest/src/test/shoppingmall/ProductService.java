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
				System.out.print("��ǰ �̸�: ");
				product.setpName(sc.nextLine());
				System.out.print("��ǰ ����: ");
				product.setpPrice(Integer.parseInt(sc.nextLine()));
				try {
					rows = productDao.insert(product);
					System.out.println(product.getpName() + "�� ��� �Ǿ����ϴ�.");
				} catch (Exception e) {
					System.out.println("��ǰ ���ݿ� ���ڸ� �Է��ϼ���");
					continue;
				}
			}
			conn.commit();
			sc.close();
			
		} catch (Exception e) {
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			System.out.println("�Է� ����... �ٽ� �õ��� �ּ���");
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
					System.out.format(" %-5s| \t     %-4s     \t| %-7s\n", "��ǰ��ȣ","��ǰ��","��ǰ �ܰ�");
					System.out.println("----------------------------------------------------------------------");
					for(Product product : list) {
						System.out.format("%5d%25s%10d\n",product.getpNo(),product.getpName(),product.getpPrice());
					}
					System.out.println("----------------------------------------------------------------------");
					System.out.println("���� ������: " + pageNo);
					System.out.println("----------------------------------------------------------------------");
					System.out.println("�Է�(ex: ����������: <, ����������: > | ��������: q");
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
							System.out.println("�ٽ� �Է��ϼ���.");
							continue;
						}
					}
				} else {
					System.out.println("��ϵ� ��ǰ�� �����ϴ�.");
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
