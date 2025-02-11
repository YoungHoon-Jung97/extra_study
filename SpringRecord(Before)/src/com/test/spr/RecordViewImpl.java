/*
 * 
 * RecordViewImpl1.java
 * -RecordView인터페이스를 구현하는 클래스로 설계
 * 
 * */

package com.test.spr;

import java.util.Scanner;

public class RecordViewImpl implements RecordView
{
	//check~!!!
	//주요 속성 구성 → 인터페이스 형태의 자료형 구성
	private Record record;

	@Override
	public void setRecord(Record record)
	{
		this.record = record;
		
	}

	@Override
	public void input()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println(">>국어 영어 수학 점수 입력(공백구분):");
		String kor=sc.next();
		String eng=sc.next();
		String mat=sc.next();
		
		record.setKor(Integer.parseInt(kor));
		record.setEng(Integer.parseInt(eng));
		record.setMat(Integer.parseInt(mat));
		
		sc.close();
		
	}

	@Override
	public void output()
	{
		System.out.println(" 국어 영어 수학 총점 평균");
		System.out.printf("%6d %5d %5d %6d %6.1f",
					record.getKor(),record.getEng(),record.getMat(),
					record.getTot(),record.getAvg());
		
	}
	
	
}
