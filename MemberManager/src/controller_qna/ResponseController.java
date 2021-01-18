package controller_qna;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.ModelAndView;
import service.QnAService;

public class ResponseController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		QnAService.getInstance().response(Integer.parseInt(request.getParameter("qNo"))," /n "+request.getParameter("response"));
		return new ModelAndView("qnaView.do", true);
	}

}
