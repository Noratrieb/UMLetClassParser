import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        String path = "C:\\Users\\nilsh\\Desktop\\Umlet/test.uxf";

        File inputFile = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputFile);
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        System.out.println("---------");

        NodeList nList = doc.getDocumentElement().getElementsByTagName("element");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            System.out.println("Current Element: " + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE){

                Element element = (Element) node;

                if(element.getElementsByTagName("id").item(0).getTextContent().equals("UMLClass")){

                    System.out.println("class");

                }

            }
        }

    }

}
