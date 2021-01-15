package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.QnADTO;
import model.ModelAndView;
import service.QnAService;
import vo.PagingVO;

public class QnAViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		String id = (String)request.getSession().getAttribute("id");
		if(id==null) {
			try {
				response.getWriter().append("<script>alert('로그인이 필요한 작업입니다.');location='"+request.getContextPath()+"/member/login.jsp';</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ArrayList<QnADTO> li = null;
		try {
			if(request.getParameter("page")!=null) {
				li = QnAService.getInstance().selectQnAByPage(Integer.parseInt(request.getParameter("page")));
				request.getSession().setAttribute("pagingVO", new PagingVO(QnAService.getInstance().selectAllQnA("%").size() , Integer.parseInt(request.getParameter("page"))));
			}else {
				li = QnAService.getInstance().selectQnAById(id,1);
				request.getSession().setAttribute("pagingVO", new PagingVO(QnAService.getInstance().selectAllQnA("%").size() , 1));
			}
			request.getSession().setAttribute("li", li);
			view = new ModelAndView(request.getContextPath()+"/qna/qna_view.jsp", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

}
