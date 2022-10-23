package HuffmanCodeImpl;

import java.util.Scanner;

public class HuffmanCode {

    //driver code
    public static void main(String args[]) {
        System.out.println("Enter a string in all lowercase: ");
        Scanner s = new Scanner(System.in);
        String input = sanitizeInput(s.nextLine());

        HashMap<Character, Integer> charFreqMap = buildFreqMap(input);
        System.out.println("Character | Frequency:");
        charFreqMap.display();

        PriorityQueue huffmanTree = charFreqMap.convertToQueue();

        Node root = null;
        while (huffmanTree.size() > 1) {
            Node first = huffmanTree.peekMin();
            huffmanTree.remove();
            Node second = huffmanTree.peekMin();
            huffmanTree.remove();

            Node newParent = new Node();
            newParent.freq = first.freq + second.freq;
            newParent.ch = '~';
            newParent.left = first;
            newParent.right = second;
            root = newParent;
            huffmanTree.insert(newParent);

        }

        HashMap<Character, String> encodedMap = new HashMap<>();

        encodeData(root, "", encodedMap);

        System.out.print("\nCharacter | Binary");
        encodedMap.display();
        System.out.println();

        System.out.println("Original string uncompressed: " + "\n" + input + "\n");
        System.out.println("Original string compressed: ");

        StringBuilder sb = new StringBuilder();
        //loop iterate over the character array
        for (char c : input.toCharArray()) {
            //prints encoded string by getting characters
            sb.append(encodedMap.get(c));
        }
        System.out.println(sb + "\n");


        System.out.println("The compressed string when decoded is: ");
        if (isLeaf(root)) {
            //special case: For input like a, aa, aaa, etc.
            while (root.freq-- > 0) {
                System.out.print(root.ch);
            }
        } else {
            //traverse over the Huffman tree again and this time, decode the encoded string
            int index = -1;
            while (index < sb.length() - 1) {
                index = decodeData(root, index, sb);
            }
        }
        System.out.println();


    }



    public static void encodeData(Node root, String str, HashMap<Character, String> huffmanCode) {
        if (root == null) {
            return;
        }
        //checks if the node is a leaf node or not
        if (isLeaf(root)) {
            huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
        }
        encodeData(root.left, str + '0', huffmanCode);
        encodeData(root.right, str + '1', huffmanCode);
    }

    public static int decodeData(Node root, int index, StringBuilder sb) {
        //checks if the root node is null or not
        if (root == null) {
            return index;
        }
        //checks if the node is a leaf node or not
        if (isLeaf(root)) {
            System.out.print(root.ch);
            return index;
        }
        index++;
        root = (sb.charAt(index) == '0') ? root.left : root.right;
        index = decodeData(root, index, sb);
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
        //returns true if both conditions return ture
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