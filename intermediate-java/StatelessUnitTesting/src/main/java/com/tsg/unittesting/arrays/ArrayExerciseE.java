/*********************************
* The Software Guild
* Copyright (C) 2020 Wiley edu LLC - All Rights Reserved
*********************************/
package com.tsg.unittesting.arrays;

import java.util.Locale;

/**
 *
 * @author ahill
 */
public class ArrayExerciseE {
    
    /**
     * Given an array of words turn it into a single camelCased phrase.
     * Lower case the first word, capitalize the first letter (but only the first) of the rest.
     *
     * camelCaseIt( {"llama", "llama", "duck"}  ) ->  "llamaLlamaDuck"
     * camelCaseIt( {"lambs", "eat", "oats", "and", "does", "eat", "oats"}  ) ->  "lambsEatOatsAndDoesEatOats"
     * camelCaseIt( {"DO", "OR", "DO", "NOT", "THERE", "IS", "NO", "TRY"}  ) ->  "doOrDoNotThereIsNoTry"
     * @param words
     * @return String camelCased phrase
     * @implNote Implemented by VrishtiVA
     */

    public static String camelCaseIt(String[] words){

        //If no words
        if (words.length == 0) return "";

        //Utility objects
        StringBuilder stringBuilder = new StringBuilder();

        //Tracking variables
        boolean addedFirstWord = false;

        //Add rest of the words
        for (String w : words) {

            //Skip if empty word
            if (w.isEmpty()) continue;

            //If we haven't added first word, add lowercase word
            if (!addedFirstWord) {
                stringBuilder.append(w.toLowerCase());
                addedFirstWord = true;

            } else {
                //Otherwise follow camel case
                stringBuilder.append(Character.toUpperCase(w.charAt(0)));
                //Check if more than 1 character to avoid errors with substring
                if (w.length() > 1) {
                    stringBuilder.append(w.substring(1).toLowerCase());
                }
            }
        }

        //Return built string
        return stringBuilder.toString();

    }
    
}
