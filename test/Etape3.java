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
                if (line.trim().isEmpty()) {
                    continue; // Ignore les lignes vides
                }
                char character = line.charAt(0);
                String code = line.substring(2); // Le code commence à l'index 2
                huffmanCodes.put(character, code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return huffmanCodes;
    }

    public static void compressText(String inputFilename, String outputFilename, Map<Character, String> huffmanCodes) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename))) {
            int c;
            while ((c = br.read()) != -1) {
                char character = (char) c;
                String code = huffmanCodes.get(character);
                if (code != null) {
                    bw.write(code);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Etape3 <input_file> <output_file>");
            return;
        }
        Map<Character, String> huffmanCodes = readHuffmanCodes("huffman_codes.txt");
        compressText(args[0], args[1], huffmanCodes);
    }
}