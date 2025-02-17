/*=================
 * MemberDTO.java
 ==================*/

package com.test.mvc;

public class MemberDTO
{
	//주요 속성 구성
	private int mid;
	private String name,telephone;
	public int getMid()
	{
		return mid;
	}
	public void setMid(int mid)
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
