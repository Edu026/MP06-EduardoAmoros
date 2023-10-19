import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PR141Main {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/";
        String fileName = "Arxiu.xml";
        String filePath = basePath + fileName;
        
        // Crea una factoria de constructors de documents
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // Crea un constructor de documents
            DocumentBuilder db = dbf.newDocumentBuilder();
            //Crea un nou document XML
            Document doc  = db.newDocument();

            //Crear l'element root 
            Element elmRoot = doc.createElement("biblioteca");
            // Afegeix l'element root al document XML
            doc.appendChild(elmRoot);

            Element elmLlibre = doc.createElement("llibre");
            //Crear un atribut
            Attr attrId = doc.createAttribute("id");
            //Donar-li valor
            attrId.setValue("001");
            //Afegir un atribit a un element
            elmLlibre.setAttributeNode(attrId);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        
    }
}
