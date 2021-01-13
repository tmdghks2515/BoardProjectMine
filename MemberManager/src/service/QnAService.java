package service;

import java.util.ArrayList;

import dao.QnADAO;
import dto.QnADTO;

public class QnAService {
	private static QnAService instance = new QnAService();

	private QnAService() {
	}

	public static QnAService getInstance() {
		if(instance == null)
			instance = new QnAService();
		return instance;
	}
	

	public ArrayList<QnADTO> selectQnAById(String id) throws Exception {
		ArrayList<QnADTO> li = null;
		if(MemberService.getInstance().select(id).getGrade().equals("MASTER")) {
			id="%";
			li = QnADAO.getInstance().selectQnAById(id);
		}else {
			li = QnADAO.getInstance().selectQnAById(id);
		}
		return li;
	}
	
	public ArrayList<QnADTO> selectAllQnA(String id) throws Exception {
		ArrayList<QnADTO> li = null;
		if(MemberService.getInstance().select(id).getGrade().equals("MASTER"))
			id="%";
		li = QnADAO.getInstance().selectAllQnA(id);
		return li;
	}

	public void insertQnA(String title, String content, String writer) throws Exception {
		QnADAO.getInstance().insertQnA(title,content,writer);
	}
	
	
}
