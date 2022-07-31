package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.DuplicateFormatFlagsException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

public class Main {
	
	private static AnnotationConfigApplicationContext ctx;

	public static void main(String[] args) throws IOException{
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" "));
			}else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
			}else if(command.startsWith("list")) {
				processListCommand();
			}else if(command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
			}
			
		}
	}

	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regSrv = ctx.getBean("memberRegisterService", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.");
			return;
		}
		try {
			regSrv.regist(req);
			System.out.println("등록했습니다.");
		}catch(DuplicateFormatFlagsException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}

	private static void processChangeCommand(String[] arg) {
		ChangePasswordService pwdSrv = ctx.getBean(ChangePasswordService.class);
		try {
			pwdSrv.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("비밀번호를 변경했습니다.");
		}catch(WrongIdPasswordException e) {
			System.out.println("이메일과 비밀번호가 맞지 않습니다.");
		}
	}
	
	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean(MemberListPrinter.class);
		listPrinter.printAll();
	}

	private static void processInfoCommand(String[] arg) {
		MemberInfoPrinter infoPrinter = ctx.getBean(MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}
	

	private static void printHelp() {
		System.out.println("-------명령어 모음-------");
		System.out.println("1: new 이메일 이름 비밀번호 비밀번호확인");
		System.out.println("2: change 이메일 이전비밀번호 바꿀비밀번호");
		System.out.println("3: list");
		System.out.println("4: info 이메일");		
		System.out.println("5: exit");
	}

}
