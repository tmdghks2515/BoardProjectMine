package survice;


import DTO.BoardDTO;
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
	
}
