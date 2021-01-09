package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.BoardDTO;
import config.DBManager;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	DBManager manager;
	Connection conn;

	private BoardDAO() {
		manager = DBManager.getInstance();
		conn = manager.getConn();
	}

	public static BoardDAO getInstance() {
		if(instance == null)
			instance = new BoardDAO();
		return instance;
	}
	
	public int insertBoard1() throws Exception {
		int bNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select bno_seq.nextval from dual";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				bNo = rs.getInt(1);
			else
				throw new Exception("bNo 추출 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		return bNo;
	}
	
	public void insertBoard2(BoardDTO dto) throws Exception {
		PreparedStatement pstmt = null;
		String sql = "insert into board(bno,title,writer,content) values(bno_seq.nextval,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());
			int count = pstmt.executeUpdate();
			if(count == 0)
				throw new Exception("게시글 등록 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, null);
		}
		
	}

}
