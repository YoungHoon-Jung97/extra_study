/*
 * Main.java
 * -클래스
 * -mian()메소드가 포함된 테스트 클래스
 * 
 * */

package com.test.spr;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import java.lang.reflect.Proxy;

public class Main
{
	public static void main(String[] args) {
		
		//『인터페이스 변수 = new 인터페이스구현클래스();』
		//Calculator cal = new CalculatorImp();
		
		//메소드 호출를 통해 업무 실행 테스트
		/*
		int add= cal.add(10, 20);
		System.out.println(add);
		
		int sub= cal.sub(10, 20);
		System.out.println(sub);
		
		int multi= cal.multi(10, 20);
		System.out.println(multi);
		
		int div= cal.div(10, 20);
		System.out.println(div);
		*/
	
		
		
		// Proxy.newProxyInstance(loader, interfaces, h)
		// Proxy.newProxyInstance(ⓐ, ⓑ, ⓒ)
		
		// ⓐ: (loader) 주 업무 실행 클래스에 대한 정보 전달(제공)
		//	cal.getClass().getClassLoader()	
		//  ---/----------/----------------
		//주 업무 객체(대상) / 설계도 / 클래스를 읽어오는 스캐너
		
		// ⓑ: (interfaces) 주 업무 실행 클래스의 인터페이스에 대한 정보 전달(제공).
		// cal.getClass().getInterfaces()	// 주 업무 실행 클래스의 인터페이스들에 대한 정보 전달(제공) 
		//---/----------/---------------
		//주 업무 객체(대상) / 설계도 / 클래스가 implements 하고있는 인터페이스(들) 정보를 읽어오는 스캐너
		
		// ⓒ: (h) 보조 업무 실행 클래스에 대한 정보 전달(제공).
		// new CalculatorProxy(cal));	
        //-- 가짜 (틀) 을 가져와서 결합시키는 과정
		
		/*
		Calculator cal2 = (Calculator)Proxy.newProxyInstance(cal.getClass().getClassLoader()
				, cal.getClass().getInterfaces()
				, new CalculatorProxy(cal));
		
		int add= cal2.add(10, 20);
		System.out.println(add);
		
		int sub= cal2.sub(10, 20);
		System.out.println(sub);
		
		int multi= cal2.multi(10, 20);
		System.out.println(multi);
		
		int div= cal2.div(10, 20);
		System.out.println(div);
		*/
		
		
		//주 업무 실행에 대한 테스트(Spring AOP 기법 적용 후)
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		
		//Spring 에서 제공하는 proxy를 활용하여
		//보조 업무와 결합된 형태로 주 업무 처리
		//『인터페이스 변수  =  new 인터페이스를 구현한 클래스();』
		Calculator cal = context.getBean("proxy" ,Calculator.class);
		
		int add= cal.add(10, 20);
		System.out.println(add);
		
		int sub= cal.sub(10, 20);
		System.out.println(sub);
		
		int multi= cal.multi(10, 20);
		System.out.println(multi);
		
		int div= cal.div(10, 20);
		System.out.println(div);
		
		//예외 상황이 발생할 수 있도록 값을 구성하는(넘기는) 처리 추가
		int add2 = cal.add(100, 200);
		System.out.println(add2);
		
		
	}
}
