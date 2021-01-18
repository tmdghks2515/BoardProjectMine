package controller_member;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.ModelAndView;
import service.MemberService;

public class RegisterController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		try {
			MemberService.getInstance().insert(request.getParameter("id"), request.getParameter("pass"), request.getParameter("name"), Integer.parseInt(request.getParameter("age")));
			view = new ModelAndView("login.jsp", true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().append("<script>alert('이미 존재하는 아이디 입니다.');history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return view;
	}

}
