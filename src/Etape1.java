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
        trierFrequences();
    }
    
    private void calculerFrequences() {
        for (char c : texte.toCharArray()) {
            Frequences.put(c, Frequences.getOrDefault(c, 0) + 1);
        }
    }

    private void trierFrequences() {
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(Frequences.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                int valueCompare = o1.getValue().compareTo(o2.getValue());
                if (valueCompare != 0) {
                    return valueCompare;
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });

        Frequences.clear();
        for (Map.Entry<Character, Integer> entry : list) {
            Frequences.put(entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        Etape1 etape1 = new Etape1("Hello World!");
        System.out.println("Fréquences des caractères triées : " + etape1.Frequences);
    }
}