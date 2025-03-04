/*==================================
 * MemberDTO.java
 * -데이터 저장 및 전송 객체 활용
==================================*/

package com.test.mybatis;

public class MemberDTO
{
	//주요 객체 구성
	private String mid,name,telephone;

	//사용자 정의 생성자 정의 안함
	//→default 생성자 자동 삽입
	
	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}
	
	
}
