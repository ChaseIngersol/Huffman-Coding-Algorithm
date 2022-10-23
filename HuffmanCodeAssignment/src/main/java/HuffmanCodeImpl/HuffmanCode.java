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

        PriorityQueue nodeQueue = charFreqMap.convertToQueue();

        Node root = null;
        while (nodeQueue.size() > 1) {
            Node first = nodeQueue.peekMin();
            nodeQueue.remove();
            Node second = nodeQueue.peekMin();
            nodeQueue.remove();

            Node newParent = new Node();
            newParent.freq = first.freq + second.freq;
            newParent.ch = '~';
            newParent.left = first;
            newParent.right = second;
            root = newParent;
            nodeQueue.insert(newParent);

        }

        HashMap<Character, String> encodedData = new HashMap<>();

        encodeData(root, "", encodedData);

        System.out.println("Character | Binary");
        encodedData.display();


    }

    public static boolean isLeaf(Node root)
    {
        //returns true if both conditions return ture
        return root.left == null && root.right == null;
    }

    public static void encodeData(Node root, String str, HashMap<Character, String> huffmanCode)
    {
        if (root == null)
        {
            return;
        }
        //checks if the node is a leaf node or not
        if (isLeaf(root))
        {
            huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
        }
        encodeData(root.left, str + '0', huffmanCode);
        encodeData(root.right, str + '1', huffmanCode);
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