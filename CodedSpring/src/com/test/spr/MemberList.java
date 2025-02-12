/*=================================
   MemberList.java
   - DAO 클래스를 참조하는 클래스
   - 의존 관계 설정
   - print() 메소드 구현
================================== */

package com.test.spr;

public class MemberList
{
	// IOC/DI 적용 전
	/*
	// 처음 연결하는 클래스 정보
	private OracleDAO dao;
	
	public MemberList()
	{
		//MemberList 입장에서의 의존객체 OracleDAO
		dao = new OracleDAO();
	}
	*/
	// 두번째 연결하는 클래스 정보
	/*
	private MssqlDAO dao;
	
	public MemberList()
	{
		//MemberList 입장에서의 의존객체 MssqlDAO
		dao = new MssqlDAO();
	}
	*/

	// ※ 스프링 IoC/DI 패턴 적용
	
	//private 김향안 향안이;
	//private 김환기 환기;
	//             ↓
	//private 사람 라흐헤스트인물;
	
	// check
	// 멤버 변수를 추가하는 과정에서 데이터 타입은 인터페이스 형식
	// → DI 를 위한 준비 작업
	private IDAO dao;
	
	// check!!
	// setter 메소드 추가
	public void setDao(IDAO dao)
	{
		this.dao = dao;
	}
	
	// print() 메소드 구현 → 수정 사항 없음. 그대로 사용.
	public void print()
	{
		try
		{
			for (MemberDTO dto : dao.list())
			{
				System.out.printf("%14s %4s %15s %15s%n"
							, dto.getId(), dto.getName()
							, dto.getTel(), dto.getEmail());
			}
		} 
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
}