package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.QnADTO;
import model.ModelAndView;
import service.QnAService;

public class QnAViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		String id = (String)request.getSession().getAttribute("id");
		try {
			ArrayList<QnADTO> li = QnAService.getInstance().selectQnAById(id,1);
			request.getSession().setAttribute("li", li);
			view = new ModelAndView(request.getContextPath()+"/qna/qna_view.jsp", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

}
