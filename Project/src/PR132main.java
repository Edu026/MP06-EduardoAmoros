import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.tools.FileObject;

public class PR132main {
    public static void main(String[] args) {
        //Varible con los paths 
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "PR132people.dart";

        //Lista para guardar los objetos
        List<PR132persona> people  = new ArrayList<>();

            //Creamos los objetos
            PR132persona pr1 = new PR132persona("Maria","Lopez",36);
            PR132persona pr2 = new PR132persona("Gustavo", "Ponts", 63);
            PR132persona pr3 = new PR132persona("Irene", "Sales", 54);

            people.add(pr1);
            people.add(pr2);
            people.add(pr3);
       
        //Guardar la lista con los objetos
        try {
            
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(people);
            

        }catch (IOException e) { e.printStackTrace(); }

        //Leer la lista con los objetos
        try{
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<PR132persona> catchPeople = (List<PR132persona>) ois.readObject();

            System.out.println("\n"); 
            System.out.print(String.format("%-10s","Nom") + String.format("%-10s", "Cognom") + String.format("%-10s", "Edat"));
            System.out.println("\n");

            for(PR132persona person : catchPeople){
                System.out.print(String.format("%-10s",person.getNom()));
                System.out.print(String.format("%-10s",person.getCognom()));
                System.out.print(String.format("%-10s",person.getEdat()));
                System.out.println("\n"); 
            }

        }catch (IOException  | ClassNotFoundException e2) { e2.printStackTrace(); }
    
    }
}

//Classe PR132persona

class PR132persona  implements Serializable{
    private String nom;
    private String cognon;
    private Integer edat;

    PR132persona ( String nom, String cognonm, Integer edat){
        this.nom = nom;
        this.cognon = cognonm;
        this.edat = edat;

    }
     String getNom(){
        return this.nom;
    }

    String getCognom(){
        return this.cognon;
    }
    
    Integer getEdat(){
        return this.edat;
    }
}
