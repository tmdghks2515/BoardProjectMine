package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DBManager;
import dto.BoardDTO;
import dto.CommentDTO;

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
		String sql = "insert into board(bno,title,writer,content) values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getbNo());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getWriter());
			pstmt.setString(4, dto.getContent());
			int count = pstmt.executeUpdate();
			if(count == 0)
				throw new Exception("게시글 등록 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, null);
		}
		
	}

	public BoardDTO selectBoard(int bNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		String sql = "select * from board where bno = "+bNo;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				dto = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
			else
				throw new Exception("게시글 조회 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		return dto;
	}

	public ArrayList<CommentDTO> selectAllComment(int bNo) {
		ArrayList<CommentDTO> li = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board_comment where bno = "+bNo+" order by cNo desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				li.add(new CommentDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		
		return li;
	}

	public void insertComment(CommentDTO cdto) throws Exception {
		PreparedStatement pstmt = null;
		String sql = "insert into board_comment(cno,bno,content,writer) values(cno_seq.nextval,"
				+ "?,?,?) ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cdto.getbNo());
			pstmt.setString(2, cdto.getContent());
			pstmt.setString(3, cdto.getWriter());
			int count = pstmt.executeUpdate();
			if(count != 1)
				throw new Exception("댓글 등록 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, null);
		}
	}

	public boolean boardLikeCheck(int bNo, String id) {
		boolean result = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board_like where bno = ? and id like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if(rs.next())
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void insertBoardLike(int bNo, String id, int flag) throws Exception {
		PreparedStatement pstmt = null;
		String sql = "insert into board_like values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.setString(2, id);
			pstmt.setInt(3, flag);
			int count = pstmt.executeUpdate();
			if(count != 1)
				throw new Exception("좋아요 누르기 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, null);
		}
		
				
	}

	public void boardLike(int bNo, int flag) throws Exception {
		String sql = "";
		if(flag == 1)
			sql = "update board set blike = blike+1 where bno ="+bNo;
		else if(flag == -1)
			sql = "update board set bhate = bhate+1 where bno ="+bNo;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			int count = pstmt.executeUpdate();
			if(count != 1)
				throw new Exception("좋아요 증가 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, null);
		}
	}

	public ArrayList<BoardDTO> selectAllBoards() {
		ArrayList<BoardDTO> li = new ArrayList<BoardDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board order by bno desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				li.add(new BoardDTO(rs.getInt(1), rs.getString(2), rs.getInt(3),
						rs.getString(4),rs.getString(5), rs.getInt(6), rs.getInt(7), 
						rs.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		return li;
	}

	public void cLikeHate(int cNo, int index){
		PreparedStatement pstmt = null;
		String sql = "";
		if(index == 0)
			sql = "update board_comment set clike = clike + 1 where cno = "+cNo;
		else if(index == 1)
			sql = "update board_comment set chate= chate + 1 where cno = "+cNo;
		try {
			pstmt = conn.prepareStatement(sql);
			int count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, null);
		}
		
	}
	


}
