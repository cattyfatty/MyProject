package example01.addrbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddrBean {
	Connection conn;
	PreparedStatement pstmt = null;
	
	void connect() {
		try {
			this.conn = ConnectionManager.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("check jdbc driver");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("check url, user name, password");
		}
	}
	
	void disconnect() {
		if(this.pstmt != null){
			try{pstmt.close();} catch(SQLException e){}
		}
		if(this.conn != null) {
			try{conn.close();} catch(SQLException e){}
		}
	}
	
	public Integer insertDB(AddrBook addrbook) {
		Integer ab_id = null;
		String sql = "INSERT INTO user2_addrbook(ab_name,ab_email,ab_birth,ab_tel,"
				+ "ab_comdept, ab_memo) VALUES(?,?,?,?,?,?)";
		ResultSet rs = null;
		connect();
		
		try {
			pstmt = conn.prepareStatement(sql, new String[]{"ab_id"});
			pstmt.setString(1, addrbook.getAb_name());
			pstmt.setString(2, addrbook.getAb_email());
			pstmt.setString(3, addrbook.getAb_birth());
			pstmt.setString(4, addrbook.getAb_tel());
			pstmt.setString(5, addrbook.getAb_comdept());
			pstmt.setString(6, addrbook.getAb_memo());
			int rows = pstmt.executeUpdate();
			if(rows > 0) {
				rs = pstmt.getGeneratedKeys();
				if(rs.next()){
					ab_id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("failed insert");
			return ab_id;
		} finally {
			disconnect();
		}
		
		return ab_id;
	}
	
	public Integer updateDB(AddrBook addrbook) {
		Integer rows = null;
		String sql = "UPDATE user2_addrbook SET ab_name=?, ab_email=?, ab_birth=?, "
				+ "ab_tel=?, ab_comdept=?, ab_memo=? WHERE ab_id=?";
		connect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addrbook.getAb_name());
			pstmt.setString(2, addrbook.getAb_email());
			pstmt.setString(3, addrbook.getAb_birth());
			pstmt.setString(4, addrbook.getAb_tel());
			pstmt.setString(5, addrbook.getAb_comdept());
			pstmt.setString(6, addrbook.getAb_memo());
			pstmt.setInt(7, addrbook.getAb_id());
			
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("failed update");
			return rows;
		} finally {
			disconnect();
		}
		
		return rows;
	}
	
	public Integer deleteDB(int ab_id) {
		Integer rows = null;
		String sql = "DELETE FROM user2_addrbook WHERE ab_id=?";
		connect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ab_id);
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("failed delete");
			return rows;
		} finally {
			disconnect();
		}
		
		return rows;
	}
	
	public AddrBook selectDBbyId(int ab_id) {
		connect();
		AddrBook addrbook = null;
		String sql = "SELECT * FROM user2_addrbook WHERE ab_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ab_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				addrbook = new AddrBook();
				addrbook.setAb_id(rs.getInt("ab_id"));
				addrbook.setAb_name(rs.getString("ab_name"));
				addrbook.setAb_email(rs.getString("ab_email"));
				addrbook.setAb_birth(rs.getString("ab_birth"));
				addrbook.setAb_tel(rs.getString("ab_tel"));
				addrbook.setAb_comdept(rs.getString("ab_comdept"));
				addrbook.setAb_memo(rs.getString("ab_memo"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("failed select by id");
			return addrbook;
		} finally {
			disconnect();
		}
		
		return addrbook;
	}
	
	public ArrayList<AddrBook> selectDBAll() {
		connect();
		ArrayList<AddrBook> list = new ArrayList<AddrBook>();
		AddrBook addrbook = null;
		String sql = "SELECT * FROM user2_addrbook ORDER BY ab_id desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				addrbook = new AddrBook();
				addrbook.setAb_id(rs.getInt("ab_id"));
				addrbook.setAb_name(rs.getString("ab_name"));
				addrbook.setAb_email(rs.getString("ab_email"));
				addrbook.setAb_birth(rs.getString("ab_birth"));
				addrbook.setAb_tel(rs.getString("ab_tel"));
				addrbook.setAb_comdept(rs.getString("ab_comdept"));
				addrbook.setAb_memo(rs.getString("ab_memo"));
				list.add(addrbook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("failed select by id");
			return list;
		} finally {
			disconnect();
		}
		
		return list;
	}
}
