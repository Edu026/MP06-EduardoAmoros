import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

            //Llistar ids de cursos, tutors i total d’alumnes.
            String expression = "/cursos/curs";
            // Avaluem l'expressió XPath i obtenim una llista de nodes
            NodeList listExpression = UtilsXML.getNodeList(doc, expression);

            NodeList alumnes = UtilsXML.getNodeList(doc, "/cursos/curs/alumnes");

            
//Llistar ids de cursos, tutors i total d’alumnes. 
            System.out.println("\n"+"Llistar ids de cursos, tutors i total d’alumnes.");
            for(int cnt = 0; cnt < listExpression.getLength(); cnt = cnt + 1) {
                Node node = listExpression.item(cnt);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    // Si és de tipus "ELEMENT_NODE" podem fer el cast a Element
                    Element elm = (Element) node;
                    String attrId = elm.getAttribute("id");
                    Element childName = UtilsXML.getFirstChildByName(elm, "tutor");
                    String txtName = childName.getTextContent();   
                    // Recorre los hijos del nodo <curs> para buscar nodos de alumnos y contarlos
                    NodeList children = elm.getElementsByTagName("alumne");
                    int numStudents = children.getLength();
                    System.out.print(" - " + attrId + " : " + txtName + ", numero alumnes : " +numStudents + "\n");        
                }
            }
//Mostrar ids i titols dels mòduls a partir d'un id de curs.
            showModules(doc, "AWS1");
            
//Llistar alumnes d’un curs.
            System.out.println("\n"+"Llistar alumnes d un curs."+"\n"+">Alumnes:");
            expression = "/cursos/curs[@id = 'AMS2']/alumnes/alumne";
            alumnes = UtilsXML.getNodeList(doc, expression);
            for(int cnt = 0; cnt < alumnes.getLength(); cnt = cnt + 1) {
                Node node = alumnes.item(cnt);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    // Si és de tipus "ELEMENT_NODE" podem fer el cast a Element
                    Element elm = (Element) node;
                    String txtName = elm.getTextContent();   
                    System.out.println(" - " + txtName);        
                }
            }
// Afegir un alumne a un curs.
            System.out.println("\n"+"Afegir un alumne a un curs.");
            String courseId = "AWS1"; // ID del curso al que deseas agregar el alumno

            // Encuentra el nodo <curs> con el ID deseado
            expression = "/cursos/curs[@id='" + courseId + "']";
            Node courseNode = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
            // Encuentra el nodo <alumnes> dentro del nodo <curs>
            Node alumnesNode = (Node) xPath.compile("alumnes").evaluate(courseNode, XPathConstants.NODE);

                if (alumnesNode != null) {
                    Element newStudentElement = doc.createElement("alumne");
                    newStudentElement.setTextContent("PITARQUE, Albert");

                    // Agrega el nuevo elemento "alumne" como hijo del nodo <alumnes> dentro del nodo <curs>
                    alumnesNode.appendChild(newStudentElement);
                    
                    try {
                        write(filePath, doc);
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }
                }    
                
                
// Eliminar un alumne d un curs.
            System.out.println("\n"+"Eliminar un alumne d un curs.");
            // Obtén la lista de hijos del nodo padre         
            alumnesNode = (Node) xPath.compile("alumnes").evaluate(courseNode, XPathConstants.NODE);
            NodeList children = alumnesNode.getChildNodes();
            // Verifica si la lista de hijos no está vacía
            if (children.getLength() > 0) {
                alumnesNode.removeChild(children.item(1));              
                System.out.println("");
            }
        }        
         catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XPathExpressionException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        

    }
static void showModules(Document doc, String idModule) {
    System.out.println("\n"+"Mostrar ids i titols dels mòduls a partir d'un id de curs.");
    NodeList moduls = UtilsXML.getNodeList(doc, "/cursos/curs[@id=" + "'" + idModule + "'" + "]" + "/moduls/modul");
    for(int cnt = 0; cnt < moduls.getLength(); cnt = cnt + 1){
        Node node = moduls.item(cnt);
        Element elm = (Element) node;
        String attrId = elm.getAttribute("id");
        Element childName = UtilsXML.getFirstChildByName(elm, "titol");
        String txtName = childName.getTextContent();   
        System.out.println(" - " + attrId + " : " + txtName );   
    }


}

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
    transformer.setOutputProperty(OutputKeys.INDENT, "no");
    // Crea una instància de DOMSource a partir del document XML
    DOMSource source = new DOMSource(doc);
    // Crea una instància de StreamResult a partir del camí del fitxer XML
    StreamResult result = new StreamResult(new File(path));
    // Transforma el document XML especificat per source i escriu el document XML
    // resultant a l'objecte especificat per result
    transformer.transform(source, result);
    System.out.println("Nuevo alumno agregado exitosamente.");
}    
    
}
