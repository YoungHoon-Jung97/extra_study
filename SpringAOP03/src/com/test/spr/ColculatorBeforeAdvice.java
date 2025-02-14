/*===============================
 * ColculatorBeforeAdvice.java
 * -Before Advice 구성
 ===============================*/

package com.test.spr;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

//※『MethodBeforeAdvice』인터페이스 구현

public class ColculatorBeforeAdvice implements MethodBeforeAdvice
{

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
	{
		Log log  = LogFactory.getLog(this.getClass());
		log.info("Before Advice 실행------------------------------");
		log.info("주 업무 실행전에 수행되어야 하는 업무");
		log.info("------------------------------Before Advice 실행");
		
	}
	
}
