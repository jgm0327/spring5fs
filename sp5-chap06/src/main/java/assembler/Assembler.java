package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {
	
	private MemberDao memberDao;
	private MemberRegisterService regsrv;
	private ChangePasswordService pwdsrv;
	
	public Assembler() {
		memberDao = new MemberDao();
		regsrv = new MemberRegisterService(memberDao);
		pwdsrv = new ChangePasswordService();
		pwdsrv.setMemberDao(memberDao);
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	
	public MemberRegisterService getMemberRegisterService() {
		return regsrv;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return pwdsrv;
	}
}
