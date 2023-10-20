import java.io.IOException;
import java.util.*;


public class Main {
  static Scanner in = new Scanner(System.in); // System.in és global


  // Main
  public static void main(String[] args) throws InterruptedException, IOException {
    boolean running = true;
    while (running) {
      String menu = "Escull una opcio:";
      menu = menu + "\n 0) PR120ReadFile";
      menu = menu + "\n 1) PR121Files";
      menu = menu + "\n 2) PR122cat";
      menu = menu + "\n 3) PR123sobreescriu";
      menu = menu + "\n 4) PR123append";
      menu = menu + "\n 5) PR124linies";
      menu = menu + "\n 6) PR125cp";
      menu = menu + "\n 7) PR131Escriu";
      menu = menu + "\n 8) PR131Llegeix";
      menu = menu + "\n 9) PR132main";
      menu = menu + "\n 10) PR133mainTreballadors";
      menu = menu + "\n 11) RegistreEstudiants";
      menu = menu + "\n 12)PR140Main" ;
      menu = menu + "\n 13)PR141Main" ;




      // Adapta aquí les altres classes de l’exercici (PR122cat…)
      menu = menu + "\n 100) Sortir";
      System.out.println(menu);


      int opcio = Integer.valueOf(llegirLinia("Opcio:"));
      try {
        switch (opcio) {
          case 0: PR120ReadFile.main(args); break;
          case 1: PR121Files.main(args); break;
          // Adapta aquí les altres classes de l’exercici (PR122cat…)
          case 2: PR122cat.main(args); break;
          case 3: PR123sobreescriu.main(args);break;
          case 4: PR123append.main(args);break;
          case 5: PR124linies.main(args);break;
          case 6: PR125cp.main(args);break;
          case 7: PR131mainEscriu.main(args);break;
          case 8: PR131mainLlegeix.main(args);break;
          case 9: PR132main.main(args);break;
          case 10: PR133mainTreballadors.main(args);break;
          case 11: RegistreEstudiants.main(args);break;
          case 12: PR140Main.main(args);break;
          case 13: PR141Main.main(args);;break;
          case 100: running = false; break;
          default: break;
        }
      } catch (Exception e) {
          System.out.println(e);
      }
    }
    in.close();
  }


  static public String llegirLinia (String text) {
    System.out.print(text);
    return in.nextLine();
  }
}
