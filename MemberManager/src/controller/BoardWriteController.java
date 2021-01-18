package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelAndView;
import service.BoardService;

public class BoardWriteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int bNo = 0;
		try {
			bNo = BoardService.getInstance().insertBoard(request.getParameter("writer"),
					request.getParameter("title"), request.getParameter("content"));
			response.getWriter().write(String.valueOf(bNo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
