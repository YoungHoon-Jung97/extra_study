/*
 ======================================
 	Position.java
 	-직위 데이터 자료형 클래스
 	DTO활용
 ======================================
 */

package com.test.mvc;

public class Position
{
	//주여 속성 구성
	private String positionId, positionName;	//--직위명, 직위 아이디
	private int minBasicPay;					//--최소 기본급
	private int delCheck;						//--삭제 가능 여부에 대한 확인
	//--참조되고 있는 경우 (→ 1이상) 삭제 불가능
	// 그렇지 않은 경우(→ 0)만 삭제 가능
	
	public String getPositionId()
	{
		return positionId;
	}
	public void setPositionId(String positionId)
	{
		this.positionId = positionId;
	}
	public String getPositionName()
	{
		return positionName;
	}
	public void setPositionName(String positionName)
	{
		this.positionName = positionName;
	}
	public int getMinBasicPay()
	{
		return minBasicPay;
	}
	public void setMinBasicPay(int minBasicPay)
	{
		this.minBasicPay = minBasicPay;
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
