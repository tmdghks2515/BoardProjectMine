package qna_controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelAndView;
import service.MemberService;
import vo.MemberVO;

public class idCheckController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		String id = request.getParameter("id");
		MemberVO vo = MemberService.getInstance().select(id);
		if(vo == null) {
			request.setAttribute("id_check", true);
			view = new ModelAndView("register.jsp", false);
		}else {
			request.setAttribute("id_check", false);
			view = new ModelAndView("register.jsp",false);
		}
		return view;
	}

}
