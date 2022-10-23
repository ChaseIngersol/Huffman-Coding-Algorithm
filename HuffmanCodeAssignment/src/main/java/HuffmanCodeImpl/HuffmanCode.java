package HuffmanCodeImpl;

import java.util.Scanner;

public class HuffmanCode {

    public static void main(String args[]) {

        // Get user input
        System.out.println("Enter a string in all lowercase: ");
        Scanner s = new Scanner(System.in);
        String input = sanitizeInput(s.nextLine());

        // Build character frequency map from input string
        HashMap<Character, Integer> charFreqMap = buildFreqMap(input);

        // Print frequency map
        System.out.println("Character | Frequency:");
        charFreqMap.display();

        // Convert the frequency map to a priority queue to build the Huffman Tree with
        PriorityQueue nodeQueue = charFreqMap.convertToPriorityQueue();

        // Build the Huffman Tree from the priority queue
        Node root = buildHuffmanTree(null, nodeQueue);

        // Build the binary encoded character map
        HashMap<Character, String> encodedMap = new HashMap<>();
        encodeToMap(root, "", encodedMap);

        // Display the binary encoded character map
        System.out.print("\nCharacter | Binary\n");
        encodedMap.display();
        System.out.println();

        // Display the original uncompressed input string
        System.out.println("Original string uncompressed: " + "\n" + input + "\n");

        // Display the compressed binary encoded input string
        StringBuilder sb = new StringBuilder();
        System.out.println("Original string compressed: " + printEncodedString(sb, input, encodedMap) + "\n");

        // Display the original input by decoding from the compressed binary string
        System.out.println("The compressed string when decoded is: ");
        printDecodedString(sb, root);
    }

    public static void printDecodedString(StringBuilder sb, Node root) {
        if (isLeaf(root)) {
            while (root.freq-- > 0) {
                System.out.print(root.ch);
            }
        } else {
            int index = -1;
            while (index < sb.length() - 1) {
                index = decodeFromHuffmanTree(root, index, sb);
            }
        }
        System.out.println();
    }

    public static String printEncodedString(StringBuilder sb, String input, HashMap<Character, String> encodedMap) {
        for (char c : input.toCharArray()) {
            sb.append(encodedMap.get(c));
        }
        return sb.toString();
    }

    public static Node buildHuffmanTree(Node root, PriorityQueue queue) {
        while (queue.size() > 1) {
            Node first = queue.peekMin();
            queue.remove();
            Node second = queue.peekMin();
            queue.remove();
            Node newParent = new Node();
            newParent.freq = first.freq + second.freq;
            newParent.ch = '~';
            newParent.left = first;
            newParent.right = second;
            root = newParent;
            queue.insert(newParent);
        }
        return root;
    }

    public static void encodeToMap(Node root, String str, HashMap<Character, String> huffmanCode) {
        if (root == null) {
            return;
        }
        if (isLeaf(root)) {
            huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
        }
        encodeToMap(root.left, str + '0', huffmanCode);
        encodeToMap(root.right, str + '1', huffmanCode);
    }

    public static int decodeFromHuffmanTree(Node root, int index, StringBuilder sb) {
        if (root == null) {
            return index;
        }
        if (isLeaf(root)) {
            System.out.print(root.ch);
            return index;
        }
        index++;
        root = (sb.charAt(index) == '0') ? root.left : root.right;
        index = decodeFromHuffmanTree(root, index, sb);
        return index;
    }

    public static HashMap<Character, Integer> buildFreqMap(String input) {
        HashMap<Character, Integer> charFreqMap = new HashMap<Character, Integer>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            Integer val = charFreqMap.get(c);
            if (val != null) {
                charFreqMap.put(c, val + 1);
            } else {
                charFreqMap.put(c, 1);
            }
        }
        return charFreqMap;
    }

    public static boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }

    public static String sanitizeInput(String input) {
        StringBuilder inputSanitized = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 32 || input.charAt(i) >= 65 && input.charAt(i) <= 90 || input.charAt(i) >= 97 && input.charAt(i) <= 122) {
                inputSanitized.append(input.charAt(i));
            }
        }
        return inputSanitized.toString();
    }
}