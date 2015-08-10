package test.shoppingmall;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberService {
	private Connection conn;
	
	public void join() {
		Scanner sc = new Scanner(System.in);
		try {
			conn = ConnectionManager.getConnection();
			Member member = new Member();
			MemberDao memberDao = new MemberDao(conn);
			conn.setAutoCommit(false);
			int rows = 0;
			while(rows==0){
				System.out.print("ID: ");
				member.setId(sc.nextLine());
				System.out.print("PW: ");
				member.setPw(sc.nextLine());
				System.out.print("이름: ");
				member.setName(sc.nextLine());
				member.setIsAdmin(0);
				
				try {
					rows = memberDao.insert(member);
					System.out.println(member.getId() + "님이 가입하셨습니다.");
				} catch (SQLException e) {
					System.out.println("가입 실패. 이미 있는 아이디 입니다...");
				}
			}
			conn.commit();
		} catch (Exception e1) {
			try {conn.rollback();} catch (SQLException e) {e.printStackTrace();}
			System.out.println("가입 실패... 다시 시도해 주세요.");
		} finally {
			try {conn.close();} catch (Exception e) {}
		}
		
		sc.close();
	}
	
	public Member login() {
		Scanner sc = new Scanner(System.in);
		Member member = null;
		try {
			conn = ConnectionManager.getConnection();
			MemberDao memberDao = new MemberDao(conn);
			boolean login = false;
			
			while(!login) {
				System.out.print("ID: ");
				String id = sc.nextLine();
				System.out.print("PW: ");
				String pw = sc.nextLine();
				try {
					member = memberDao.selectById(id);
					if(member == null) {
						System.out.println("아이디가 없습니다. 아이디를 확인하세요.");
						continue;
					} else {
						if(member.getPw().equals(pw)) {
							System.out.println("로그인 하셨습니다.");
							login = true;
						} else {
							System.out.println("비밀번호를 확인하세요.");
							continue;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("");
				}
			}
		} catch (Exception e) {
		} finally {
			try {conn.close();} catch (SQLException e) {}
			sc.close();
		}
		return member;
	}
	
	public void showMemberList() {
		Scanner sc = new Scanner(System.in);
		
		try {
			conn = ConnectionManager.getConnection();
			List<Member> memberList = new ArrayList<Member>();
			MemberDao memberDao = new MemberDao(conn);
			int pageNo = 1;
			int rowsPerPage = 5;
			boolean exit = false;
			
			while(!exit) {
				memberList = memberDao.selectAllByPage(pageNo, rowsPerPage);
				if(memberList != null){
					System.out.println("-----------------------------------------------------");
					System.out.println("    아이디    |     이름     |     관리자");
					System.out.println("-----------------------------------------------------");
					for(Member m : memberList) {
						System.out.print("     " + m.getId() + "          " + m.getName());
						if(m.getIsAdmin() == 1) {
							System.out.println("          yes");
						} else {
							System.out.println("           no");
						}
					}
					System.out.println("-----------------------------------------------------");
					System.out.println("현재 페이지: " + pageNo);
					System.out.println("-----------------------------------------------------");
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
					System.out.println("가입된 멤버가 없습니다.");
					exit = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			sc.close();
		}
	}
	
	public void deleteMember() {
		Scanner sc = new Scanner(System.in);
		try {
			conn = ConnectionManager.getConnection();
			MemberDao memberDao = new MemberDao(conn);
			boolean exit = false;
			
			while(!exit) {
				System.out.print("삭제할 회원 아이디: ");
				String mId = sc.nextLine();
				if(mId.equals("q")){
					exit = true;
				} else {
					memberDao.delete(mId);
				}
			}
		} catch (Exception e) {
			System.out.println("디비 에러 발생...");
		} finally {
			try{conn.close();} catch(SQLException e){}
			sc.close();
		}
	}
	
	public void updateMemberGrade() {
		Scanner sc = new Scanner(System.in);
		try {
			conn = ConnectionManager.getConnection();
			Member member = new Member();
			MemberDao memberDao = new MemberDao(conn);
			
			while(true){
				System.out.print("등급을 변경 할 멤버 아이디: ");
				member.setId(sc.nextLine());
				while(true){
					System.out.print("변경할 등급(admin = 1, member = 0): ");
					try {
						int grade = Integer.parseInt(sc.nextLine());
						if(grade == 1) {
							member.setIsAdmin(grade);
							break;
						} else if(grade == 0) {
							member.setIsAdmin(grade);
							break;
						} else {
							throw new NumberFormatException();
						}
					} catch (NumberFormatException e) {
						System.out.println("숫자 0(member) 또는 1(admin) 만 입력하세요");
						continue;
					}
				}
				int result = memberDao.updateMemberGrade(member);
				if(result < 1) {
					System.out.println("해당하는 아이디가 없습니다.");
					continue;
				} else {
					break;
				}
			}
		} catch (Exception e) {
		} finally {
			try{conn.close();} catch (SQLException e) {}
			sc.close();
		}
	}
	
	public void updateMemberPassword(String mId) {
		Scanner sc = new Scanner(System.in);
		try {
			conn = ConnectionManager.getConnection();
			MemberDao memberDao = new MemberDao(conn);
			while(true) {
				System.out.print("변경할 비밀번호: ");
				String pw1 = sc.nextLine();
				System.out.println("비밀번호 확인: ");
				String pw2 = sc.nextLine();
				if(pw1.equals(pw2)) {
					Member member = new Member();
					member.setId(mId);
					member.setPw(pw1);
					
					int result = memberDao.updateMemberPassword(member);
					if(result < 1) {
						System.out.println("");
					}
				} else {
					System.out.println("비밀번호를 확인하세요.");
					continue;
				}
			}
		} catch (Exception e) {
		} finally {
			try{conn.close();} catch (SQLException e) {}
			sc.close();
		}
	}
}
