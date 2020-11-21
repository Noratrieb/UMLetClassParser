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

/**
 * Opens a UMLet XML file and parses its content to an ArrayList of Type String containing all UML class texts
 */
public class XMLParser {

    private Document doc;
    private boolean valid;

    public XMLParser(String path) {
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(inputFile);
            valid = true;
        } catch (ParserConfigurationException | SAXException | IOException ignored){
        }
    }

    /**
     * Get all classes from a UMLet XML file
     * @return an ArrayList of Type String containing all UML classes in their text form
     */
    public ArrayList<String> getClassesText(){

        ArrayList<String> classes = new ArrayList<>();
        if(valid) {
            NodeList nList = doc.getDocumentElement().getElementsByTagName("element");

            for (int i = 0; i < nList.getLength(); i++) { Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    if (element.getElementsByTagName("id").item(0).getTextContent().equals("UMLClass")) {
                        String classBody = element.getElementsByTagName("panel_attributes").item(0).getTextContent();
                        classes.add(classBody);
                    }
                }
            }
        }

        return classes;
    }
}
