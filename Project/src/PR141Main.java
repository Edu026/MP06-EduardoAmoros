import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class PR141Main {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/Project/data/";
        String fileName = "biblioteca.xml";
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
            //Title
            Element elemTitle = doc.createElement("title");
            Text NodeTextTitle = doc.createTextNode("El viatge dels venturons");
            elemTitle.appendChild(NodeTextTitle);
            //Author
            Element elemAutor = doc.createElement("autor");
            Text NodeTextAutor = doc.createTextNode("Joan Pla");
            elemAutor.appendChild(NodeTextAutor);
            //Any Publicacio
            Element elemAnyPub = doc.createElement("anyPublicacio");
            Text NodeTextAnyPub = doc.createTextNode("1998");
            elemAnyPub.appendChild(NodeTextAnyPub);
            //Editorial
            Element elemEditorial = doc.createElement("editorial");
            Text NodeTextEditorial = doc.createTextNode("Edicions Mar");
            elemEditorial.appendChild(NodeTextEditorial);
            //Genere
            Element elemGenere = doc.createElement("genere");
            Text NodeTextGenere = doc.createTextNode("Aventura");
            elemGenere.appendChild(NodeTextGenere);
            //Pagines
            Element elemPagines = doc.createElement("pagines");
            Text NodeTextPagines = doc.createTextNode("320");
            elemPagines.appendChild(NodeTextPagines);
            //Disponible
            Element elemDisponible = doc.createElement("disponible");
            Text NodeTextDisponible = doc.createTextNode("true");
            elemDisponible.appendChild(NodeTextDisponible);

            elmLlibre.appendChild(elemTitle);
            elmLlibre.appendChild(elemAutor);
            elmLlibre.appendChild(elemAnyPub);
            elmLlibre.appendChild(elemEditorial);
            elmLlibre.appendChild(elemGenere);
            elmLlibre.appendChild(elemPagines);
            elmLlibre.appendChild(elemDisponible);
            
            elmRoot.appendChild(elmLlibre);







            write(filePath, doc);
            

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    // Save a Document into an XML file
static public void write (String path, Document doc) throws TransformerException, IOException {
    if (!new File(path).exists()) { new File(path).createNewFile(); }
    // Crea una factoria de transformadors XSLT
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    // Crea un transformador XSLT
    Transformer transformer = transformerFactory.newTransformer();
    // Estableix la propietat OMIT_XML_DECLARATION a "no" per no ometre la declaració XML del document XML resultant
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
    // Estableix la propietat INDENT a "yes" per indentar el document XML resultant
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    // Elimina els espais en blanc innecessaris del document XML. Implementació pròpia
    // Crea una instància de DOMSource a partir del document XML
    DOMSource source = new DOMSource(doc);
    // Crea una instància de StreamResult a partir del camí del fitxer XML
    StreamResult result = new StreamResult(new File(path));
    // Transforma el document XML especificat per source i escriu el document XML
    // resultant a l'objecte especificat per result
    transformer.transform(source, result);
}

}
