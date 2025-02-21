import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Etape1 {

    public static int etape1(String[] args) {
        // Chaîne de caractères à trier
        String str = "this is an example of a huf_@749_ fman tree";

        // Convertir la chaîne en tableau de caractères
        char[] charArray = str.toCharArray();

        // Trier le tableau de caractères
        Arrays.sort(charArray);

        // Compter les occurrences de chaque caractère
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : charArray) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Utiliser un TreeSet pour stocker les lignes uniques et les trier par ordre croissant du nombre d'occurrences
        Set<String> sortedLines = new TreeSet<>((line1, line2) -> {
            int count1 = Integer.parseInt(line1.split(" ")[0]);
            int count2 = Integer.parseInt(line2.split(" ")[0]);
            if (count1 == count2) {
                return line1.compareTo(line2);
            }
            return Integer.compare(count1, count2);
        });

        // Ajouter les lignes au TreeSet
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            sortedLines.add(entry.getValue() + " " + entry.getKey());
        }

        // Écrire les lignes triées et uniques dans un fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sorted_characters.txt"))) {
            for (String line : sortedLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Retourner le nombre de caractères distincts
        return frequencyMap.size();
    }

    public static void main(String[] args) {
        int distinctCharacterCount = etape1(args);
        System.out.println("Nombre de caractères distincts : " + distinctCharacterCount);
    }
}
