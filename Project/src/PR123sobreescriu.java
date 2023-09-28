import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Aquest exemple escriu un text 
// en un arxiu amb FileWriter
// Després el torna a obrir per afegir-hi més text
// amb el paràmetre 'append'

public class PR123sobreescriu {
    public static void main(String args[]) {
        String basePath = System.getProperty("user.dir");
        String filePath = basePath + "\\frasesMatrix.txt";

        System.out.println("");

        try {
            System.out.println("Sobreescribir");
            FileWriter fw0 = new FileWriter(filePath);
            fw0.write("Yo sólo puedo mostrarte la puerta\n");
            fw0.write("Tú eres quien la tiene que atravesar\n");
            fw0.close();

        } catch (IOException e) { e.printStackTrace(); }
    }
}

