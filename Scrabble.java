// STUDENT_NAME:Parth Khanna
// STUDENT_ID:260733741

import java.util.*;
import java.io.*;



public class Scrabble{

    static HashSet<String> myDictionary; // this is where the words of the dictionary are stored

    // DO NOT CHANGE THIS METHOD
    // Reads dictionary from file
    public static void readDictionaryFromFile(String fileName) throws Exception {
        myDictionary=new HashSet<String>();

        BufferedReader myFileReader = new BufferedReader(new FileReader(fileName) );

        String word;
        while ((word=myFileReader.readLine())!=null) myDictionary.add(word);

 myFileReader.close();
    }



    /* Arguments: 
        char availableLetters[] : array of characters containing the letters that remain availableinj
        String prefix : Word assembled to date
        Returns: String corresponding to the longest English word starting with prefix, completed with zero or more letters from availableLetters. 
          If no such word exists, it returns the String ""
     */
     public static String longestWord(char availableLetters[], String prefix) {
     
  String longest=""; String temp = ""; String check = "";
  char leftover[];
  // example of how to check with a string is in the dictionary. Remove this line when you understand how this works. 
  if (myDictionary.contains(prefix))
    longest=prefix;
  for(int i=0;i<availableLetters.length;i++){
    check= prefix+availableLetters[i];
    if(check.length()>temp.length())
      temp=check;
    leftover=new char[availableLetters.length-1];
    for(int j =0;j<i;j++){
      leftover[j]=availableLetters[j];
    }
    for(int k =i+1;k<availableLetters.length;k++){
      leftover[k-1]=availableLetters[k];
    }
    String newLongest=longestWord(leftover, temp);
    if(newLongest.length()>longest.length()){
      if(myDictionary.contains(newLongest))
      {longest=newLongest;}
    }
  }
  return longest;
 }

    /*Sam Cleland: I don't think you need the if 
     statement in line 44 if you are checking the 
       length at the end of the for loop, you don't 
       want to replace anything until you are trying 
       to figure out at the end after you call the 
       method, everything else looks fine otherwise*/

    
    /* main method
        You should not need to change anything here.
     */
    public static void main (String args[]) throws Exception {
       
 // First, read the dictionary
 try {
     readDictionaryFromFile("englishDictionary.txt");
        }
        catch(Exception e) {
            System.out.println("Error reading the dictionary: "+e);
        }
        
        
        // Ask user to type in letters
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in) );
        char letters[]; 
        do {
            System.out.println("Enter your letters (no spaces or commas):");
            
            letters = keyboard.readLine().toCharArray();

     // now, enumerate the words that can be formed
            String longest = longestWord(letters, "");
     System.out.println("The longest word is "+longest);
        } while (letters.length!=0);

        keyboard.close();
        
    }
}