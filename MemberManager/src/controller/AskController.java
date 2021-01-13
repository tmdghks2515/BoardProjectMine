package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelAndView;
import service.QnAService;

public class AskController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = (String)request.getSession().getAttribute("id");
		ModelAndView view = null;
			try {
				if(title.length()==0  || content.length()==0 || writer==null) {
					response.getWriter().append("<script>alert('내용을 입력하세요');history.back();</script>");
					return null;
				}else {
					QnAService.getInstance().insertQnA(title,content,writer);
					view = new ModelAndView("qnaView.do", true);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return view;
	}

}
