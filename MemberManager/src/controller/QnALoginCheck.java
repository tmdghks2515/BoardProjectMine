package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelAndView;

public class QnALoginCheck implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		if(request.getSession().getAttribute("login")!=null && 
				(boolean)request.getSession().getAttribute("login")) {
			view = new ModelAndView(request.getContextPath()+"/qna/qna_view.jsp", true);
		}else {
			try {
				response.getWriter().append("<script>alert('로그인이 필요한 작업 입니다.');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return view;
	}

}
