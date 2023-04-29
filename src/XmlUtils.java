import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XmlUtils {
    private XmlUtils(){}
    public static void createXML() throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootElement = document.createElement("Students");
        document.appendChild(rootElement);

        Element studentElement = document.createElement("Student");
        studentElement.setAttribute("ID", "1");
        rootElement.appendChild(studentElement);

        Element firstnameElement = document.createElement("Firstname");
        firstnameElement.appendChild(document.createTextNode("Baia"));
        studentElement.appendChild(firstnameElement);

        Element lastnameElement = document.createElement("Lastname");
        lastnameElement.appendChild(document.createTextNode("Asanidze"));
        studentElement.appendChild(lastnameElement);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);

        StreamResult resource = new StreamResult(new File("C:\\Users\\User\\IdeaProjects\\XMLParsing\\Student1.xml"));
        transformer.transform(domSource, resource);
    }

//    public static void parseXML() throws ParserConfigurationException, IOException, SAXException {
//
//        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//        Document document = documentBuilder.parse(new File("C:\\Users\\User\\IdeaProjects\\XMLParsing\\Student1.xml"));
//
//        document.getDocumentElement().normalize();
//        NodeList nodeList = document.getElementsByTagName("Firstname");
//        NodeList nodeList2 = document.getElementsByTagName("Lastname");
//
//        System.out.println(nodeList.item(0).getTextContent());
//        System.out.println(nodeList2.item(0).getTextContent());
//    }

    public static void parseXML() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("C:\\Users\\User\\IdeaProjects\\XMLParsing\\Student1.xml"));

        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("Student");

        // Get the first Student element and retrieve its ID attribute
        Element studentElement = (Element) nodeList.item(0);
        String id = studentElement.getAttribute("ID");
        System.out.println("ID: " + id);

        // Retrieve the Firstname and Lastname elements as before
        NodeList firstnameList = studentElement.getElementsByTagName("Firstname");
        NodeList lastnameList = studentElement.getElementsByTagName("Lastname");

        System.out.println("First Name: " + firstnameList.item(0).getTextContent());
        System.out.println("Last Name: " + lastnameList.item(0).getTextContent());
    }
}

