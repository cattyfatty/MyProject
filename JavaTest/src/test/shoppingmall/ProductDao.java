package test.shoppingmall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	private Connection conn;
	
	public ProductDao(Connection conn) {
		this.conn = conn;
	}
	
	public Integer insert(Product product) throws SQLException {
		int rows = 0;
		String sql = "INSERT INTO products VALUES(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, product.getpName());
		pstmt.setInt(2, product.getpPrice());
		
		rows = pstmt.executeUpdate();
		if(rows < 1) {
			System.out.println("입력 실패");
		}
		
		pstmt.close();
		return rows;
	}
	
	public Integer updateProductName(Product product) throws SQLException {
		int rows = 0;
		String sql = "UPDATE products SET product_name=? WHERE product_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, product.getpName());
		pstmt.setInt(2, product.getpNo());
		
		rows = pstmt.executeUpdate();
		if(rows < 1) {
			System.out.println("입력 실패");
		}
		
		pstmt.close();
		return rows;
	}
	
	public Integer updateProductPrice(Product product) throws SQLException {
		int rows = 0;
		String sql = "UPDATE products SET product_price=? WHERE product_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, product.getpPrice());
		pstmt.setInt(2, product.getpNo());
		
		rows = pstmt.executeUpdate();
		if(rows < 1) {
			System.out.println("입력 실패");
		}
		
		pstmt.close();
		return rows;
	}
	
	public Integer deleteByProductNo(int no) throws SQLException {
		int rows = 0;
		String sql = "DELETE FROM products WHERE product_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, no);
		rows = pstmt.executeUpdate();
		if(rows < 1) {
			System.out.println("제품 삭제 실패");
		} else {
			System.out.println(no + "번 상품이 삭제되었습니다.");
		}
		
		pstmt.close();
		return rows;
	}
	
	public Integer deleteAll() throws SQLException {
		int rows = 0;
		String sql = "DELETE FROM products";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		rows = pstmt.executeUpdate();
		if(rows < 1) {
			System.out.println("제품 삭제 실패했습니다.");
		} else {
			System.out.println("모든 제품을 삭제했습니다.");
		}
		
		pstmt.close();
		return rows;
	}
	
	public Product selectByProductNo(int no) throws SQLException {
		Product product = null;
		String sql = "SELECT * FROM products WHERE product_no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			product = new Product();
			product.setpNo(rs.getInt("product_no"));
			product.setpName(rs.getString("product_name"));
			product.setpPrice(rs.getInt("product_price"));
		}
		
		rs.close();
		pstmt.close();
		
		return product;
	}
	
	public List<Product> selectAllByPage(int pageNo, int rowsPerPage) throws SQLException {
		List<Product> list = new ArrayList<Product>();
		String sql = "SELECT * FROM "
				+ "(SELECT ROWNUM as rn, product_no, product_name, product_price FROM "
				+ "(SELECT * FROM products ORDER BY product_no DESC) "
				+ "WHERE ROWNUM <= ?) "
				+ "WHERE rn >= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageNo * rowsPerPage);
		pstmt.setInt(2, (pageNo-1)*rowsPerPage + 1);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product(); 
			product.setpNo(rs.getInt("product_no"));
			product.setpName(rs.getString("product_name"));
			product.setpPrice(rs.getInt("product_price"));
			list.add(product);
		}
		
		rs.close();
		pstmt.close();
		
		return list;
	}
	
	public List<Product> selectAll() throws SQLException {
		List<Product> list = new ArrayList<Product>();
		String sql = "SELECT * FROM products";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product();
			product.setpNo(rs.getInt("product_no"));
			product.setpName(rs.getString("product_name"));
			product.setpPrice(rs.getInt("product_price"));
			list.add(product);
		}
		
		rs.close();
		pstmt.close();
		return list;
	}
}
