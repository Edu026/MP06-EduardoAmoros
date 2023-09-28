import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PR122cat {
    public static void main(String[] args) {       
            String basePath = System.getProperty("user.dir");
            System.out.println(basePath); 
            Scanner sc = new Scanner(System.in);    
            String checkFilePath = sc.next();
            // Comprovar si una ruta és un arxiu
            File f3 = new File(checkFilePath);
            boolean esArxiu = f3.isFile();
            boolean esDir = f3.isDirectory();
            boolean exArx = f3.exists();
            
            if (esArxiu) {
                System.out.println("La ruta \"" + f3.getName() + "\" és un arxiu");
                
            } else if (esDir){
                    System.out.println("El path no correspon a un arxiu, sinó a una carpeta");
            }
            else {
                System.out.println("El fixer no existeix");
            }
    
        }
}
