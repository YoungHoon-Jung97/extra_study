/*===============================
 * Department.java
 * -부서 데이터 자료형 클래스
 * -DTO활용
=================================*/

package com.test.mvc;

public class Department
{
	//주요 속성 구성
	private String departmentId, departmentName;		//--부서 아이디, 부성명
	private int delCheck;								//--삭제 가능 여부에 대한 확인
	//--참조되고 있는 경우 (→ 1이상) 삭제 불가능
	// 그렇지 않은 경우(→ 0)만 삭제 가능
	
	
	public String getDepartmentId()
	{
		return departmentId;
	}
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}
	public String getDepartmentName()
	{
		return departmentName;
	}
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}
	public int getDelCheck()
	{
		return delCheck;
	}
	public void setDelCheck(int delCheck)
	{
		this.delCheck = delCheck;
	}
	
	

}
