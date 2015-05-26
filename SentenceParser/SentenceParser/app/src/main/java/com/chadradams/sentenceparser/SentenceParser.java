package com.chadradams.sentenceparser;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by chadadams on 4/3/15.
 */
public class SentenceParser {

    protected String inputVal;
    protected String[] sentenceArr;
    protected String outputValue = "";

    /*
        Config Parser constructor and set variables.
     */
    protected SentenceParser(String inputValue) {
        inputVal = inputValue;
        sentenceArr = inputVal.split(" ");
    }

    protected String createParsedWord(String wordToFormat) {
        String _wordToFormat = wordToFormat;
        char[] charArray = _wordToFormat.toCharArray();
        List<String> wordArrayUpdated = new ArrayList<String>();
        boolean HasPeriod = false;
        int extraCharacters = 0;

        if (wordToFormat.length() < 3) {
            //Ignore Words smaller than 3.
        } else if (wordToFormat.length() == 3 && wordToFormat.endsWith(".")) {
            //Ignore Words that have periods at the end and are 3 characters.
        } else {
            for (int w = 0; w < charArray.length; w++) {
                wordArrayUpdated.add(String.valueOf(charArray[w]));
                if(charArray[w] == ',' || charArray[w] == '.') {
                    extraCharacters++;
                }
            }

            //Remove ending and starting values.
            wordArrayUpdated.remove(charArray.length - 1);
            wordArrayUpdated.remove(0);

            wordArrayUpdated = removeDuplicates((ArrayList<String>) wordArrayUpdated);
            if(String.valueOf(charArray[charArray.length -1]) == ".") {
                _wordToFormat = charArray[0] + String.valueOf(wordArrayUpdated.size() - extraCharacters) + ".";
            } else {
                _wordToFormat = charArray[0] + String.valueOf(wordArrayUpdated.size() - extraCharacters) + charArray[charArray.length -1];
            }


        }

        return _wordToFormat;
    }

    // HELPER: Find duplicates for wordArrayUpdated

    private static ArrayList<String> removeDuplicates(ArrayList<String> list) {

        // Store unique items in result.
        ArrayList<String> result = new ArrayList<>();

        // Record encountered Strings in HashSet.
        HashSet<String> set = new HashSet<>();

        // Loop over argument list.
        for (String item : list) {

            // If String is not in set, add it to the list and the set.
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            } else if (item == "." || item ==  ",") {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }


    /*
       Iterate each word and return a sentence with the parsed values.
     */
    public String parse() {

        for (int i = 0; i < sentenceArr.length; i++) {

            String word = sentenceArr[i];

            String updatedWord = this.createParsedWord(word);


            outputValue = outputValue + updatedWord + " ";



        }

        return outputValue;
    }

}
