import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PR140Main {
    /*El programa ha de llegir el contingut del fitxer persones.xml (Veure Arxiu “persones.xml”)
    Mostra les dades llegides en una sortida per pantalla amb format de columnes alineades. 
    Amb "columnes alineades" ens referim a que les dades de cada camp 
    (nom, cognom, edat, ciutat)
     han d'aparèixer sota la seva respectiva capçalera en una presentació organitzada i fàcil de llegir.
 */
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/";
        String fileName = "persones.xml";
        String filePath = basePath + fileName;

        Document doc = UtilsXML.read(filePath);


        // Exemple de llistar tots els elements d'un XPath
        NodeList llista0 = UtilsXML.getNodeList(doc, "/persones/persona");
        printarPersones(llista0);

    }
        static void printarPersones (NodeList llista) {
            System.out.println(
                String.format("%-15s", "Nom")+
                String.format("%-15s", "Cognom")+
                String.format("%-10s", "Edat")+
                "Ciutat" + "\n"
                );
        for(int cnt = 0; cnt < llista.getLength(); cnt = cnt + 1) {
            Node nodePersona = llista.item(cnt);
            if(nodePersona.getNodeType() == Node.ELEMENT_NODE) {
                // Si és de tipus "ELEMENT_NODE" podem fer el cast a Element
                Element elmPersona = (Element) nodePersona;
                //Nom
                Element childName = UtilsXML.getFirstChildByName(elmPersona, "nom");
                String txtName = childName.getTextContent();
                //Cognom
                Element childSurname = UtilsXML.getFirstChildByName(elmPersona, "cognom");
                String textCognom = childSurname.getTextContent();
                //Edat
                Element childId = UtilsXML.getFirstChildByName(elmPersona, "edat");
                String textEdat = childId.getTextContent();
                //Ciudad
                Element childCity = UtilsXML.getFirstChildByName(elmPersona, "ciutat");
                String textCiutat = childCity.getTextContent();
                
                System.out.println(
                    String.format("%-15s", txtName)+
                    String.format("%-15s", textCognom)+
                    String.format("%-10s", textEdat)+
                    textCiutat

                );
            }
        }
    }
 
}
