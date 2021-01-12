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
		}
		
		
		return co;
	}
	
}
