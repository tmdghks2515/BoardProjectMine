package controller;

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
		}
		
		
		return co;
	}
	
}
