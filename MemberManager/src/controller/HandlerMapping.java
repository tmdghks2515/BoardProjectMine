package controller;

import controller_board.BoardListController;
import controller_board.BoardViewController;
import controller_board.BoardWriteController;
import controller_board.BoardWriteView;
import controller_member.LoginController;
import controller_member.LogoutController;
import controller_member.RegisterController;
import controller_member.UpdateActionController;
import controller_member.UpdateViewController;
import controller_member.idCheckController;
import controller_qna.AskController;
import controller_qna.QnAViewController;
import controller_qna.ReadQnAController;
import controller_qna.ResponseController;
import controller_qna.ViewMoreController;

public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {}

	public static HandlerMapping getInstance() {
		if(instance == null)
			instance = new HandlerMapping();
		return instance;
	}
	
	//원하는 컨트롤러 찍어내는 메서드
	public Controller createController(String cmd) {
		Controller co = null; 
		switch(cmd) {
		case "login.do":
			co = new LoginController();
			break;
		case "logout.do":
			co = new LogoutController();
			break;
		case "register.do":
			co = new RegisterController();
			break;
		case "idcheck.do":
			co = new idCheckController();
			break;
		case "update.do":
			co = new UpdateViewController();
			break;
		case "updateAction.do":
			co = new UpdateActionController();
			break;
		case "ask.do":
			co = new AskController();
			break;
		case "qnaView.do":
			co = new QnAViewController();
			break;
		case "view_more.do":
			co = new ViewMoreController();
			break;
		case "response.do":
			co = new ResponseController();
			break;
		case "read.do":
			co = new ReadQnAController();
			break;
		case "board_list.do":
			co = new BoardListController();
			break;
		case "boardWriteView.do":
			co = new BoardWriteView();
			break;
		case "boardWrite.do":
			co = new BoardWriteController();
			break;
		case "boardView.do":
			co = new BoardViewController();
			break;
		case "commentWrite.do":
			co = new commentWriteController();
			break;
		case "boardLike.do":
			co = new BoardLikeController();
			break;
		case "commentLike.do":
			co = new CommentLikeController();
			break;
		}
		
		
		return co;
	}
	
}
