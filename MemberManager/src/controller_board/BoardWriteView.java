package controller_board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.ModelAndView;

public class BoardWriteView implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView view = new ModelAndView();
		if(session.getAttribute("login")==null || !(boolean)session.getAttribute("login")) {
			try {
				response.getWriter().append("<script>alert('게시글 작성을 위해 로그인 화면 으로 이동합니다.');"
						+ "location.href='"+request.getContextPath()+"/member/login.jsp?url=../board/write_board.jsp';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			view.setPage(request.getContextPath()+"/board/write_board.jsp");
			view.setSendRedirect(true);
		}
		return view;
	}

}
