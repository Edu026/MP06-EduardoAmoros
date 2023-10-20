import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
//Afegir un alumne a un curs.

            
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
    
}
