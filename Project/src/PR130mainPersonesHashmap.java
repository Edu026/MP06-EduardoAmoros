import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class PR130mainPersonesHashmap {
    public static void main(String[] args) {
        HashMap<String,Integer> llista = new HashMap<String, Integer>();
        llista.put("Pedro",20);
        llista.put("Maria",30);
        llista.put("Carlos",15);
        llista.put("Puri",50);
        llista.put("Eduardo",21);

        //Guardar dades
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "PR130persones.dat";

        // Crear la carpeta 'data' si no existeix
        File dir = new File(basePath);
        if (!dir.exists()){
            if(!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'data'");
            }
        }

        System.out.println("");

        try {

            //Write
            FileOutputStream fos = new FileOutputStream(filePath);
            DataOutputStream dos = new DataOutputStream(fos);

            FileInputStream fis = new FileInputStream(filePath);
            DataInputStream dis = new DataInputStream(fis);

            Integer lgth = llista.size();
 
            for(String nom : llista.keySet()){
                dos.writeUTF(nom);
                dos.writeInt(llista.get(nom));
            }
            

            System.out.println("Arxiu guardat"+"\n");

            //Read

            for (Integer i= 0; i < lgth; i++){
                String nom = dis.readUTF();
                Integer edat = dis.readInt();
                System.out.println(String.format("Nombre: " + "%-10s", nom) + "Edat: " + edat );
            }
            fos.close();
            dos.close();
        } catch (IOException e) {

        }
        
    }
}
