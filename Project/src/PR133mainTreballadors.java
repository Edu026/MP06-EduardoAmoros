import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class PR133mainTreballadors {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/";
        String fileName = "PR133treballadors.csv";
        String filePath = basePath + fileName;

        Scanner sc = new Scanner(System.in);
        boolean programa = true;

        while (programa){
            List<String> csv = UtilsCSV.read(filePath);

            String menu = "Id del trabajador a modificar: ";
            
            System.out.println(menu);
            String id = sc.nextLine();

            String[] dadesId = UtilsCSV.getColumnData(csv, "Id");
            dadesId = Arrays.copyOfRange(dadesId, 1, dadesId.length);

            boolean exists = false;

            for (String i : dadesId){
                if (id.equals(i)){
                    exists = true;
                }
            }
            
            if (exists = false) System.out.println("El id introducido no existe"); 

            while (exists = true) {
                System.out.print("¿Qué campo deseas modificar (Nom, Cognom, Departament, Salari)? ");

                String campo = sc.nextLine();

                switch (campo){
                    case "Nom": {
                        System.out.println("Nuevo nombre: ");
                        String nouNom = sc.nextLine();
                        int numLinia = UtilsCSV.getLineNumber(csv, "id", id);
                        UtilsCSV.update(csv, numLinia, "Nom",nouNom);
                        System.out.println("Se ha cambiad el nombre a " + nouNom);
                        exists = false;
                        break;
                    }
                    case "Cognom": {
                        System.out.println("Nuevo apellido: ");
                        String nouCognom = sc.nextLine();
                        int numLinia = UtilsCSV.getLineNumber(csv, "id", id);
                        UtilsCSV.update(csv, numLinia, "Cognom",nouCognom);
                        System.out.println("Se ha cambiado el apellido a " + nouCognom);
                        exists = false;
                        break;
                    }
                    case "Departament": {
                        System.out.println("Nuevo departamento: ");
                        String nouDep = sc.nextLine();
                        int numLinia = UtilsCSV.getLineNumber(csv, "id", id);
                        UtilsCSV.update(csv, numLinia, "Departament",nouDep);
                        System.out.println("Se ha cambiado el departamento a " + nouDep);
                        exists = false;
                        break;
                    }
                    case "Salari": {
                        System.out.println("Nuevo salario: ");
                        String nouSalari = sc.nextLine();
                        int numLinia = UtilsCSV.getLineNumber(csv, "id", id);
                        UtilsCSV.update(csv, numLinia, "Salari",nouSalari);
                        System.out.println("Se ha cambiado el salario a " + nouSalari);
                        exists = false;
                        break;                       
                    }
                    default : System.out.println("Opcion incorrecta");
                }

            }
            
        }    
    }
}

