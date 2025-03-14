/*================
 * XmlDom.java
 =================*/

package com.test;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlDom
{
	public static String getText(Element parent, String tagName)
	{
		//반환할 결과 값
		String result= "";
		
		//대상 태그(tagName) 객체의 첫 번째 자식 노드 얻어내기
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element= (Element)node;
		
		//result = element.getChildNodes().item(0).getNodeValue();		
		result = element.getTextContent();
		//최종 결과 값
		return result;
	}

}
