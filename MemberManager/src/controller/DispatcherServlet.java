package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import model.ModelAndView;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet({"/","*.do"})
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String[] arr = request.getRequestURI().split("/");
		ModelAndView view = null;
		Controller co = HandlerMapping.getInstance().createController(arr[arr.length-1]);
		if(co != null)
			view = co.execute(request, response);
		if(view == null)
			view = new ModelAndView("index.jsp", true);
		if(view.isSendRedirect()) {
			response.sendRedirect(view.getPage());
		}else {
			request.getRequestDispatcher(view.getPage()).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
