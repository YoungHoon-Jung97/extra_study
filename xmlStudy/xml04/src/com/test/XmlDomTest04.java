package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

import java.io.File;

public class XmlDomTest04 {
    public static void main(String[] args) {
        try {
            // XML 파일을 DOM 객체로 로드
            File xmlFile = new File("VEHICLES.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlObj = builder.parse(xmlFile);

            // 루트 엘리먼트 가져오기
            Element root = xmlObj.getDocumentElement();

            // VEHICLE 요소 리스트 가져오기
            NodeList vehicleNodeList = root.getElementsByTagName("VEHICLE");

            System.out.println("-------------------------------------------------------------");
            System.out.println("NO\tMAKE\tMODEL\tYEAR\tSTYLE\tPRICE");
            System.out.println("-------------------------------------------------------------");

            for (int i = 0; i < vehicleNodeList.getLength(); i++) {
                Node vehicleNode = vehicleNodeList.item(i);

                if (vehicleNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element vehicleElement = (Element) vehicleNode;

                    System.out.println("-------------------------------------------------------------");
                    System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n",
                            getText(vehicleElement, "INVENTORY_NUMBER"),
                            getText(vehicleElement, "MAKE"),
                            getText(vehicleElement, "MODEL"),
                            getText(vehicleElement, "YEAR"),
                            getText(vehicleElement, "STYLE"),
                            getText(vehicleElement, "PRICE")
                    );

                    System.out.println("Options -----------------------------------------------------");

                    // OPTIONS 노드 가져오기
                    NodeList optionList = vehicleElement.getElementsByTagName("OPTIONS");

                    if (optionList.getLength() > 0) {
                        Node optionNode = optionList.item(0);
                        if (optionNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element optionElement = (Element) optionNode;

                            // OPTIONS 하위 요소 출력
                            NodeList childNodeList = optionElement.getChildNodes();
                            for (int j = 0; j < childNodeList.getLength(); j++) {
                                Node childNode = childNodeList.item(j);
                                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                                    System.out.printf("%s : %s\n", childNode.getNodeName(), childNode.getTextContent().trim());
                                }
                            }
                        }
                    }
                    System.out.println("-------------------------------------------------------------");
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getText(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);

        if (nodeList.getLength() == 0) {
            return "N/A"; // 값이 없을 경우 기본값 반환
        }

        Node node = nodeList.item(0);
        return node.getTextContent().trim(); // 안전한 방식으로 텍스트 추출
    }
}
