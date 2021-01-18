package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import dto.CommentDTO;
import model.ModelAndView;
import service.BoardService;

public class BoardViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		try {
			BoardDTO bdto = BoardService.getInstance().selectBoard(bNo);
			request.getSession().setAttribute("bdto", bdto);
			ArrayList<CommentDTO> cli = BoardService.getInstance().selectAllComment(bNo);
			request.getSession().setAttribute("cli", cli);
			request.getSession().setAttribute("idHidden", ((String)request.getSession().getAttribute("id")).substring(0, 3)+"***");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(request.getContextPath()+"/board/board_view.jsp",true);
	}

}
