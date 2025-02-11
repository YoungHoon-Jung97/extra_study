/*
 * Main.java
 * -클래스
 * -main()메소드가 포함된 테스트 클래스
 * 
 * */


package com.test.spr;

public class Main
{
	public static void main(String[] arges) {
		
		//스프링 처리하고 관리하게 될 영역
		//----------------------------------------------
		//RecordImpl1(→ Record)객체 생성 과정
		//--RecordView입장에서 의존 객체(dependency)
		Record rec1 = new RecordImpl1();
		
		//RecordImpl2(→ Record)객체 생성 과정
		//--RecordView입장에서 의존 객체(dependency)
		Record rec2 = new RecordImpl2();
		
		//--RecordViewImpl(→RecordView)객체 생성 과정
		//--Record 객체 (의존 객체)필요
		RecordView view = new RecordViewImpl();
		
		//의존 객체 주입(injection)
		view.setRecord(rec2);	//setter injection(setter 를 통한 주입)
		//--의존객체 주입 방법을 사용하게 되면
		// 느슨한 결합을 구성할 수 있게 되고
		// 한 쪽의 변동 상황이 다른 한 쪽에 영향을 덜 줄 수 있게 된다.
		
		//----------------------------------------------
		view.input();
		view.output();
		
	}	
	
}
