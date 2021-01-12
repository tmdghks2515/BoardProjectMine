package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelAndView;
import service.MemberService;
import vo.MemberVO;

public class RegisterController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		MemberVO vo = MemberService.getInstance().select(id);
		ModelAndView view = null;
		if(vo == null) {
			MemberService.getInstance().insert(id, request.getParameter("pass"), request.getParameter("name"), Integer.parseInt(request.getParameter("age")));
			view = new ModelAndView("login.jsp",true);
		}else {
			try {
				response.getWriter().append("<script>alert('이미 존재하는 아이디 입니다.');</script>");
				view = new ModelAndView("register.jsp", true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return view;
	}

}
