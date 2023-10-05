import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class PR131mainEscriu {
    public static void main(String[] args) {
        //Create variables that contein the paths
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "PR131hashmap.java";

        try {
        //Create file and data output
        FileOutputStream fos = new FileOutputStream(filePath);
        DataOutputStream dos = new DataOutputStream(fos);
        }catch(Exception e){}
    }
}
