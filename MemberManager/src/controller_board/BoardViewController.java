package controller_board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import dto.BoardDTO;
import dto.CommentDTO;
import model.ModelAndView;
import service.BoardService;

public class BoardViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		HttpSession session = request.getSession();
		try {
			BoardDTO bdto = BoardService.getInstance().selectBoard(bNo);
			session.setAttribute("bdto", bdto);
			ArrayList<CommentDTO> cli = BoardService.getInstance().selectAllComment(bNo);
			session.setAttribute("cli", cli);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(request.getContextPath()+"/board/board_view.jsp",false);
	}

}
