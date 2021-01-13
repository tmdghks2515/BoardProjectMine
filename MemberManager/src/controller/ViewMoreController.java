package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelAndView;
import service.QnAService;

public class ViewMoreController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().setAttribute("li", QnAService.getInstance().selectAllQnA((String)request.getSession().getAttribute("id")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
