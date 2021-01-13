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

	public ArrayList<QnADTO> selectQnAById(String id,int page) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QnADTO> li = new ArrayList<>();
		String sql = "select * from (select ceil(rownum/5) as page qna2.* from  (select qna.* from qna where writer like ? order by qno desc)qna2) where page = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, page);
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
	
}
