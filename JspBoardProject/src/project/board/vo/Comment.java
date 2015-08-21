package project.board.vo;

public class Comment {
	private int cmts_no;
	private int board_no;
	private int member_uid;
	private String cmts_body;
	private String cmts_date;
	
	public int getCmts_no() {
		return cmts_no;
	}
	public void setCmts_no(int cmts_no) {
		this.cmts_no = cmts_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getMember_uid() {
		return member_uid;
	}
	public void setMember_uid(int member_uid) {
		this.member_uid = member_uid;
	}
	public String getCmts_body() {
		return cmts_body;
	}
	public void setCmts_body(String cmts_body) {
		this.cmts_body = cmts_body;
	}
	public String getCmts_date() {
		return cmts_date;
	}
	public void setCmts_date(String cmts_date) {
		this.cmts_date = cmts_date;
	}
}
