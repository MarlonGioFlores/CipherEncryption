package CipherEncryption.Week1;
import edu.duke.*;
import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet upper and lowercase.
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabetUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0, key);
        String shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);
        //String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            // Check if it's an uppercase letter
            if (Character.isUpperCase(currChar)){
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabetUpper.indexOf(currChar);
            //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabetUpper.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
            // Check if it's an lowercase letter
            else if(Character.isLowerCase(currChar)){
                int idx = alphabetLower.indexOf(currChar);
                if(idx != -1){                    
                    char newChar = shiftedAlphabetLower.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    
    public boolean isVowel(char ch){
     ch = Character.toLowerCase(ch);
         if (ch == 'a' | ch == 'e' | ch == 'i' | ch == 'o' | ch == 'u'){
             return(true);          
         
        }
        else return(false);
           
    }
    
    public String replaceVowels( String phrase, char ch){
        StringBuilder result  = new StringBuilder(phrase);
      for (int i = 0; i < result.length(); i++) {
               char str = result.charAt(i);
               if (isVowel(str)){
                    
                   result.setCharAt(i, ch);
                }
        }
        return result.toString();
    }
    
    public StringBuilder emphasize(StringBuilder phrase, char ch) {
        char lowercaseCh = Character.toLowerCase(ch);
        char uppercaseCh = Character.toUpperCase(ch);
        
        for (int i = 0; i < phrase.length(); i++) {
            char str = phrase.charAt(i);
            if (str == lowercaseCh || str == uppercaseCh){
                
                    if (i%2 == 0){
                        phrase.setCharAt(i, '*');
                    }
                    else {
                        phrase.setCharAt(i, '+');
                    }
                }
            }
        
        return(phrase);
    
    }
    
    public String encryptTwokeys(String input, int key1, int key2) {
        int n = input.length();
        String original = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted_even = original.substring(key1, 26);
        shifted_even = shifted_even + original.substring(0, key1);
        String shifted_odd = original.substring(key2, 26);
        shifted_odd = shifted_odd + original.substring(0, key2);
        
        String newinput1 = new String();
        String newinput2 = new String();
        String newinput = new String();
        
        for (int i = 0; i < n; i= i + 2) {
            int position = original.indexOf(input.substring(i, i + 1));
            if (position == -1) {
                position = original.toLowerCase().indexOf(input.substring(i, i + 1));
                if (position > -1) newinput1 = newinput1 + shifted_even.substring(position, position+1).toLowerCase();
                else newinput1 = newinput1 + input.substring(i, i + 1);
            }
            else if (position > -1) newinput1 = newinput1 + shifted_even.substring(position, position+1);
        }
        
        for (int i = 1; i < n; i= i + 2) {
            int position = original.indexOf(input.substring(i, i + 1));
            if (position == -1) {
                position = original.toLowerCase().indexOf(input.substring(i, i + 1));
                if (position > -1) newinput2 = newinput2 + shifted_odd.substring(position, position+1).toLowerCase();
                else newinput2 = newinput2 + input.substring(i, i + 1);
            }
            else if (position > -1) newinput2 = newinput2 + shifted_odd.substring(position, position+1);
        }
        
        int n1 = newinput1.length();
        int n2 = newinput2.length();
        
        if (n1 > n2) {
            for (int i = 0; i < n2; i++) {
                newinput = newinput + newinput1.substring(i, i+1);
                newinput = newinput + newinput2.substring(i, i+1);
            }
            newinput = newinput + newinput1.substring(n1-1, n1);
        }
        
        if (n1 == n2) {
            for (int i = 0; i < n2; i++) {
                newinput = newinput + newinput1.substring(i, i+1);
                newinput = newinput + newinput2.substring(i, i+1);
            }
        }
        
        if (n1 < n2) {
            for (int i = 0; i < n1; i++) {
                newinput = newinput + newinput1.substring(i, i+1);
                newinput = newinput + newinput2.substring(i, i+1);
            }
            newinput = newinput + newinput2.substring(n2-1, n2);
        }
        
        return(newinput);
    }
      
    
    public void testCaesar() {
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is: " + encrypted);       
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    public void testisVowel(){
     char str = 'A';
     boolean result = isVowel(str);
     System.out.println(result);
    }
    
    public void testreplaceVowels(){
     String phrase = "HELLO WORLD";
     String result = replaceVowels(phrase, '*');
     System.out.println(result);
    }
    
    public void testemphasize() {
        StringBuilder phrase = new StringBuilder("Mary Bella Abracadabra");
        StringBuilder result = emphasize(phrase, 'a');
        System.out.println(result);
    }
    
    public void testencryptTwoKeys() {
        String message = "Top ncmy qkff vi vguv vbg ycpx";
        String result = encryptTwokeys(message,8 , 21);
        System.out.println(result);
}

}
