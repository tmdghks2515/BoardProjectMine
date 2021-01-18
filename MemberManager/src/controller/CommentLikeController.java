package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ModelAndView;
import service.BoardService;

public class CommentLikeController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BoardService.getInstance().cLikeHate((String)session.getAttribute("id"),Integer.parseInt(request.getParameter("cNo")), Integer.parseInt(request.getParameter("index")));
		return null;
	}

}
