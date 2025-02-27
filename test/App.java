import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.List;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java App <input_file>");
            return;
        }
        String inputFilename = args[0];
        String baseFilename = inputFilename.substring(0, inputFilename.lastIndexOf('.'));
        String outputDir = "output";

        // Créer le répertoire de sortie s'il n'existe pas
        new File(outputDir).mkdirs();

        // Étape 1 : Détermination de l'alphabet et des fréquences de caractères
        int distinctCharacterCount = Etape1.etape1(inputFilename, outputDir + "/" + baseFilename + "_freq.txt");
        System.out.println("Nombre de caractères distincts : " + distinctCharacterCount);

        // Étape 2 : Construction de l'arbre de codage
        List<List<Object>> characterFrequencies = Etape2.readCharacterFrequencies(outputDir + "/" + baseFilename + "_freq.txt");
        Etape2.Node huffmanTreeRoot = Etape2.buildHuffmanTree(characterFrequencies);
        System.out.println("Huffman Codes:");
        Etape2.writeHuffmanCodesToFile(huffmanTreeRoot, outputDir + "/" + baseFilename + "_huffman_codes.txt");
        System.out.println("\nHuffman Tree:");
        Etape2.printHuffmanTree(huffmanTreeRoot, "");

        // Étape 3 : Codage et compression du texte
        Etape3.compressText(inputFilename, outputDir + "/" + baseFilename + "_comp.bin", Etape3.readHuffmanCodes(outputDir + "/" + baseFilename + "_huffman_codes.txt"));

        // Étape 4 : Détermination du taux de compression
        Map<Character, Integer> characterFrequenciesMap = Etape4_5.readCharacterFrequencies(outputDir + "/" + baseFilename + "_freq.txt");
        String text = readFile(inputFilename); // Lire le texte à partir du fichier
        double compressionRate = Etape4_5.calculateCompressionRate(text, Etape3.readHuffmanCodes(outputDir + "/" + baseFilename + "_huffman_codes.txt"), characterFrequenciesMap);
        System.out.println("Taux de compression : " + compressionRate);

        // Étape 5 : Détermination du nombre moyen de bits de stockage d’un caractère
        double averageBitsPerCharacter = Etape4_5.calculateAverageBitsPerCharacter(Etape3.readHuffmanCodes(outputDir + "/" + baseFilename + "_huffman_codes.txt"), characterFrequenciesMap);
        System.out.println("Nombre moyen de bits par caractère : " + averageBitsPerCharacter);
    }

    private static String readFile(String filename) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}