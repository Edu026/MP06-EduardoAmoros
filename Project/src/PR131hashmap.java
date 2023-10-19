import java.io.Serializable;
import java.util.HashMap;

public class PR131hashmap implements Serializable{
    HashMap<String,Integer> map = new HashMap<String,Integer>();

    PR131hashmap() {
        map.put("Sara",20);
        map.put("Alba",26);
        map.put("Pedro",21);
    }

    public HashMap<String,Integer> getHashMap(){
        return this.map;
    }

    public void addPerson (String nom, Integer edat){
        this.map.put(nom, edat);
    }
}
