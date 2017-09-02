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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.NamedNodeMap;

public class ReaderXml {

    public static void main(String[] args) throws FileNotFoundException {

//        File file;
//        String[] list;
////       
//        file = new File("repo_tune");
//        list = file.list();
//        for (String filename : list) {
//            saveToFile(export(filename), "output/" + filename + ".txt");
//        }
        testPrint("vin01m.mul.xml");
    }

    public static void replaceNote(String filename) {
//         File file = new File("repo");
//        String[] list = file.list();
//
//        for (String filename : list) {
//            FileUtil fileUtil = new FileUtil();
//            String reen = fileUtil.getFile("repo/" + filename,"UTF16")
//                    .replaceAll("UTF-16", "UTF-8") //                    
//                    .replaceAll("<note>", "[")
//                    .replaceAll("</note>", "]");
//            saveToFile(reen, "repo_tune/" + filename);
//        }
    }

    public static String export(String filename) {
        StringBuilder result = new StringBuilder();
        try {
            File file = new File("repo_tune/" + filename);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            if (doc.hasChildNodes()) {
                result = printNote(doc.getChildNodes(), "TEI.2");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result.toString().trim();

    }

    private static StringBuilder printNote(NodeList nodeList, String nodeName) {
        StringBuilder result = new StringBuilder();
        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);
            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                if (nodeName == tempNode.getNodeName()) {
                    result.append(tempNode.getTextContent());
                }
            }
        }
        return result;
    }

    public static String testPrint(String filename) {
        StringBuilder result = new StringBuilder();
        try {
            File file = new File("repo_tune/" + filename);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            if (doc.hasChildNodes()) {
                printAllNote(doc.getChildNodes());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result.toString().trim();

    }

    private static void printAllNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
//                System.out.println("Node Value =" + tempNode.getTextContent());

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
                    printAllNote(tempNode.getChildNodes());
                }
            }

        }

    }

    public static String saveToFile(String strText, String fileName) {
        File savedFile = new File(fileName);
        try {

            if (!savedFile.exists()) {
                savedFile.createNewFile();
            }

            FileWriter fw = new FileWriter(savedFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(strText);
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(ReaderXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        return savedFile.getAbsolutePath();
    }
}
