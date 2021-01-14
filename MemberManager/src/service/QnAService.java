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
	

	public ArrayList<QnADTO> selectQnAById(String id,int page) throws Exception {
		ArrayList<QnADTO> li = null;
		if(MemberService.getInstance().select(id).getGrade().equals("MASTER")) {
			id="%";
		}
		li = QnADAO.getInstance().selectQnAById(id,page);
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
	
	public ArrayList<QnADTO> searchUnanswered(){
		return QnADAO.getInstance().searchUnanswered();
	}

	public void response(int qNo, String response) {
		QnADAO.getInstance().response(qNo,response);
	}

	public void readQnA(int qNo) throws Exception {
		QnADAO.getInstance().readQnA(qNo);
	}
	
	
}
