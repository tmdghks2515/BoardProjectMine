package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBManager;
import vo.MemberVO;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private Connection conn;
	private DBManager manager;
	private MemberDAO() {
		manager = DBManager.getInstance();
		conn = manager.getConn();
	}

	public static MemberDAO getInstance() {
		if(instance == null)
			instance = new MemberDAO();
		return instance;
	}

	public MemberVO login(String id, String pass) {
		MemberVO vo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ID,NAME,AGE,GRADE_NAME FROM HIVER_MEMBER , GRADE_LIST WHERE GRADE = GRADE_NO AND ID LIKE ? AND PASS LIKE ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			if(rs.next())
				vo = new MemberVO(rs.getString(1), null, rs.getString(2), rs.getInt(3), rs.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		
		return vo;
	}

	public void insert(String id, String pass, String name, int age) throws Exception {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO HIVER_MEMBER VALUES(?,?,?,?,1)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setInt(4, age);
			int count = pstmt.executeUpdate();
			if(count == 0)
				throw new Exception("아이디가 이미 존재합니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, null);
		}
		
	}

	public MemberVO select(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO vo = null;
		String sql = "SELECT ID,NAME,AGE,GRADE_NAME FROM HIVER_MEMBER,GRADE_LIST WHERE GRADE=GRADE_NO AND ID LIKE ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
				vo = new MemberVO(rs.getString(1), null, rs.getString(2), rs.getInt(3), rs.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		
		return vo;
	}

	public void update(MemberVO vo) throws Exception {
		PreparedStatement pstmt = null;
		String sql = "update hiver_member set pass = ?,name=?,age=? where id like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getAge());
			pstmt.setString(4, vo.getId());
			int count = pstmt.executeUpdate();
			if(count == 0)
				throw new Exception("ㅋㅋ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
