import java.io.File;
import java.io.IOException;

public class PR121Files {
    public static void main(String args[]) {
        String basePath = System.getProperty("user.dir")+"/myFiles/";
        String fileName = "Arxiu.txt";
        String filePath = basePath + fileName;


        // Crear la carpeta 'myFiles' si no existeix
        File dir = new File(basePath);
        if (!dir.exists()){
            if(!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'data'");
            }
        }

        //Crear file1 & file2
        try {
            File f1 = new File(basePath + "file1.txt");
            File f2 = new File(basePath + "file2.txt");
            boolean arxiu1Creat = f1.createNewFile();
            boolean arxiu2Creat = f2.createNewFile();

            if (arxiu1Creat) {
                System.out.println("S'ha creat l'arxiu \"" + f1.getName() + "\"");
            } else {
                System.out.println("No s'ha pogut crear l'arxiu \"" + f1.getName() + "\"");
            }

            if (arxiu2Creat) {
                System.out.println("S'ha creat l'arxiu \"" + f2.getName() + "\"");
            } else {
                System.out.println("No s'ha pogut crear l'arxiu \"" + f2.getName() + "\"");
            }


            //Rename file2.txt 

            System.out.println("\n");

            File renameFile = new File(basePath, "renamedFile.txt");
            boolean renamed = f2.renameTo(renameFile);

            if (renamed){
                System.out.println("S'ha cambiat el nom del arxiu");
            }else{
                System.out.println("No s'ha cambiat el nom del arxiu");

            }
            
            System.out.println("\n");


            //Mostrar els fixers 

            showFiles(dir);

            //Eliminar file1

            boolean arxiuBorrat = f1.delete();
            if (arxiuBorrat) {
                System.out.println("S'ha borrat l'arxiu \"" + f1.getName() + "\"");
            } else {
                System.out.println("No s'ha pogut borrar l'arxiu \"" + f1.getName() + "\"");
            }
            
            System.out.println();

            showFiles(dir);

        } catch (IOException e) { e.printStackTrace(); 
        } 

    }

    public static void showFiles(File dir){
            System.out.println("Els arxius de la carpeta són:");

            // Comprobem que el directori existeix
        if (dir.exists() && dir.isDirectory()) {
            // Llistem els fixers de la carpeta
            File[] files = dir.listFiles();

            if (files != null) {
                for (File file : files) {
                    System.out.println("-"+file.getName());
                }
            } else {
                System.err.println("No s'han pogut llistar els fixers de la carpeta");
            }
        } else {
            System.err.println("La carpeta especificada no existeix o no es al directori");
        }

        System.out.println("\n");

    }

}