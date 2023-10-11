import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PR131mainEscriu {
    public static void main(String[] args) {
        //Create variables that contein the paths
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "PR131HashMapData.ser";


        // Crear la carpeta 'data' si no existeix
        File dir = new File(basePath);
        if (!dir.exists()){
            if(!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'data'");
            }
        }

        System.out.println("");

        try {
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

            PR131hashmap obj = new PR131hashmap();

            obj.addPerson("Paco", 45);
            obj.addPerson("Martha", 12);
            
			oos.writeObject(obj);

			oos.close();
			fos.close();

            System.out.println("Llest" + "\n");

		} catch (IOException e) { e.printStackTrace(); }
    }
}
