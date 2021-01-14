package controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelAndView;
import service.QnAService;

public class ResponseController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		QnAService.getInstance().response(Integer.parseInt(request.getParameter("qNo")),request.getParameter("response"));
		return new ModelAndView("qnaView.do", true);
	}

}
