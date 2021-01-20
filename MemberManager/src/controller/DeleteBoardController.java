package controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.FileDTO;
import model.ModelAndView;
import service.BoardService;

public class DeleteBoardController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		ArrayList<FileDTO> fList = BoardService.getInstance().selectFiles(bNo);
		if(fList.size() > 0) {
			for(int i=0;i<fList.size();i++) {
				File file = new File(fList.get(i).getPath());
				if(file.exists())
					file.delete();
			}
		}
		BoardService.getInstance().deleteBoard(bNo);
		return new ModelAndView("board_list.do", true);
	}

}
