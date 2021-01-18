package org.star_pet_house_commons.io;

import com.google.common.io.Resources;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/*
 *@description:
 *@author jiafeng
 *@date 2021/1/18 0018 10:50
 */
public class ReadXmlUtils {

    public static void main(String[] args) {
        String soucePath = Resources.getResource("book.xml").getPath();
        org.w3c.dom.Document doc = ReadXmlUtils.useDomReadXml(soucePath);
        //读取xml内部节点集合
        org.w3c.dom.NodeList nlst = doc.getElementsByTagName("book");
        //遍历集合内容
        for (int i = 0; i < ((org.w3c.dom.NodeList) nlst).getLength(); i++) {
            String title = doc.getElementsByTagName("title").item(i).getFirstChild().getNodeValue();
            String creater = doc.getElementsByTagName("author").item(i).getFirstChild().getNodeValue();
            String year = doc.getElementsByTagName("year").item(i).getFirstChild().getNodeValue();
            String price = doc.getElementsByTagName("price").item(i).getFirstChild().getNodeValue();
            System.err.println("标题"+ title);
            System.err.println("作者"+creater);
            System.err.println("年份"+ year);
            System.err.println("价格"+ price);
        }
    }

    public static Document useDomReadXml(String soucePath){
        File file = new File(soucePath);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            return doc;
        } catch (Exception e) {
            System.err.println("读取该xml文件失败");
            e.printStackTrace();
        }
        return null;
    }
}
