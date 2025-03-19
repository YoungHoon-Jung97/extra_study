/*
 * WeatherDAO.java
 * https://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=184
 * */

package com.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFunction;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WeatherDAO
{
	//주요 속성 구성 → 공통 멤버 구성 → 멤버 변수 → 초기화 → 생성자
	private Document xmlObj;
	private XPath xPath;
	private HashMap<String, String> map;
	
	//생성자 정의 → 기본 생성자 형태
	public WeatherDAO() throws ParserConfigurationException, IOException, SAXException{
		
		this("108");		//-- 기본 생성자 호출시 전국 
	}
	
	//생성자 정의 → 매개변수 있는 생성자 형태
	public WeatherDAO(String stnId) throws ParserConfigurationException, IOException, SAXException {
		map = new HashMap<String , String>();
		
		map.put("맑음", "W_DB01(맑음).png");
		map.put("흐림", "W_DB04(흐림).png");
		map.put("비", "W_DB05(비).png");
		map.put("구름조금", "W_NB02(구름조금).png");
		map.put("구름많음", "W_NB03(구름많음).png");
		map.put("흐리고 비", "W_NB08(흐리고비).png");
		map.put("구림많고 비", "W_NB20(구름많고 비).png");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
		String str = String.format("https://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=%s", stnId);
		
		URL url = new URL(str);
		InputSource is = new InputSource(url.openStream());
		xmlObj = builder.parse(is);
		
		xPath = XPathFactory.newInstance().newXPath();
		//--XPathFactory xFactory = XPathFactory.newInstance();
		//	xPath = xFactory.newPath();
		//	와 동일한 구문
		
		/*
		 ○ XPath 생성
		 	- XPathFactory() 의 정적(static)메소드 newInstance() 호출을 통해
		 	  XPath를 생성하는 XPathFactory 를 생성하고
		 	- 이 XPathFactory 의 정적(static)메소드 newXPath() 호출을 통해
		 	  XPath 객체를 생성한다.
		 	  
		○ 노드 선택(Selecting Nodes)
			- 브라우저마다 XPath를 처리하는  방법에서 차리를 보인다.
			- Chrome, Firefox, Edge,Opera, Safari등은
				exaluation() 메소드를 사용하여 노드를 처리한다.
				→ xml.Doc.evaluation(xPath, xmlDoc,null, XPathResult.ANY_TYPE, null)
			- IE 는 selectNodes() 메소드를 상용하여 노드를 처리한다.
			
		○ XPath 의 complie(XPath 경로 표현식)
			- XML 데이터 구문분석(피싱, parsing)
				'XML 이 제공되는 URL로 접속하여 데이터를 수신한다.
				'DocumentBuilderFactory ... newInstance()로 factory를 생성한다.
				'DocumentBuilder ... new InputSource()로 InputSource를 생성한다.
				'InputSource is ... new InputSource()로 InputSource를 생성한다.
				 이때 , 파일로 수신한 경루라면 File객체를 넘겨준다.
				'Document xmlObj = builder.parse(is)로 xml을 파싱(parsing)한다
		 * */
	}
	
	//<title>서울,경기도 육상 중기예보 - 2025년 03월 19일 (수)요일 06:00 발표</title>
	public String weatherTitle() throws XPathExpressionException {
		
		String result = "";
		
		result = xPath.compile("/rss/channel/item/title").evaluate(xmlObj);
		
		return result;
	}
	
	public String weatherInfo() throws XPathExpressionException {
		String result = "";
		
		result = xPath.compile("/rss/channel/item/description/header/wf").evaluate(xmlObj);
		
		return result;
		
	}
	
	//도시 이름 배열 구성
	public ArrayList<String> weathCityList() throws XPathExpressionException {
		
		ArrayList<String> result = new ArrayList<String>();
		
		NodeList cityNodeList = (NodeList)xPath
				.compile("/rss/channel/item/description/body/location/city")
				.evaluate(xmlObj, XPathConstants.NODESET);
		/*
		 
		 ※ XPath 의 evaluate() 메소드의 두 번째 파라미터
		 	-XPathConstants.NODESET
		 	-XPathConstants.NODE
		 	-XPathConstants.BOOLEAN
		 	-XPathConstants.NUMBER
		 	-XPathConstants.STRING
		 */
		
		for (int i=0; i<cityNodeList.getLength();i++) {
			
			Node cityNode = cityNodeList.item(i);
			result.add(cityNode.getTextContent());
		}
			
			
		return result;
	}
	
	//날씨 정보 리스트
	public ArrayList<WeatherDTO> weatherList(String strIdx) throws XPathExpressionException  {
		
		ArrayList<WeatherDTO> result = new ArrayList<WeatherDTO>();
		
		int idx = Integer.parseInt(strIdx);
		NodeList dataNodeList = (NodeList)xPath.compile(String.format("/rss/channel/item/description/body/location[%d]/data", (idx+1))).evaluate(xmlObj,XPathConstants.NODESET);

		for (int i = 1; i <= dataNodeList.getLength(); i++)
		{
			String tmEf = (String)xPath.compile(String.format("/rss/channel/item/description/body/location[%d]/data[%d]/tmEf", (idx+1),i)).evaluate(xmlObj);
			String wf = (String)xPath.compile(String.format("/rss/channel/item/description/body/location[%d]/data[%d]/wf", (idx+1),i)).evaluate(xmlObj);
			String tmn = (String)xPath.compile(String.format("/rss/channel/item/description/body/location[%d]/data[%d]/tmn", (idx+1),i)).evaluate(xmlObj);
			String tmx = (String)xPath.compile(String.format("/rss/channel/item/description/body/location[%d]/data[%d]/tmx", (idx+1),i)).evaluate(xmlObj);
			String rnSt = (String)xPath.compile(String.format("/rss/channel/item/description/body/location[%d]/data[%d]/rnSt", (idx+1),i)).evaluate(xmlObj);
			WeatherDTO weather = new WeatherDTO();
			
			weather.setRnSt(rnSt);
			weather.setTmEf(tmEf);
			weather.setWf(wf);
			weather.setTmn(tmn);
			weather.setTmx(tmx);
			weather.setImg(map.get(wf));
			
			result.add(weather);
		}
		
		/*
		String path = String.format("/rss/channel/item/description/body/location[%s]/data", idx);
		
		NodeList dataNodeList = (NodeList)xPath.compile(path).evaluate(xmlObj,XPathConstants.NODESET);
		
		for (int i = 1; i <= dataNodeList.getLength(); i++)
		{
			Node dataNode = dataNodeList.item(i);
			Element dataElement = (Element)	dataNode;
			
			WeatherDTO weather = new WeatherDTO();
			
			weather.setRnSt(getText(dataElement, "rnSt"));
			weather.setTmEf(getText(dataElement, "tmEf"));
			weather.setTmn(getText(dataElement, "tmn"));
			weather.setTmx(getText(dataElement, "tmx"));
			weather.setWf(getText(dataElement, "wf"));
			weather.setImg(map.get(getText(dataElement, "wf")));
			
			result.add(weather);
		}
		
		 */
		
		
		
		return result;
	}
	
	public static String getText(Element parents, String tagName) {
		String result = "";
		
		Node node = parents.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		result = element.getChildNodes().item(0).getNodeValue();
		
		return result;
		
	}
}
