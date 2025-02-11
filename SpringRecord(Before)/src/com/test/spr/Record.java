/*=============================
 * Record.java
 * -인터페이스
 * 
 *=============================*/

package com.test.spr;

public interface Record
{
	public void setKor(int kor);
	public int getKor();
	
	public void setEng(int eng);
	public int getEng();
	
	public void setMat(int mat);
	public int getMat();
	
	public int getTot();
	
	public double getAvg();
}
