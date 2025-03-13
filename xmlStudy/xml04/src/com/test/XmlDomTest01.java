/*============================================
 * XmlDomTest01.java
 * -콘솔 기반 자바 프로그램
 * -XML DOM 활용 → 로컬(local) XML 읽어내기
 * (VEHICLES.xml)
 ============================================*/

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class XmlDomTest01
{
	public static void main(String[] args)
	{
		// 1.XML 파일을 메모리에 로드
		//			→ XML DOM 형성 (구축)
		// 2. 루트 얼레먼트 접근
		// 3. 특정 하위 엘리먼트 접근
		//			→ 위치, 이름 등을 기준으로 접근
		// 4. 텍스트 노드 (속성 노드) 접근
		// 5. 데이터 획득
		// 6. 결과 처리 (출력)
		
		try
		{
			//XML 파일을 메모리에 로드시킬 수 있도록 준비
			//→ XML DOM형성을 위해 필요한 준비
			//	(이를 위해 필요한 리소스 구성)
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			//XML 파일을 메모리에 로드(적재) → XML DOM 형성(구축)
			String url = "VEHICLES.xml";
			xmlObj = builder.parse(url);
			
			//루트 엘레먼트 접근
			Element root = xmlObj.getDocumentElement();
			
			
			//확인
			//System.out.println(root.getNodeName());
			
			
			//루트 엘릴먼트 통해 하위 얼레먼트 접근(VECHICLE)
			//자식(자손)노드에 접근할 수 있도록 해주는 메소드
			NodeList vehicleNoedList = root.getElementsByTagName("VEHICLE");
			
			//System.out.println(vehicleNoedList.getLength());
			
			for (int i = 0; i < vehicleNoedList.getLength(); i++)
			{
				//NodeList 객체의 item()메소드는
				//파라미터에 해당하는 인덱스의 위치한 노드 접근 메소드
				Node vehicleNode = vehicleNoedList.item(i);
				
				//캐스트 연산자를 이용하면
				//Node 객체를 Element 객체로 변환하는 것이 가능하다.
				//Node 는 상위 자료형, Element는 하위 자료형으로 다루고 있기 때문이다.
				Element vehicleElement = (Element)vehicleNode;
				
				/*
				 * NodeList makeNodeList = vehicleElement.getElementsByTagName("MAKE"); Node
				 * makeNode = makeNodeList.item(0); Element makeElement = (Element)makeNode;
				 * System.out.printf("%s : %s%n",makeElement.getNodeName(),makeElement.
				 * getTextContent());
				 * 
				 * NodeList modelNodeList = vehicleElement.getElementsByTagName("MODEL"); Node
				 * modelNode = modelNodeList.item(0); Element modelElement = (Element)modelNode;
				 * System.out.printf("%s : %s%n",modelElement.getNodeName(),
				 * modelElement.getTextContent());
				 */
				
				System.out.printf("%s %s %s %s %s%n",getText(vehicleElement, "MAKE")
													,getText(vehicleElement, "MODEL")
													,getText(vehicleElement, "YEAR")
													,getText(vehicleElement, "PICTURE")
													,getText(vehicleElement, "STYLE"));
				
			}
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
	}
	private static String getText(Element parent, String tagName)
	{
		String result = "";
		//특정 태그 이름을 가진 객체의 첫 번째 자식 노드를 얻어온 다음
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		//특정 엘리먼트의 자식 노드(Text Node)의 값(nodeValue)를 얻어올 수 
		result = element.getChildNodes().item(0).getNodeValue();
		
		
		return result;
	}
}
