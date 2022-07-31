package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	// 스프링과 자바의 버전이 안맞으면 실행이 안될 수 있다.
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		Greeter g = ctx.getBean("greeter", Greeter.class);
		String msg = g.greet("스프링");
		System.out.println(msg);
		ctx.close();
	}
}
