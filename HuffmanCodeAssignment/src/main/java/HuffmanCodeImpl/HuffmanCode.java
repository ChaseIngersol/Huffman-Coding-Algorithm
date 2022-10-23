package HuffmanCodeImpl;

import java.util.Scanner;

public class HuffmanCode {

    //driver code
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        String input = sanitizeInput(s.nextLine());


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

}  