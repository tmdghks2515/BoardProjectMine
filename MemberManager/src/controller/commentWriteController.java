package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CommentDTO;
import model.ModelAndView;
import service.BoardService;

public class commentWriteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String comment = request.getParameter("comment");
		String writer = request.getParameter("writer");
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		CommentDTO cdto = new CommentDTO(bNo, comment, writer);
		try {
			BoardService.getInstance().insertComment(cdto);
			ArrayList<CommentDTO> cli =  BoardService.getInstance().selectAllComment(bNo);
			request.getSession().setAttribute("cli", cli);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("/board/board_view.jsp", true);
	}

}
