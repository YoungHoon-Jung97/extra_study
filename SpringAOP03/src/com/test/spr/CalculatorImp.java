/*==================
 * CalculatorImp.java
 * -클래스
 * -Calculator 인터페이스를 구현하는 클래스
 * -주 업무 구조로 구성.
 *  (Spring AOP 기법 적용 이전)
 * 
 ===================*/

package com.test.spr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

public class CalculatorImp implements Calculator
{
	//인터 페이스로부터 상속답은 메소드 재정의를 통해
	//주 업무 진행을 위한 메소드 구현
	@Override
	public int add(int x, int y)
	{
		int result = 0;
		
		
		//추가
		//※ 주 업무 처리 과정에서
		// 예외가 발생할 수 있는 상황을 만들기 위해
		//	예외 객체를 생성하여 처리할 수 있도록 구성
		// -try ~  catch ~ finally : 예외 발생 시 자체적을 처리
		// -gh
		
		if(x>100 || y>100)
		{
			
			throw new IllegalArgumentException(" 100 보다 큰 인자를 보내 오류 발생");
		}
		
		
		//주 업무
		result = x+ y ;
		System.out.printf("%d + %d = %d" ,x,y,x+y);
	
		
		return result;
	}

	@Override
	public int sub(int x, int y)
	{
		int result = 0;
		
		//주 업무
		result = x+ y ;
		System.out.printf("%d - %d = %d" ,x,y,x-y);
		

		return result;
	}

	@Override
	public int multi(int x, int y)
	{
		int result = 0;
		
		
		//주 업무
		result = x+ y ;
		System.out.printf("%d * %d = %d" ,x,y,x*y);
		
		return result;
	}

	@Override
	public int div(int x, int y)
	{
		int result = 0;
		
		//주 업무
		result = x+ y ;
		System.out.printf("%d / %d = %d" ,x,y,x/y);
		
		
		return result;
	}
	
}
