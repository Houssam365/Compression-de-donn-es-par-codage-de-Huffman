import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Etape3 {

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

    public static String encodeText(String text, Map<Character, String> huffmanCodes) {
        StringBuilder encodedText = new StringBuilder();

        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }

        return encodedText.toString();
    }

    public static void writeEncodedTextToFile(String encodedText, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(encodedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Lire les codes de Huffman à partir du fichier
        Map<Character, String> huffmanCodes = readHuffmanCodes("huffman_codes.txt");

        // Texte à encoder
        String text = "bbcdffddaaeefaaffafebccbbeffaadcdabaab";

        // Encoder le texte
        String encodedText = encodeText(text, huffmanCodes);

        // Écrire le texte encodé dans un fichier
        writeEncodedTextToFile(encodedText, "encoded_text.txt");

        System.out.println("Le texte encodé a été écrit dans le fichier encoded_text.txt");
    }
}
