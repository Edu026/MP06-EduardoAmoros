import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.sound.sampled.FloatControl;

public class RegistreEstudiants {
    private static final int ID_SIZE = 4; // bytes
    private static final int CHAR_SIZE = 2; // bytes per caràcter en UTF-16
    private static final int NAME_SIZE = 20; // Longitud màxima en caràcters del nom
    private static final int NOTA_SIZE = 4; // Longitud  màxima en caràcter de la nota 

    private static int last_id = 0;
    public static void main(String[] args) {
        boolean programa = true ;
        Scanner sc = new Scanner(System.in);

        while (programa){
            String menu = "1) Afgir un nou alumne" + "\n" + "2) Actualitzar la nota d'un alumne" + "\n" + "3) Consultar la nota";
            menu = menu + "\n" + "Opcion : ";
            System.out.println(menu);
            String opcion = sc.nextLine();

         
                try {    
                    RandomAccessFile raf = new RandomAccessFile("./data/alumnes.dat", "rw");
                    String nom;
                    Float nota;
                    switch (opcion) {
                        case "1" : 
                            System.out.println("AFEGIR UN NOU ALUMNE !");
                            //Nom
                            System.out.print("Nom : ");
                            nom = sc.nextLine();
                            //Nota
                            System.out.print("Nota :");
                            nota = sc.nextFloat();
                            afegirAlumne(raf, last_id, nom, nota);
                            consultarAlumne(raf, last_id);
                            break;

                        case "2" : 

                            System.out.println("ACTUALITZAR NOTA !");
                            //Preguntar el id 
                            System.out.println("Número de registre del estudinat: ");
                            int id_consulta = sc.nextInt();
                            //Nota
                            System.out.println("Nota :");
                            nota = sc.nextFloat();
                            
                            consultarAlumne(raf, id_consulta);
                            
                            break;
                    }
                }catch(InputMismatchException e) {
                    System.out.println("Error: El valor ingresado tiene el formato válido.");
                }catch (Exception e) {
                    e.printStackTrace();
                }
                
            }    
        }
    

    public static void afegirAlumne(RandomAccessFile raf, int id, String nom, float nota) throws Exception {
    raf.seek(raf.length());
    raf.writeInt(id + 1);
    raf.writeChars(getPaddedName(nom));
    raf.writeFloat(nota);
    System.out.println("Estudiante agregado correctamente.");
    }

    public static void actualitzarNotaAlumne(RandomAccessFile raf, int id, Float nota) throws Exception {
        raf.seek(getSeekPosition(id) + ID_SIZE + NAME_SIZE);
        raf.writeFloat(nota);
    }

    public static void mostrarAlumne(RandomAccessFile raf, int id, String msg) throws Exception {
        System.out.println(msg + " " + id + ": " + consultarAlumne(raf, id));
    }

    public static String consultarAlumne(RandomAccessFile raf, int id) throws Exception {
        raf.seek(getSeekPosition(id));
        raf.readInt();
        char[] chars = new char[NAME_SIZE];
        for (int i = 0; i < NAME_SIZE; i++) {
            chars[i] = raf.readChar();
        }
        
        return new String(chars).trim();
    }


    
    /**
     * Retorna una versió del nom del videojoc que sempre té una longitud fixa (NAME_SIZE).
     * Si el nom és més llarg que NAME_SIZE, es trunca. Si és més curt, s'omple amb espais en blanc.
     *
     * @param name El nom original del videojoc.
     * @return El nom amb una longitud fixa de NAME_SIZE caràcters.
     */
    private static String getPaddedName(String name) {
        // Si el nom és més llarg que la mida màxima permesa (NAME_SIZE),
        // es trunca per ajustar-se a aquesta mida.
        if (name.length() > NAME_SIZE) {
            return name.substring(0, NAME_SIZE);
        }
        // Si el nom és més curt que NAME_SIZE, s'omple amb espais en blanc fins a assolir aquesta mida.
        // String.format amb "%1$-" + NAME_SIZE + "s" assegura que la cadena resultant tingui una longitud fixa.
        return String.format("%1$-" + NAME_SIZE + "s", name);
    }


    /**
     * Retorna una versió del nom del videojoc que sempre té una longitud fixa (NAME_SIZE).
     * Si el nom és més llarg que NAME_SIZE, es trunca. Si és més curt, s'omple amb espais en blanc.
     *
     * @param name El nom original del videojoc.
     * @return El nom amb una longitud fixa de NAME_SIZE caràcters.
     */
    private static long getSeekPosition(int id) {
        // L'operació (id - 1) serveix per obtenir un índex basat en 0.
        // (ID_SIZE + NAME_SIZE * CHAR_SIZE) calcula la mida total en bytes d'un registre de videojoc.
        // ID_SIZE representa la mida en bytes de l'ID del videojoc.
        // NAME_SIZE * CHAR_SIZE representa la mida total en bytes del nom del videojoc.
        return (id - 1) * (ID_SIZE + NAME_SIZE * CHAR_SIZE * NOTA_SIZE);
    }

}
