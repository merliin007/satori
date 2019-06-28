/**
 * Created by Miguel Angel Aguilar
 * maaguilar@sciodev.com - jun 2019
 **/

package utility.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utility.Log;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XMLReader {
    private NodeList nList;

    public XMLReader(String file) {
        try{
            File xmlFile = new File( file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            nList = doc.getElementsByTagName("location");
        }catch (Exception e){
            Log.error(e.getMessage());
        }
    }

    public Map<String, String> getURLS(){
        Map<String, String> urls = new HashMap<>();
        try{
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;

                    String path = element.getAttribute("path");
                    String dest = element.getElementsByTagName("httpRedirect")
                            .item(0).getAttributes()
                            .getNamedItem("destination").getTextContent();

                    urls.put(path,dest);
                }
            }
            return urls;
        }catch (Exception e){
            Log.error(e.getMessage());
            return null;
        }
    }
}
