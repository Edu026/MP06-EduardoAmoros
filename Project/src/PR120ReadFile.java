import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PR120ReadFile {
    public static void main(String[] args) {
       String rutaAbsoluta = "C:\\Users\\PC\\Documents\\Programacion\\PR1.2-Lectura-i-escriptura\\Project\\src\\PR120ReadFile.java";
       
        try {
            List<String> linies = Files.readAllLines(Paths.get(rutaAbsoluta, args), StandardCharsets.UTF_8);
            for (int cnt = 0; cnt < linies.size(); cnt = cnt + 1) {
                System.out.println(cnt + "      " + linies.get(cnt));
            }
        } catch (IOException e) { e.printStackTrace(); }

    }
}   

