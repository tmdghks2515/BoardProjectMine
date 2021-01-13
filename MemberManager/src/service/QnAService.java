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
		ArrayList<QnADTO> li = QnADAO.getInstance().selectQnAById(id,page);
		return null;
	}

	public void insertQnA(String title, String content, String writer) throws Exception {
		QnADAO.getInstance().insertQnA(title,content,writer);
	}
}
