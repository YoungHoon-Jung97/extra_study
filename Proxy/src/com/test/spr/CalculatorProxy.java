/*=======================================
   CalculatorProxy.java
   - 프록시 클래스
   - 보조 업무 적용 및 주 업무 호출 과정
========================================*/

// ※ Proxy 클래스를 만드는 여러 가지 방법들 중 비교적 쉽고 직관적인 방법은
//    InvocationHandler 인터페이스를 구현하는 클래스를 만드는 것이다.

package com.test.spr;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

public class CalculatorProxy implements InvocationHandler
{
	// 주요 속성 구성
	
	// target → (proxy 내부에서)가짜가...진짜 행세를 하게 될 대상
	private Object target;
	
	// 생성자 정의
	public CalculatorProxy(Object target)
	{
		this.target = target;
	}

	// InvocationHandler 인터페이스의 메소드 재정의
	// -- 보조 업무 적용 및 주 업무 호출 과정 추가
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		// 보조 업무 (cross-cutting concern) 설정
		//-- 시간 측정 및 로그 기록
		Log log = LogFactory.getLog(this.getClass());	//-- 시작할 때는 공장부터 짓자~
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("처리 시간 측정 시작 ---------------");
		
		// 주 업무(core concern) 실행 내용
		Object result = method.invoke(target, args);
		//                                    ---- 여러가지 속성들
		
		sw.stop();
		log.info("처리 시간 측정 종료 ---------------");
		log.info(String.format("경과시간 : %s/1000초", sw.getTotalTimeMillis()));
		
		return result;
	}
}