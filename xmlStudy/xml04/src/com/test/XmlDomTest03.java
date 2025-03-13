/*============================================
 * XmlDomTest03.java
 * -콘솔 기반 자바 프로그램
 * -XML DOM 활용 → 로컬(local) XML 읽어내기
 * (breakfast_menu.xml)
 ============================================*/

//breakfast_menu.xml 파일 대상으로
/*
 * [Belgian Waffles] $5.95  650칼로리
 * -Two of our famous Belgian Waffles with plenty of real maple syrup
 * 
 * */

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest03
{
	public static void main(String[] args)
	{
		try
		{
			//1.xml파일 dom객체로 가져 오기
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			//2. 객체에 xml파일 주입
			String  url = "breakfast_menu.xml";
			xmlObj = builder.parse(url);
			
			//3. root 엘리먼트 추출
			Element root = xmlObj.getDocumentElement();
			
			NodeList foodList = root.getElementsByTagName("food");
			
			for (int i = 0; i < foodList.getLength(); i++)
			{
				Node foodNode = foodList.item(i);
				
				Element foodElement = (Element)foodNode;
				
				String name = "[" +getText(foodElement, "name") + "]";
				String price = getText(foodElement, "price");
				String calories = getText(foodElement, "calories")+ "칼로리";
				String description = getText(foodElement, "description");
				
				System.out.printf("%s %s %s \n-%s \n",name,price,calories,description);
				System.out.println();
				
			}
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	private static String getText(Element parent, String tagName) {
		
		String result = "";
		
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		result = element.getChildNodes().item(0).getNodeValue();
		
		return result;
	}
}
