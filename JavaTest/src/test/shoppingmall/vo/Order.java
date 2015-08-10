package test.shoppingmall.vo;

import java.util.Date;

public class Order {
	
	private int orderNo;
	private String memberId;
	private int orderPrice;

	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderprice) {
		this.orderPrice = orderprice;
	}
	
	
	
}
