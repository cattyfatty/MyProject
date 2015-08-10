package test.shoppingmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.shoppingmall.vo.OrderItem;

public class OrderItemDao {
	private Connection conn;
	
	public OrderItemDao(Connection conn){
		this.conn = conn;
	}
	public Integer insert(OrderItem orderitem) throws SQLException{
		Integer pk = null;
		String sql = "insert into orderitems(order_no,product_no,orderitem_count,orderitem_price) "
				+ "values(?,?,?,?)";
		PreparedStatement pstmt= conn.prepareStatement(sql,new String[]{"orderitem_no"});
		
		pstmt.setInt(1,orderitem.getOrderNo() );
		pstmt.setInt(2, orderitem.getProductNo());
		pstmt.setInt(3, orderitem.getOrderItemCount());
		pstmt.setInt(4, orderitem.getOrderItemPrice());
		int rows = pstmt.executeUpdate();
		if(rows==1){
			ResultSet rs=pstmt.getGeneratedKeys();
			if(rs.next()){
				pk=rs.getInt(1);
			}
			rs.close();
		}
		
		pstmt.close();
		return pk;
	}
	public int update(OrderItem orderitem) throws SQLException {
		int rows = 0;
		String sql = "update orderitems set order_no=? ,product_no=?, orderitem_count=?,orderitem_price=? where orderitem_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,orderitem.getOrderNo() );
		pstmt.setInt(2, orderitem.getProductNo());
		pstmt.setInt(3, orderitem.getOrderItemCount());
		pstmt.setInt(4, orderitem.getOrderItemPrice());
		pstmt.setInt(5, orderitem.getOrderItemNo());
		rows = pstmt.executeUpdate();
		pstmt.close();
		return rows;
	}
	public int delete(int orderItemNo) throws SQLException {
		int rows = 0;
		String sql = "delete from orderitems where orderitem_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, orderItemNo);
		rows = pstmt.executeUpdate();
		pstmt.close();
		return rows;
	}
	
	public List<OrderItem> selectByorderNo(int orderNo) throws SQLException {
		List<OrderItem> list=new ArrayList<OrderItem>();
		String sql = "select a.order_no, orderitem_count, c.product_price, c.product_no, c.product_name "
				+ "from orders a,orderitems b, products c "
				+ "where a.order_no=b.order_no and b.product_no=c.product_no and order_no=? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  orderNo);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			OrderItem orderitem = new OrderItem();
			orderitem.setOrderNo( rs.getInt("order_no") );
			orderitem.setOrderItemCount(rs.getInt("orderitem_count") );
			orderitem.setOrderItemPrice( rs.getInt("product_price") );
			orderitem.setProductNo( rs.getInt("product_no") );
			orderitem.setProductName(rs.getString("product_name"));
//			orderitem.setOrderItemNo( rs.getInt("orderitem_no") );
			list.add(orderitem);
		}
		rs.close();
		pstmt.close();
		return list;
	}
	public List<OrderItem> selectAll() throws SQLException {
		List<OrderItem> list=new ArrayList<OrderItem>();
		String sql = "select a.order_no, orderitem_count, c.product_price, c.product_no, c.product_name "
				+ "from orders a,orderitems b, products c "
				+ "where a.order_no=b.order_no and b.product_no=c.product_no ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			OrderItem orderitem = new OrderItem();
			orderitem.setOrderNo( rs.getInt("order_no") );
			orderitem.setOrderItemCount(rs.getInt("orderitem_count") );
			orderitem.setOrderItemPrice( rs.getInt("product_price") );
			orderitem.setProductNo( rs.getInt("product_no") );
			orderitem.setProductName( rs.getString("product_name"));
			
			
			list.add(orderitem);
		}
		rs.close();
		pstmt.close();
		
		return list;
	}
	
	
	
}
