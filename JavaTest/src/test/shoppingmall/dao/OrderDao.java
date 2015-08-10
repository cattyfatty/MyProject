package test.shoppingmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.shoppingmall.vo.Order;

public class OrderDao {
	private Connection conn;

	// 생성자 주입
	public OrderDao(Connection conn) {
		this.conn = conn;
	}

	// 데이터 접근 메소드
	public Integer insert(Order order) throws SQLException {
		Integer pk = null;
		String sql = "insert into orders (MEMBER_ID, ORDER_PRICE) values (?,?)";
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "ORDER_NO" });


		pstmt.setString(1, order.getMemberId());
		pstmt.setInt(2, order.getOrderPrice());

		
		// sql문 실행 준비끝
		int row = pstmt.executeUpdate(); // sql문을 실행한다. 실행한 행 수를 리턴.

		if (row == 1) {

			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				pk = rs.getInt(1); 
			}
			rs.close();
		}

		return pk; // insert에 실패하면 null 반환
	}
	

	public int update(Order order) throws SQLException {
		int rows = 0;
		String sql = "update orders set order_price=? where order_no = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, order.getOrderPrice());
		pstmt.setInt(2, order.getOrderNo());

		rows = pstmt.executeUpdate();
		pstmt.close();
		return rows;
	}

	
	public int delete(int orderNo) throws SQLException {
		int rows = 0;
		String sql = "delete from orders where order_no=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, orderNo);

		rows = pstmt.executeUpdate(); // 몇개의 행이 실행되었는지

		// System.out.println(pk + "번 게시물이 저장됨");
		return rows;
	}

	public Order selectByPK(int orderNo) throws SQLException {
		Order order = null;
		String sql = "select * from orders where order_No=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, orderNo);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {

			// false를 리턴하면 뒤에 행이 없는 것.
			order = new Order();
			orderNo = rs.getInt("order_No");
			order.setOrderNo(rs.getInt("order_No"));
			order.setMemberId(rs.getString("member_id"));
			order.setOrderPrice(rs.getInt("order_price"));
		}
		rs.close();
		pstmt.close();
		return order;
	}

	public List<Order> selectByPage(int pageNo, int rowsPerPage) throws SQLException {
		List<Order> list = new ArrayList<Order>();
		String sql = "";

		sql += "select rn, order_No, member_id, order_price ";
		sql += "from ";
		sql += "( ";
		sql += "select rownum rn, order_No, member_id, order_price ";
		sql += "from ";
		sql += "(";
		sql += "select order_No, member_id, order_price ";
		sql += "from orders ";
		sql += " order by order_No desc ";
		sql += ") ";
		sql += "where rownum <= ? ";
		sql += ") ";
		sql += "where rn >= ? ";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageNo * rowsPerPage);
		pstmt.setInt(2, (pageNo - 1) * rowsPerPage + 1);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			// false를 리턴하면 뒤에 행이 없는 것.
			Order order = new Order();
			order.setOrderNo(rs.getInt("order_No"));
			order.setMemberId(rs.getString("member_id"));
			order.setOrderPrice(rs.getInt("order_price"));

			list.add(order);
		}
		rs.close();
		pstmt.close();
		return list;
	}

	public List<Order> selectAll() throws SQLException {
		List<Order> list = new ArrayList<Order>();
		String sql = "select * from orders";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			// false를 리턴하면 뒤에 행이 없는 것.
			Order order = new Order();
			order.setOrderNo(rs.getInt("order_No"));
			order.setMemberId(rs.getString("member_id"));
			order.setOrderPrice(rs.getInt("order_price"));

			list.add(order);
		}
		rs.close();
		pstmt.close();
		return list;
	}

}
