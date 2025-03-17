/*
 * XmlDomTest06.java
 * -콘솔 기반 자바 프로그램
 * -XML DOM 활용 → 다운로드 받은 로컬(local) XML 읽어내기
 * 	.rss.xml  →  기상청 날씨누리 육상 중기 예보
 * */

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest06
{
	public static void main(String[] args)
	{
		try
		{
			// 1. XML 파일 메모리에 로드하기
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			String url = "rss.xml";
			Document xmlObj = builder.parse(url);

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

				
				/*
				Node dataNode = dataNodeList.item(0);
				Element dataElement = (Element) dataNode;

				NodeList childNodeList = dataElement.getChildNodes();
				for (int j = 0; j < childNodeList.getLength(); j++)
				{
					Node childNode = childNodeList.item(j);

					if (childNode.getNodeType() == Node.ELEMENT_NODE)
					{
						Element childElement = (Element) childNode;
						System.out.printf("%s : %s\n", childElement.getNodeName(), childElement.getTextContent());
					}
				}
				*/
			}

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
