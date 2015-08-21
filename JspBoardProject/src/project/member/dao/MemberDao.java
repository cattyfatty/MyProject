package project.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.member.vo.Member;

public class MemberDao {
	private Connection conn;

	public MemberDao(Connection conn) {
		this.conn = conn;
	}

	public Integer insert(Member member) throws SQLException {
		Integer uid = null;
		String sql = "INSERT INTO team1.hak_members(member_id, member_password, member_email) VALUES(?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"member_uid"});
		pstmt.setString(1, member.getMember_id());
		pstmt.setString(2, member.getMember_password());
		pstmt.setString(3, member.getMember_email());
		
		int row = pstmt.executeUpdate();
		if(row > 0) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				uid = rs.getInt(1);
			}
			rs.close();
		}
		pstmt.close();
		return uid;
	}
	
	public boolean updateName(Member member) throws SQLException {
		boolean success = false;
		String sql = "UPDATE team1.hak_members SET member_id = ? WHERE member_uid = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getMember_id());
		pstmt.setInt(2, member.getMember_uid());
		
		int row = pstmt.executeUpdate();
		if(row > 0) {
			success = true;
		}
		
		pstmt.close();
		return success;
	}
	
	public boolean updatePassword(Member member) throws SQLException {
		boolean success = false;
		String sql = "UPDATE team1.hak_members SET member_password = ? WHERE member_uid = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getMember_password());
		pstmt.setInt(2, member.getMember_uid());
		
		int row = pstmt.executeUpdate();
		if(row > 0) {
			success = true;
		}
		
		pstmt.close();
		return success;
	}
	
	public Member selectByEmail(String email) throws SQLException {
		Member member = null;
		String sql = "SELECT * FROM team1.hak_members WHERE member_email = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			member = new Member();
			member.setMember_uid(rs.getInt("member_uid"));
			member.setMember_id(rs.getString("member_id"));
			member.setMember_password(rs.getString("member_password"));
			member.setMember_email(email);
		}
		
		rs.close();
		pstmt.close();
		return member;
	}
}
