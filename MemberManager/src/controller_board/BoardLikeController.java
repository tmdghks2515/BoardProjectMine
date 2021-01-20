package controller_board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import dto.BoardDTO;
import model.ModelAndView;
import service.BoardService;

public class BoardLikeController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			if(session.getAttribute("id")==null) {
					response.getWriter().write("로그인이 필요한 작업입니다");
					return null;
			}else {
				String id = (String) session.getAttribute("id");
				int bNo = ((BoardDTO)session.getAttribute("bdto")).getbNo();
				boolean flag = BoardService.getInstance().boardLikeCheck(bNo, id);
				if(flag) {
					response.getWriter().write("좋아요는 한번만 가능합니다.");
				}else {
					int index = Integer.parseInt(request.getParameter("index"));
					BoardService.getInstance().boardLike(bNo, id, index);
					session.setAttribute("bdto", BoardService.getInstance().selectBoard(bNo));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
