package HuffmanCodeImpl;

import java.util.Arrays;
import java.util.Scanner;

public class HuffmanTree {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        input = sanitizeInput(input);

        char[] uniqueChars = new char[input.length()];
        int[] charFreq = new int[input.length()];

        for(int i = 0; i < input.length(); i++){
            for(int j = 0; j < uniqueChars.length; j++){
                if(input.charAt(i) == uniqueChars[j]){
                    continue;
                } else {
                    uniqueChars[i] = input.charAt(i);
                }
            }
        }

        String uniqueCharsAsString = printArray(uniqueChars);
        System.out.println(uniqueCharsAsString);




        String test = "";

    }

    public static String sanitizeInput(String input){
        StringBuilder inputSanitized = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == 32 || input.charAt(i) >= 65 && input.charAt(i) <= 90 || input.charAt(i) >= 97 && input.charAt(i) <= 122){
                inputSanitized.append(input.charAt(i));
            }
        }
        return inputSanitized.toString();
    }

    public static String printArray(char[] array){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < array.length; i++){
            sb.append(array[i]);
        }
        return sb.toString();
    }
}
