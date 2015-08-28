package project.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import project.ConnectionManager;
import project.member.dao.GroupDao;
import project.member.dao.MemberDao;
import project.member.vo.Group;
import project.member.vo.Member;

public class MemberService {
	
	public int joinMember(Member member, int group_id) {
		Connection conn = null;
		int uid = 0;
		try {
			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			MemberDao memberDao = new MemberDao(conn);
			GroupDao groupDao = new GroupDao(conn);
			
			if(memberDao.selectByEmail(member.getMember_email()) != null) {
				System.out.println("user already exits");
				uid = -1;
			} else {
				uid = memberDao.insert(member);
				Group group = new Group();
				group.setGroup_id(group_id);
				group.setMember_uid(uid);
				groupDao.insert(group);
			}
			conn.commit();
		} catch(Exception e) {
			System.out.println("join error");
			e.printStackTrace();
			uid = -1;
		} finally {
			try{conn.close();} catch(SQLException e){}
		}
		
		return uid;
	}
	
	public Member login(Member member) {
		Member cur_member = null;
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
			Member isExist = null;
			MemberDao memberDao = new MemberDao(conn);
			
			isExist = memberDao.selectByEmail(member.getMember_email());
			if(isExist != null) {
				System.out.println(isExist.getMember_email());
				if(isExist.getMember_password().equals(member.getMember_password())) {
					cur_member = new Member();
					cur_member.setMember_uid(isExist.getMember_uid());
					cur_member.setMember_email(isExist.getMember_email());
					cur_member.setMember_id(isExist.getMember_id());
				} else {
					System.out.println("wrong password");
				}
			} else {
				System.out.println("no member");
			}
		} catch (Exception e) {
			System.out.println("login error");
			e.printStackTrace();
		} finally {
			try{conn.close();} catch(SQLException e){}
		}
		
		return cur_member;
	}
	
	public ArrayList<Group> getGroup(Member member) {
		ArrayList<Group> group_list = null;
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
			GroupDao groupDao = new GroupDao(conn);
			
			group_list = groupDao.selectGroup(member.getMember_uid());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get group error");
		} finally {
			try{conn.close();} catch(SQLException e){}
		}
		
		return group_list;
	}
}
