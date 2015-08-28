package project.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.member.vo.Group;

public class GroupDao {
	private Connection conn;

	public GroupDao(Connection conn) {
		this.conn = conn;
	}
	
	public boolean insert(Group group) throws SQLException {
		boolean success = false;
		String sql = "INSERT INTO team1.hak_groups VALUES(?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, group.getMember_uid());
		pstmt.setInt(2, group.getGroup_id());
		
		int row = pstmt.executeUpdate();
		if(row > 0) {
			success = true;
		}
		
		pstmt.close();
		return success;
	}
	
	public boolean updateGroup(Group group, int old_group) throws SQLException {
		boolean success = false;
		String sql = "UPDATE team1.hak_groups SET group_id = ? WHERE member_uid = ? and group_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, group.getGroup_id());
		pstmt.setInt(2, group.getMember_uid());
		pstmt.setInt(3, old_group);
		
		int row = pstmt.executeUpdate();
		if(row > 0) {
			success = true;
		}
		
		pstmt.close();
		return success;
	}
	
	public boolean deleteGroup(Group group) throws SQLException {
		boolean success = false;
		String sql = "DELETE FROM team1.hak_groups WHERE member_uid = ? and group_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, group.getMember_uid());
		pstmt.setInt(2, group.getGroup_id());
		
		int row = pstmt.executeUpdate();
		if(row > 0) {
			success = true;
		}
		
		return success;
	}
	
	public ArrayList<Group> selectGroup(int uid) throws SQLException {
		ArrayList<Group> group_list = new ArrayList<Group>();
		String sql = "SELECT * FROM team1.hak_groups WHERE member_uid = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Group group = new Group();
			group.setGroup_id(rs.getInt("group_id"));
			group.setMember_uid(rs.getInt("member_uid"));
			group_list.add(group);
		}
		
		rs.close();
		pstmt.close();
		return group_list;
	}
}
