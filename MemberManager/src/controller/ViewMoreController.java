package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelAndView;
import service.QnAService;

public class ViewMoreController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page"));
		try {
			int maxPage = (int)Math.ceil((QnAService.getInstance().selectAllQnA((String)request.getSession().getAttribute("id")).size()/5));
			if(page < maxPage)
				request.getSession().setAttribute("li", QnAService.getInstance().selectQnAById((String)request.getSession().getAttribute("id"),page));
			else {
				request.getSession().setAttribute("li", QnAService.getInstance().selectAllQnA((String)request.getSession().getAttribute("id")));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
