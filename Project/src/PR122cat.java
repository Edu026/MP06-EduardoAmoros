import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class PR122cat {
    public static void main(String[] args) {      
        
            System.out.println("\n"+"Path: ");
            
            Scanner sc = new Scanner(System.in);    
            String checkFilePath = sc.next();

            // Comprovar si una ruta és un arxiu
            File f3 = new File(checkFilePath);
            boolean esArxiu = f3.isFile();
            boolean esDir = f3.isDirectory();
            boolean exArx = f3.exists();
            
            if (esArxiu) {
                System.out.println("La ruta \"" + f3.getName() + "\" es un arxiu");

                try {
                    List<String> linies = Files.readAllLines(Paths.get(checkFilePath, args), StandardCharsets.UTF_8);
                    for (int cnt = 0; cnt < linies.size(); cnt = cnt + 1) {
                        System.out.println(linies.get(cnt));
                    }
                } catch (IOException e) { e.printStackTrace(); } 
                
            } else if (esDir){
                    System.out.println("El path no correspon a un arxiu, sinó a una carpeta");
            }
            else {
                System.out.println("El fixer no existeix");
            }
    
        }
}
