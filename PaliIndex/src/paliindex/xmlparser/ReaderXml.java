/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paliindex.xmlparser;

//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Iterator;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
import java.io.BufferedWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.NamedNodeMap;

public class ReaderXml {

    public static void main(String[] args) {

        File file = new File("repo");
        
        String[] list = file.list();
        for (String string : list) {
          System.out.println(string);  
        }

    }

    public static void export() {

        try {

            File file = new File("repo/vin11t.nrf.xml");

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = dBuilder.parse(file);

//            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            if (doc.hasChildNodes()) {

                printNote(doc.getChildNodes());

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void printNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);
            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
//                System.out.println("Node Value =" + tempNode.getTextContent());

                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

            }

        }

    }

    private static void printAllNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                System.out.println("Node Value =" + tempNode.getTextContent());

                if (tempNode.hasAttributes()) {

                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();

                    for (int i = 0; i < nodeMap.getLength(); i++) {

                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());

                    }

                }
                if (tempNode.hasChildNodes()) {

                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes());

                }
                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

            }

        }

    }

    public static String saveToFile(String strText) throws IOException {
        String strFileName = System.getProperty("user.dir") + File.separator;
        String strTimestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
        StringBuilder buf = new StringBuilder();
        buf.append(strFileName)
                .append("BTree-")
                .append(strTimestamp)
                .append(".txt");

        strFileName = buf.toString();
        File savedFile = new File(strFileName);

        if (!savedFile.exists()) {
            savedFile.createNewFile();
        }

        FileWriter fw = new FileWriter(savedFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(strText);
        bw.close();

        return strFileName;
    }
}
