import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Etape1 {
    public static int etape1(String inputFilename, String outputFilename) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                str.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convertir la chaîne en tableau de caractères
        char[] charArray = str.toString().toCharArray();
        
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            writer.write(frequencyMap.size() + "\n"); // Écrire la taille de l'alphabet
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
        if (args.length != 2) {
            System.out.println("Usage: java Etape1 <input_file> <output_file>");
            return;
        }
        int distinctCharacterCount = etape1(args[0], args[1]);
        System.out.println("Nombre de caractères distincts : " + distinctCharacterCount);
    }
}