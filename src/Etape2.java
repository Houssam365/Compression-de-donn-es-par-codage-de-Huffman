import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Etape2 {

    static class Node {
        char character;
        int frequency;
        Node left;
        Node right;

        Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        Node(int frequency, Node left, Node right) {
            this.character = '\0'; // '\0' indicates an internal node
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }
    }

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

    public static Node buildHuffmanTree(List<List<Object>> characterFrequencies) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

        // Ajouter tous les caractères à la file de priorité
        for (List<Object> pair : characterFrequencies) {
            char character = (char) pair.get(0);
            int frequency = (int) pair.get(1);
            priorityQueue.add(new Node(character, frequency));
        }

        // Construire l'arbre de Huffman
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            // Placer le nœud avec la fréquence la plus basse à gauche et le plus élevé à droite
            Node parent = new Node(left.frequency + right.frequency, left, right);
            priorityQueue.add(parent);
        }

        // Retourner la racine de l'arbre de Huffman
        return priorityQueue.poll();
    }

    public static void printHuffmanCodes(Node root, String code, BufferedWriter writer) throws IOException {
        if (root == null) {
            return;
        }

        // Si c'est une feuille, écrire le caractère et son code dans le fichier
        if (root.left == null && root.right == null) {
            writer.write(root.character + " " + code);
            writer.newLine();
            return;
        }

        // Parcourir les sous-arbres gauche et droit
        printHuffmanCodes(root.left, code + "0", writer);
        printHuffmanCodes(root.right, code + "1", writer);
    }

    public static void writeHuffmanCodesToFile(Node root, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            printHuffmanCodes(root, "", writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printHuffmanTree(Node root, String indent) {
        if (root == null) {
            return;
        }

        // Afficher le nœud courant
        if (root.character != '\0') {
            System.out.println(indent + "Character: '" + root.character + "', Frequency: " + root.frequency);
        } else {
            System.out.println(indent + "Internal Node, Frequency: " + root.frequency);
        }

        // Parcourir les sous-arbres gauche et droit avec une indentation accrue
        printHuffmanTree(root.left, indent + "  ");
        printHuffmanTree(root.right, indent + "  ");
    }

    public static void main(String[] args) {
        List<List<Object>> characterFrequencies = readCharacterFrequencies("sorted_characters.txt");
        Node huffmanTreeRoot = buildHuffmanTree(characterFrequencies);
        System.out.println("Huffman Codes:");
        writeHuffmanCodesToFile(huffmanTreeRoot, "huffman_codes.txt");
        System.out.println("\nHuffman Tree:");
        printHuffmanTree(huffmanTreeRoot, "");
    }
}