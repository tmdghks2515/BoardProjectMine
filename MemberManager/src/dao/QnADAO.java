package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DBManager;
import dto.QnADTO;

public class QnADAO {
	private static QnADAO instance = new QnADAO();
	private Connection conn;
	private DBManager manager;
	private QnADAO() {
		manager = DBManager.getInstance();
		conn = manager.getConn();
	}

	public static QnADAO getInstance() {
		if(instance == null)
			instance = new QnADAO();
		return instance;
	}

	public ArrayList<QnADTO> selectQnAById(String id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QnADTO> li = new ArrayList<>();
		String sql = "select rownum, qna2.* from(select * from qna where writer like ? order by status,qno desc)qna2 where rownum <= 5";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				li.add(new QnADTO(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
			}
			if(li.size()==0)
				throw new Exception("작성한 문의가 없습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		
		return li;
	}

	public void insertQnA(String title, String content, String writer) throws Exception {
		PreparedStatement pstmt = null;
		String sql = "insert into qna(qno,title,content,writer) values(qno_seq.nextval,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			int count = pstmt.executeUpdate();
			if(count == 0)
				throw new Exception("falied to insert QnA");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, null);
		}
		
	}

	public ArrayList<QnADTO> selectAllQnA(String id) {
		ArrayList<QnADTO> li = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qna where writer like ? order by status,qno desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				li.add(new QnADTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		return li;
	}

	
}
