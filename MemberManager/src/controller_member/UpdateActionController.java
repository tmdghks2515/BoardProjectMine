package controller_member;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.ModelAndView;
import service.MemberService;
import vo.MemberVO;

public class UpdateActionController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		try {
			if(id==null || pass==null || name==null || request.getParameter("age")==null) {
				response.getWriter().append("<script>alert('모든 정보를 입력해야 합니다.');history.back()");
				return null;
			}
			MemberVO vo = new MemberVO(id, pass, name, age);
			MemberService.getInstance().update(vo);
			request.getSession().setAttribute("name", name);
			view = new ModelAndView("index.jsp", true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}

}
