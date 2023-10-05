import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

// Aquest exemple escriu un text 
// en un arxiu amb FileWriter
// Després el torna a obrir per afegir-hi més text
// amb el paràmetre 'append'

public class PR124linies {
    public static void main(String args[]) {
        String basePath = System.getProperty("user.dir");
        String filePath = basePath + "\\numeros.txt";
        Random random = new Random();
        int num;

        System.out.println("");
        try {
            for (int i = 0; i < 10;i++)
            {
                num = random.nextInt(100);
                FileWriter fw0 = new FileWriter(filePath,true);
                fw0.write(num+"\n");
                fw0.close();
            }

        } catch (IOException e) { e.printStackTrace(); }
    }
}

