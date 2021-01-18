package controller_board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import dto.BoardDTO;
import model.ModelAndView;
import service.BoardService;
import vo.PagingVO;

public class BoardListController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int page = 1;
		String mode = "bNo";
		if(request.getParameter("page")!=null)
			page = Integer.parseInt(request.getParameter("page"));
		PagingVO pvo = new PagingVO(BoardService.getInstance().getBoardTotal(), page);
		if(request.getParameter("mode")!=null) {
			mode = request.getParameter("mode");
			pvo.setMode(mode);
		}
		ArrayList<BoardDTO> li = BoardService.getInstance().selectBoards(pvo.getCurrentPage(), pvo.getBoardPerPage(), pvo.getMode());
		request.getSession().setAttribute("li", li);
		request.getSession().setAttribute("pvo",pvo);
		return new ModelAndView(request.getContextPath()+"/board/board_list.jsp",true);
	}

}
