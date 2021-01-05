package survice;

import dao.MemberDAO;
import vo.MemberVO;

public class MemberService {
	private static MemberService instance = new MemberService();

	private MemberService() {
	}

	public static MemberService getInstance() {
		if(instance == null)
			instance = new MemberService();
		return instance;
	}
	
	public MemberVO login(String id, String pass) {
		MemberVO vo = MemberDAO.getInstance().login(id, pass);
		return vo;
	}
	
	public void insert(String id, String pass, String name, int age) {
		MemberDAO.getInstance().insert(id,pass,name,age);
	}
	
	public MemberVO select(String id) {
		MemberVO vo = MemberDAO.getInstance().select(id);
		return vo;
	}
}
