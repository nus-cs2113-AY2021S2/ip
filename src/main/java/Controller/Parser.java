package controller;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Parser class that parses the string input by the user.
 */
public class Parser {

    /**
     * Method that parses string input by user to separate them into different substrings.
     * @param input String input by user.
     * @return Substrings of string input.
     */
    public String[] processInput(String input) {
        String[] strings = new String[3];
        input = input.trim();
        if (!input.contains(" ")) {
            strings[0] = input;
            return strings;
        }
        int index = input.indexOf(" ");
        String subString1 = input.substring(index+1);
        strings[0] = input.substring(0,index);
        if (!subString1.contains("/")) {
            strings[1] = subString1;
            return strings;
        }
        else {
            int index1 = subString1.indexOf("/");
            String subString2 = subString1.substring(0, index1-1);
            String subString3 = subString1.substring(index1);
            int index2 = subString3.indexOf(" ");
            String subString4 = subString3.substring(index2+1);
            strings[1] = subString2;
            strings[2] = subString4;
            return strings;
        }
    }

    /**
     * Method that parses string input to LocalDate.
     * @param strings String input by user.
     * @return LocalDate.
     * @throws DateTimeParseException
     */
    public LocalDate processString(String[] strings) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datetime = LocalDate.parse(strings[1], formatter);
        return datetime;
    }


    /**
     * Return integer from 1-99 by parsing through string input by user.
     * @param input String input by user.
     * @return integer from 1-99.
     */
    public int charNumber(String input) {
        char ch1, ch2;
        for (int i = 0; i < input.length(); i++){
            ch1 = input.charAt(i);
            int index = i;
            if (++index == input.length()){
                if(Character.isDigit(ch1)) {
                    return Character.getNumericValue(ch1);
                }
            }
            ch2 = input.charAt(index);
            if(Character.isDigit(ch1)&&Character.isDigit(ch2)) {
                return Character.getNumericValue(ch1)*10 + Character.getNumericValue(ch2);
            }
        }
        return 0;
    }
}
