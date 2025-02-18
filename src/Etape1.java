import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class Etape1 {
    String texte;
    HashMap<Character, Integer> Frequences = new HashMap<Character, Integer>();
    
    public Etape1(String texte) {
        this.texte = texte;
        calculerFrequences();
    }
    
    private void calculerFrequences() {
        for (char c : texte.toCharArray()) {
            Frequences.put(c, Frequences.getOrDefault(c, 0) + 1);
        }
    }

    public static void main(String[] args) {
        Etape1 etape1 = new Etape1("Bonjour, monde!");
        System.out.println("Fréquences des caractères : " + etape1.Frequences);
    }
}