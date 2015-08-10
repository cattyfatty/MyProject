package test.shoppingmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.shoppingmall.vo.Member;

public class MemberDao {
	private Connection conn;
	
	public MemberDao(Connection conn) {
		this.conn = conn;
	}
	
	public Integer insert(Member member) throws SQLException{
		int rows = 0;
		String sql = "INSERT INTO members VALUES(?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getId());
		pstmt.setString(3, member.getName());
		pstmt.setString(2, member.getPw());
		pstmt.setInt(4, member.getIsAdmin());
		
		rows = pstmt.executeUpdate();
		
		pstmt.close();
		return rows;
	}
	
	public Integer updateMemberGrade(Member member) throws SQLException {
		int rows = 0;
		String sql = "UPDATE members SET member_isadmin=? WHERE member_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, member.getIsAdmin());
		pstmt.setString(2, member.getId());
		rows = pstmt.executeUpdate();
		if(rows < 1) {
			System.out.println("���� ����");
		} else {
			System.out.println(member.getId() + "���� ����� ����Ǿ����ϴ�.");
		}
		
		pstmt.close();
		return rows;
	}
	
	public Integer updateMemberPassword(Member member) throws SQLException {
		int rows = 0;
		String sql = "UPDATE members SET member_password=? WHERE member_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, member.getPw());
		pstmt.setString(2, member.getId());
		rows = pstmt.executeUpdate();
		if(rows < 1) {
			System.out.println("��й�ȣ ���� ����");
		} else {
			System.out.println(member.getId() + "���� ��й�ȣ ������ �����߽��ϴ�.");
		}
		
		pstmt.close();
		return rows;
	}
	
	public Integer delete(String id) throws SQLException {
		int rows = 0;
		String sql = "DELETE FROM members WHERE member_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		rows = pstmt.executeUpdate();
		if(rows < 1) {
			System.out.println("���� ����");
		} else {
			System.out.println(id + "�� ���� �Ǿ����ϴ�.");
		}
		
		pstmt.close();
		return rows;
	}
	
	public Member selectById(String id) throws SQLException {
		Member member = null;
		String sql = "SELECT * FROM members WHERE member_id=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			member = new Member();
			member.setId(rs.getString("member_id"));
			member.setPw(rs.getString("member_password"));
			member.setName(rs.getString("member_name"));
			member.setIsAdmin(rs.getInt("member_isadmin"));
		}
		
		rs.close();
		pstmt.close();
		
		return member;
	}
	
	public List<Member> selectAllByPage(int pageNo, int rowsPerPage) throws SQLException {
		List<Member> list = new ArrayList<Member>();
		String sql = "SELECT * FROM "
				+ "(SELECT ROWNUM as rn, member_id, member_name, "
				+ "member_isadmin FROM members "
				+ "WHERE ROWNUM <= ?) "
				+ "WHERE rn >= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, pageNo*rowsPerPage);
		pstmt.setInt(2, (pageNo-1)*rowsPerPage+1);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Member member = new Member();
			member.setId(rs.getString("member_id"));
			member.setName(rs.getString("member_name"));
			member.setIsAdmin(rs.getInt("member_isadmin"));
			System.out.println(rs.getString("member_id") + " " + rs.getString("member_name") + " " + rs.getInt("member_isadmin"));
			list.add(member);
		}
		
		rs.close();
		pstmt.close();
		return list;
	}
	public List<Member> selectAll() throws SQLException {
		List<Member> list = new ArrayList<Member>();
		String sql = "SELECT * FROM members";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Member member = new Member();
			member.setId(rs.getString("member_id"));
			member.setPw(rs.getString("member_password"));
			member.setName(rs.getString("member_name"));
			member.setIsAdmin(rs.getInt("member_isadmin"));
		}
		
		rs.close();
		pstmt.close();
		return list;
	}
}
