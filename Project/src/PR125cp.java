import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class PR125cp {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Ruta del archivo original
        System.out.println("Arxivo a copiar -Path:");
        String rutaArchivoOriginal = (String)sc.next();

        // Ruta de destino proporcionada por el usuario
        System.out.println("Destino -Path:");
        String rutaDestinoUsuario = (String)sc.next();

        // Crear objetos File para el archivo original y el destino
        File archivoOriginal = new File(rutaArchivoOriginal);
        File destino = new File(rutaDestinoUsuario);

        try (FileInputStream fis = new FileInputStream(archivoOriginal);
             FileOutputStream fos = new FileOutputStream(destino);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            System.out.println("\n"+"El archivo se ha copiado correctamente a " + destino.getAbsolutePath()+"\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
