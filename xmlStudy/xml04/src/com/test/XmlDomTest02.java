/*============================================
 * XmlDomTest02.java
 * -콘솔 기반 자바 프로그램
 * -XML DOM 활용 → 로컬(local) XML 읽어내기
 * (memberList.xml)
 ============================================*/

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest02
{
	public static void main(String[] args)
	{
		//김상기	010-1213-4546
		//김민성	010-5678-6789
		
		try
		{
			//1. xml 파일을 메모드리에 로드
			//		→ XML DOM 형식으로
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			
			//2. 루트 엘리먼트에 접근
			String url = "memberList.xml";
			xmlObj = builder.parse(url);
			
			//3. 특정 하위 엘리먼트 접근
			Element root = xmlObj.getDocumentElement();
			
			//4. 텍스트 노드 접근
			NodeList memberList = root.getElementsByTagName("memberInfo");
			
			//5. 데이터 획득
			for (int i = 0; i < memberList.getLength(); i++)
			{
				Node memberListNode = memberList.item(i);
				
				
				Element memberListElement =  (Element)memberListNode;
				
				System.out.printf("%s	%s\n",getText(memberListElement, "name"),getText(memberListElement, "telephone"));
				
				
				
				//케러큘럼에 대한 처리 추가-----------------------------------------------------------------------------------
				//memberInfoElement로 부터 courriculumn NodeList 얻어 오기
				NodeList curriculumnNodeList = memberListElement.getElementsByTagName("curriculumn");
				
				if (curriculumnNodeList.getLength()>0)
				{
					Node curriculumnNode = curriculumnNodeList.item(0);
					Element curriculumnElement = (Element)curriculumnNode;
					
					//방법 1.
					/*
					 * NodeList subNodeList = curriculumnElement.getElementsByTagName("sub"); 
					 * for(int j = 0; j < subNodeList.getLength(); j++) {
					 * 		Node subNode = subNodeList.item(j);
					 *  	Element subElement = (Element)subNode;
					 * 		System.out.printf("%s",subElement.getTextContent());
					 * } 
					 * System.out.println();
					 */
					
					//방법 2.
					/*
					 * ----------------------------------------------------------------------------
					 * Node Type			Named Constant
					 * ----------------------------------------------------------------------------
					 * 1					ELEMENT_NODE
					 * 2					ATTRUBUTE_NODE
					 * 3					TEXT_NODE
					 * 4					CDATA_SECTION_NODE
					 * 5					ENTITY_REFERENCE_NODE
					 * 6					PROCESSING_INSTRUCTION_NODE
					 * 7					COMMIT_NODE
					 * 8					DOCUNENT_TYPE_NODE
					 * 9					DOCUMENT_FRAGMENT_NODE
					 * 10					NOTATION_NODE
					 * */
					 NodeList subNodeList = curriculumnElement.getChildNodes(); // check~~
					 for (int j = 0; j < subNodeList.getLength(); j++)
					 {
						Node subNode = subNodeList.item(j);
						if (subNode.getNodeType()== 1) //-- ELEMENT_NODE 			check~~
						{
							Element subNodeElement = (Element)subNode;
							System.out.printf(" -%s\n",subNodeElement.getTextContent());
						}
					 }
					 System.out.println();
					
				}
				
				
				//-----------------------------------------------------------------------------------커리큘럼에 대한 처리 추가
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	private static String  getText(Element parent, String tagName) {
		
		String result = "";
		
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		result = element.getChildNodes().item(0).getNodeValue();
		
		return result;
	}
}
