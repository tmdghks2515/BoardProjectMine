package survice;


import java.util.ArrayList;

import DTO.BoardDTO;
import DTO.CommentDTO;
import dao.BoardDAO;

public class BoardService {
	private static BoardService instance = new BoardService();
	
	private BoardService() {
	}

	public static BoardService getInstance() {
		if(instance == null)
			instance = new BoardService();
		return instance;
	}
	
	public int insertBoard(String writer,String title,String content) throws Exception {
		
		int bNo = BoardDAO.getInstance().insertBoard1();
		BoardDTO dto = new BoardDTO(bNo, writer, content, title);
		BoardDAO.getInstance().insertBoard2(dto);
		return bNo;
	}
	
	public BoardDTO selectBoard(int bNo) throws Exception {
		return BoardDAO.getInstance().selectBoard(bNo);
	}
	
	public ArrayList<CommentDTO> selectAllComment(int bNo){
		return BoardDAO.getInstance().selectAllComment(bNo);
	}
	
	public void insertComment(CommentDTO cdto) throws Exception {
		BoardDAO.getInstance().insertComment(cdto);
	}
	
	public boolean boardLikeCheck(int bNo, String id) {
		return BoardDAO.getInstance().boardLikeCheck(bNo,id);
	}
	
	public void boardLike(int bNo,String id,int flag) throws Exception {
		BoardDAO.getInstance().insertBoardLike(bNo,id,flag);
		BoardDAO.getInstance().boardLike(bNo,flag);
	}
}
