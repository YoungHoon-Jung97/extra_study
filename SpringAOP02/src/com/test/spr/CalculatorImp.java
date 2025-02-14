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
