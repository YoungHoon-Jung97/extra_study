/*============================================
   MemberDTO.java
   - TBL_MEMBERLIST 테이블 레코드 구조 참조
=============================================*/

package com.test.spr;

public class MemberDTO
{
	// 주요 속성 구성 → TBL_MEMBERLIST 테이블 레코드 형식
	private String id,pw,name,tel,email;

	// 사용자 정의 생성자 없음 → 기본 생성자(default) 자동 삽입
	
	// getter / setter 구성
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getPw()
	{
		return pw;
	}

	public void setPw(String pw)
	{
		this.pw = pw;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
	
	
}