import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Etape4_5 {

    public static Map<Character, String> readHuffmanCodes(String filename) {
        Map<Character, String> huffmanCodes = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    char character = parts[0].charAt(0);
                    String code = parts[1];
                    huffmanCodes.put(character, code);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return huffmanCodes;
    }

    public static Map<Character, Integer> readCharacterFrequencies(String filename) {
        Map<Character, Integer> characterFrequencies = new HashMap<>();

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
                    characterFrequencies.put(character, frequency);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return characterFrequencies;
    }

    public static double calculateCompressionRate(String text, Map<Character, String> huffmanCodes, Map<Character, Integer> characterFrequencies) {
        int initialVolume = text.length(); // Volume initial en octets

        int finalVolumeBits = 0;
        for (Map.Entry<Character, Integer> entry : characterFrequencies.entrySet()) {
            char character = entry.getKey();
            int frequency = entry.getValue();
            int codeLength = huffmanCodes.get(character).length();
            finalVolumeBits += frequency * codeLength;
        }

        int finalVolume = (int) Math.ceil(finalVolumeBits / 8.0); // Convertir les bits en octets

        return 1 - ((double) finalVolume / initialVolume);
    }

    public static double calculateAverageBitsPerCharacter(Map<Character, String> huffmanCodes, Map<Character, Integer> characterFrequencies) {
        int totalBits = 0;
        int totalCharacters = 0;

        for (Map.Entry<Character, Integer> entry : characterFrequencies.entrySet()) {
            char character = entry.getKey();
            int frequency = entry.getValue();
            int codeLength = huffmanCodes.get(character).length();
            totalBits += frequency * codeLength;
            totalCharacters += frequency;
        }

        return (double) totalBits / totalCharacters;
    }

    public static void main(String[] args) {
        // Lire les codes de Huffman à partir du fichier
        Map<Character, String> huffmanCodes = readHuffmanCodes("huffman_codes.txt");

        // Lire les fréquences des caractères à partir du fichier
        Map<Character, Integer> characterFrequencies = readCharacterFrequencies("sorted_characters.txt");

        // Texte à encoder
        String text = "bbcdffddaaeefaaffafebccbbeffaadcdabaab";

        // Calculer le taux de compression
        double compressionRate = calculateCompressionRate(text, huffmanCodes, characterFrequencies);
        System.out.println("Taux de compression : " + compressionRate);

        // Calculer le nombre moyen de bits nécessaires pour stocker un caractère
        double averageBitsPerCharacter = calculateAverageBitsPerCharacter(huffmanCodes, characterFrequencies);
        System.out.println("Nombre moyen de bits par caractère : " + averageBitsPerCharacter);
    }
}
