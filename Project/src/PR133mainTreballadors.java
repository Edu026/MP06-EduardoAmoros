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

            String menu = "Id del trabajador a modificar: (Escribe Salir para salir)";
            
            System.out.println(menu);
            String id = sc.nextLine();

            if (id.equals("Salir")) {
                programa = false;
                break;
            }
        

            String[] dadesId = UtilsCSV.getColumnData(csv, "Id");
            dadesId = Arrays.copyOfRange(dadesId, 1, dadesId.length);

            boolean exists = false;


            for (String i : dadesId){
                
                if (id.equals(i)){
                    exists = true;
                }
            }

            if (exists == false) {System.out.println("El id introducido no existe"); }

            while (exists) {
                System.out.print("¿Qué campo deseas modificar (Nom, Cognom, Departament, Salari, Salir)? ");

                String campo = sc.nextLine();

                switch (campo){
                    case "Nom": {
                        System.out.println("Nuevo nombre: ");
                        String nouNom = sc.nextLine();
                        int numLinia = UtilsCSV.getLineNumber(csv, "Id", id);
                        UtilsCSV.update(csv, numLinia, "Nom",nouNom);
                        UtilsCSV.write(filePath, csv);
                        System.out.println("Se ha cambiad el nombre a " + nouNom);
                        exists = false;
                        break;
                    }
                    case "Cognom": {
                        System.out.println("Nuevo apellido: ");
                        String nouCognom = sc.nextLine();
                        int numLinia = UtilsCSV.getLineNumber(csv, "Id", id);
                        UtilsCSV.update(csv, numLinia, "Cognom",nouCognom);
                        UtilsCSV.write(filePath, csv);
                        System.out.println("Se ha cambiado el apellido a " + nouCognom);
                        exists = false;
                        break;
                    }
                    case "Departament": {
                        System.out.println("Nuevo departamento: ");
                        String nouDep = sc.nextLine();
                        int numLinia = UtilsCSV.getLineNumber(csv, "Id", id);
                        UtilsCSV.update(csv, numLinia, "Departament",nouDep);
                        UtilsCSV.write(filePath, csv);
                        System.out.println("Se ha cambiado el departamento a " + nouDep);
                        exists = false;
                        break;
                    }
                    case "Salari": {
                        System.out.println("Nuevo salario: ");
                        String nouSalari = sc.nextLine();
                        int numLinia = UtilsCSV.getLineNumber(csv, "Id", id);
                        UtilsCSV.update(csv, numLinia, "Salari",nouSalari);
                        UtilsCSV.write(filePath, csv);
                        System.out.println("Se ha cambiado el salario a " + nouSalari);
                        exists = false;
                        break;                       
                    }
                    case "Salir": exists = false; break;
                    default : System.out.println("Opcion incorrecta");

                    System.out.println("");
                }

            }
            
        }    
    }
}

