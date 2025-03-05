/*
 * GradeDTO.java
 * */

package com.test.mybatis;

public class GradeDTO
{
	//주요 속성 구성
	private String sid, name, sub1, sub2, sub3, tot, avg, ch;

	//※참고
	//원칙적으로는 각 데이터 타입이 존재하고
	//여기에 맞추어 속성 타입을 지정하는 것이 바람직하다.
	//(String , int, double 등)
	//하지만, 오라클에서 NUMBER 로 구성되어 있는 정수 및 실수를
	//숫자로 구성하는 과저에서 null 에 대한 제어가 까다로워질 수 있다.
	//이와 같은 이유로 실무에서 편의상 String으로 구성하는 경우가 많다.
	//날짜(date) 타입도 이에 해당한다고 할 수 있다.
	
	//즉, sub1,sub2,sub3, tot,avg등과 같이
	//상황에 따라 null 이 적용될 가능성이 농후한 컬럼의 경우
	//String자료형을 사용하는 것이 개발 과정에서의 편의성을
	//향상시킬수 있다.
	public String getSid()
	{
		return sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSub1()
	{
		return sub1;
	}

	public void setSub1(String sub1)
	{
		this.sub1 = sub1;
	}

	public String getSub2()
	{
		return sub2;
	}

	public void setSub2(String sub2)
	{
		this.sub2 = sub2;
	}

	public String getSub3()
	{
		return sub3;
	}

	public void setSub3(String sub3)
	{
		this.sub3 = sub3;
	}

	public String getTot()
	{
		return tot;
	}

	public void setTot(String tot)
	{
		this.tot = tot;
	}

	public String getAvg()
	{
		return avg;
	}

	public void setAvg(String avg)
	{
		this.avg = avg;
	}

	public String getCh()
	{
		return ch;
	}

	public void setCh(String ch)
	{
		this.ch = ch;
	}
	
	
	
	
}
