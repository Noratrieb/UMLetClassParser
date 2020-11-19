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
import java.util.ArrayList;

public class XMLParser {

    private Document doc;

    public XMLParser(String path) {
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(inputFile);
        } catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getClassesText(){

        ArrayList<String> classes = new ArrayList<>();

        NodeList nList = doc.getDocumentElement().getElementsByTagName("element");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE){

                Element element = (Element) node;

                if(element.getElementsByTagName("id").item(0).getTextContent().equals("UMLClass")){

                    String classBody = element.getElementsByTagName("panel_attributes").item(0).getTextContent();
                    classes.add(classBody);

                }
            }
        }

        return classes;
    }
}
