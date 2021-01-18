package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ModelAndView;
import service.MemberService;
import vo.MemberVO;

public class LoginController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		MemberVO vo = MemberService.getInstance().login(id, pass);
		if(vo == null) {
			session.setAttribute("login", false);
		}else {
			session.setAttribute("login", true);
			session.setAttribute("id", id);
			session.setAttribute("name", vo.getName());
			session.setAttribute("grade", vo.getGrade());
		}
		ModelAndView view = new ModelAndView();
		view.setSendRedirect(true);
		if(request.getParameter("url")==null)
			view.setPage("../index.jsp");
		else
			view.setPage(request.getParameter("url")+"?bNo="+request.getParameter("bNo"));
		return view;
	}

}
