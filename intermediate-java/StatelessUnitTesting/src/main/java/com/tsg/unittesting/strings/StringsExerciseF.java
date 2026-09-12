/*********************************
* The Software Guild
* Copyright (C) 2020 Wiley edu LLC - All Rights Reserved
*********************************/
package com.tsg.unittesting.strings;

/**
 *
 * @author ahill
 */
public class StringsExerciseF {
    
    /**
     * Take in a phrase that has many words separated by spaces. 
     * Return the longest word that you can find within the phrase - 
     * don't forget, hyphenated words are allowed!
     *
     * If there is a tie, return the first instance of a word of that size.
     *
     * Ex:
     * longestWord( "Invention my dear friends is 93% perspiration 6% electricity 4% evaporation and 2% butterscotch ripple" ) ->  "perspiration"
     * longestWord( "All well-established principles should be periodically challenged" ) ->  "well-established"
     * longestWord( "Never argue with the data" ) ->  "Never"
     * 
     * @param aPhrase
     * @return String word
     */
    public static String longestWord(String aPhrase){

        //End early if no words
        if (aPhrase == null || aPhrase.isBlank()) return null;

        //Tracker variables;
        String longestWord = "";
        int longestWordLength = 0;

        //Utility variables
        int startOfWord = 0;

        //Loop through phase
        aPhrase = aPhrase.trim();
        for (int i = 0; i < aPhrase.length(); i++) {

            //If current is space, skip
            if (aPhrase.charAt(i) == ' ') {
                startOfWord = i+1;
            }

            //If next is space or end of phase, would be end of word.
            if (i+1 == aPhrase.length() || aPhrase.charAt(i+1) == ' ') {
                //If found new max, update
                if (i - startOfWord + 1 > longestWordLength) {
                    longestWord = aPhrase.substring(startOfWord, i+1);
                    longestWordLength = longestWord.length();
                }
                //Update start
                startOfWord = i+1;
            }
        }

        //Return longest word
        return longestWord;
    }
}
