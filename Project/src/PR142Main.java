import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class PR142Main {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/Project/data/";
        String fileName = "cursos.xml";
        String filePath = basePath + fileName;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbf.newDocumentBuilder();
            // Analitza el fitxer XML i crea un document XML
            Document doc = dBuilder.parse(filePath);
            // Crea un objecte XPath
            XPath xPath = XPathFactory.newInstance().newXPath();


        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
    
}
