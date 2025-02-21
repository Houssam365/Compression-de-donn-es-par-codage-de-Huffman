import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Etape2 {

    public static List<List<Object>> readCharacterFrequencies(String filename) {
        List<List<Object>> characterFrequencies = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Ignore les lignes vides
                }
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    char character = parts[1].charAt(0);
                    int frequency = Integer.parseInt(parts[0]);
                    List<Object> pair = new ArrayList<>();
                    pair.add(character);
                    pair.add(frequency);
                    characterFrequencies.add(pair);
                } else if (parts.length == 1) {
                    // Gérer le cas où il n'y a qu'un seul élément (fréquence de l'espace)
                    int frequency = Integer.parseInt(parts[0]);
                    List<Object> pair = new ArrayList<>();
                    pair.add(' '); // Ajouter l'espace comme caractère
                    pair.add(frequency);
                    characterFrequencies.add(pair);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return characterFrequencies;
    }

    public static void main(String[] args) {
        List<List<Object>> characterFrequencies = readCharacterFrequencies("sorted_characters.txt");
        for (List<Object> pair : characterFrequencies) {
            System.out.println("Character: '" + pair.get(0) + "', Frequency: " + pair.get(1));
        }
    }
}