package qna_controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.QnADTO;
import model.ModelAndView;
import service.QnAService;

public class ReadQnAController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int index = Integer.parseInt(request.getParameter("index"));
		try {
			ArrayList<QnADTO> li = QnAService.getInstance().selectAllQnA((String)session.getAttribute("id"));
			if(li.get(index).getStatus()==0) {
				int qNo = li.get(index).getqNo();
				QnAService.getInstance().readQnA(qNo);
				response.getWriter().write("did");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
