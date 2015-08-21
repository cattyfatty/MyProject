package project.board.dao;

import java.sql.Connection;

import project.board.vo.Board;

public class BoardDao {
	private Connection conn;

	public BoardDao(Connection conn) {
		this.conn = conn;
	}
	
	public boolean insertNewThread(Board board) {
		boolean success = false;
		String sql = "INSERT INTO team1.hak_boards(member_uid, board_title, board_date, board_content, "
				+ "board_ref) VALUES(?,?,?,?,?)";
		
		
		return success;
	}
}
