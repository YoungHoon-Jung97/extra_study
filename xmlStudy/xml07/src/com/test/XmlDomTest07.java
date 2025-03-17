/*=======================================================
 * XmlDomTest07.java
 * -콘솔 기반 자바 프로그램
 * -XML DOM 활용 → 원격(remote) XML 읽어내기
 * 	https://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=184
 * 	.rss.xml  →  기상청 날씨누리 육상 중기 예보
 ==========================================================*/

/*
 stnId= 108 전국
 
 stnId= 109 서울 ,경기
 
 stnId= 105 강원
 
 stnId= 131 충북
 
 stnId= 133 충남
 
 stnId= 146 전북
 
 stnId= 156 전남
 
 stnId= 143 경북
 
 stnId= 159 경남
 
 stnId= 184 제주특별자치도
 */

package com.test;

import java.net.URL;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlDomTest07
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String[] stnId =
		{ "108", "109", "105", "131", "133", "146", "156", "143", "159", "184" };

		do
		{
			try
			{
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document xmlObj = null;

				// 로컬 XML 파일인 경우
				/*
				 * String url = "rss.xml"; xmlObj = builder.parse(url);
				 */

				// 원격 XML 파일인 경우
				// stnId 를 외부 입력된 값으로 처리
				System.out.println("\n\n■■■기상청 육상 중기 예보■■■");
				System.out.println("==============================================================");
				System.out.println("1. 전국");
				System.out.println("2. 서울, 경기");
				System.out.println("3. 강원");
				System.out.println("4. 충북");
				System.out.println("5. 충남");
				System.out.println("6. 전북");
				System.out.println("7. 전남");
				System.out.println("8. 경북");
				System.out.println("9. 경남");
				System.out.println("10. 제주특별자치도");
				System.out.println("----------------------------");
				System.out.println(">> 지역 선택(종료 0) :");

				String selectMenu = sc.next();
				if (selectMenu.equals("0"))
				{
					break;
				}
				
				String str = String.format("https://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=%s",
						stnId[Integer.parseInt(selectMenu)-1]);
				
				
				//확인
				//System.out.println(str);
				//--==>>https://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108
				
				//check
				//URL 객체 구성
				URL url = new URL(str);
				
				//check
				//구성된 URL로 접근하여 데이터 내용 읽어오기 → 스트림(stream)
				InputSource is = new InputSource(url.openStream());
				xmlObj = builder.parse(is);
				
				
				// 2. 루트엘리먼트 접근하기
				Element root = xmlObj.getDocumentElement();

				// 3. 아이텀 하위의 타이틀 추출하기
				Node itemNode = root.getElementsByTagName("item").item(0);
				Element itemElement = (Element) itemNode;

				System.out.printf("%s\n\n", XMLDOM.getText(itemElement, "title"));
				// --==>>전국 육상 중기예보 - 2025년 03월 17일 (월)요일 06:00 발표

				// 4. 기상전망(wf) 추출하기
				Node wfNode = root.getElementsByTagName("wf").item(0);
				Element wfElement = (Element) wfNode;

				System.out.println(
						"[기상 전망] ====================================================================================");
				System.out.printf(" %s\n\n", wfElement.getTextContent().replaceAll("<br />", "\n"));
				// --==>>
				/*
				 * ○ (하늘상태) 전국이 대체로 맑겠으나, 24일(월)~26일(수)은 구름많겠습니다. ○ (기온) 이번 예보기간 아침 기온은 1~11도, 낮
				 * 기온은 12~22도로 평년(최저기온 0~7도, 최고기온 11~16도)보다 높겠습니다. ○ (주말전망) 22일(토)~23일(일)은 전국이
				 * 대체로 맑겠고, 아침 기온은 1~10도, 낮 기온은 14~22도가 되겠습니다.
				 * 
				 */

				// 5. 도시별 기상예보 추출하기
				System.out.println(
						"[육상 날씨] ====================================================================================");
				NodeList locationNodeList = root.getElementsByTagName("location");

				// 확인
				// System.out.println(locationNodeList.getLength());
				// --==>>41
				for (int i = 0; i < locationNodeList.getLength(); i++)
				{
					Node locationNode = locationNodeList.item(i);
					Element locationElement = (Element) locationNode;

					System.out.printf("도시 : %s\n", XMLDOM.getText(locationElement, "city"));

					// teEf, wf, tmn, tmx, rnSt

					NodeList dataNodeList = locationElement.getElementsByTagName("data");

					// 확인
					// System.out.println(dataNodeList.getLength());
					// --==>>11

					for (int j = 0; j < dataNodeList.getLength(); j++)
					{
						Node dataNode = dataNodeList.item(j);
						Element dataElement = (Element) dataNode;

						System.out.printf("\t%s ", XMLDOM.getText(dataElement, "tmEf"));
						System.out.printf("%s ", XMLDOM.getText(dataElement, "wf"));
						System.out.printf("%s ", XMLDOM.getText(dataElement, "tmn"));
						System.out.printf("%s ", XMLDOM.getText(dataElement, "tmx"));
						System.out.printf("%s ", XMLDOM.getText(dataElement, "rnSt"));
						System.out.println();
					}
					System.out.println("--------------------------------------------------");
				}
				
			} catch (Exception e)
			
			{
				System.out.println(e.toString());
			}
		} while (true);
	}

}
