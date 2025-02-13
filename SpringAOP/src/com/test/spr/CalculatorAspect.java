/*
 * CalculatorAspect.java
 * -보조 업무 수행 클래스
 * -보조 업무 적용 및 주 업무 호출 구문
 */
 

package com.test.spr;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

//※ Spring AOP Proxy 클래스를 설계하기 위해
//	 MethodInterceptor 인터페이스를 구현하는 클래스로 만든다.

public class CalculatorAspect implements MethodInterceptor
{

	//MethodInterceptor 인터페이스의 invoke() 메소드 재정의
	
	//public Object invoke(MethodInvocation arg0) throws Throwable
	@Override
	public Object invoke(MethodInvocation method) throws Throwable
	{
		//반환할 결과값
		Object result = null;
		
		//보조 업무 실정
		Log log =  LogFactory.getLog(this.getClass());		//로그 기록 처리를 위한 리소스
		StopWatch sw = new StopWatch();						//스톱워치 시간 측정을 위한 리소스
		
		//보조 업무 처리
		sw.start();
		log.info("처리 시간 측정 시작==========================");
		
		
		
		//주 업무 실행 내용
		result = method.proceed();
		
		
		//보조 업무 처리
		sw.stop();
		log.info("처리 시간 측정 종료==========================");
		log.info(String.format("경과시간 : %s/1000초", sw.getTotalTimeMillis()));
		
		//최종 결과값 반환
		return result;
	}
}
