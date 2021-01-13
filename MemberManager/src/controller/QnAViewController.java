package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.QnADTO;
import model.ModelAndView;
import service.QnAService;

public class QnAViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		//아이디에 해당되는 문의 목록을 조회 처음에는 페이지번호 1번
		//단. 관리자일때는 모든 사용자의 문의 목록을 읽어옴
		ModelAndView view = null;
		int page = 1;
		if(request.getParameter("page")!=null)
			page = Integer.parseInt(request.getParameter("page"));
		String id = (String)request.getSession().getAttribute("id");
		if(id==null) {
			return new ModelAndView("index.jsp", true);
		}
		ArrayList<QnADTO> li = null;
		try {
			li = QnAService.getInstance().selectQnAById(id,page);
			request.getSession().setAttribute("li", li);
			view = new ModelAndView(request.getContextPath()+"/qna/qna_view.jsp", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

}
