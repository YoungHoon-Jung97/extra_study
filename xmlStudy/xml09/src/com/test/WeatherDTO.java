package com.test;

public class WeatherDTO
{
	//주요 속성 구성
	private String tmEf, wf, tmn, tmx, rnSt, img;
	//--tmEf → 날짜와 시간 태그
	//--wf → 날씨 예보 태그
	//			○ 맑음	○ 구름조금
	//			○ 구름많은, 구름많고 비, 구름많고 비/눈, 구름많고 눈/비, 구름많고 눈
	//			○ 흐림, 흐르고 비, 흐리고 눈/비, 흐리고 비/눈, 흐리고 눈
	//--tmn	→ 최저 온도 태그
	//			6℃
	//--tmx → 최고 온도 태그
	//			16℃
	//--rnSt → 강수 확률 태그
	//			10%
	//--img → 날씨 예보에 따른 이미지

	public String getTmEf()
	{
		return tmEf;
	}

	public void setTmEf(String tmEf)
	{
		this.tmEf = tmEf;
	}

	public String getWf()
	{
		return wf;
	}

	public void setWf(String wf)
	{
		this.wf = wf;
	}

	public String getTmn()
	{
		return tmn;
	}

	public void setTmn(String tmn)
	{
		this.tmn = tmn;
	}

	public String getTmx()
	{
		return tmx;
	}

	public void setTmx(String tmx)
	{
		this.tmx = tmx;
	}

	public String getRnSt()
	{
		return rnSt;
	}

	public void setRnSt(String rnSt)
	{
		this.rnSt = rnSt;
	}

	public String getImg()
	{
		return img;
	}

	public void setImg(String img)
	{
		this.img = img;
	}
	
	
	

}
