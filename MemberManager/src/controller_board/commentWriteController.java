package controller_board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import controller.Controller;
import dto.CommentDTO;
import model.ModelAndView;
import service.BoardService;

public class commentWriteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String comment = request.getParameter("comment");
		String writer = request.getParameter("writer");
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		CommentDTO cdto = new CommentDTO(bNo, comment, writer);
		try {
			BoardService.getInstance().insertComment(cdto);
			ArrayList<CommentDTO> cli =  BoardService.getInstance().selectAllComment(bNo);
			request.getSession().setAttribute("cli", cli);
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			for(CommentDTO dto : cli) {
				JSONObject temp = new JSONObject();
				temp.put("cdto", dto);
				ja.put(temp);
			}
			jo.put("result", ja);
			response.getWriter().write(jo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
