package duke;

import java.time.LocalDate;

public class Parser {

    /**
     * Returns true or false, according to whether a substring is present in the input array of strings
     *
     * @param input  an array of strings, to be checked
     * @param substring date which the task is to be completed by
     * @return returns true if substring is found in input, returns false otherwise
     */
    public static boolean checkForSubstring(String[] input, String substring){
        for(String string : input){
            if(string.equals(substring)){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index position of the substring, in the array of strings
     *
     * @param input  an array of strings, to be checked
     * @param substring date which the task is to be completed by
     * @return returns true if substring is found in input, returns false otherwise
     */
    public static int indexOfSubstring(String[] input, String substring){
        int index = 0;
        for(String string : input){
            if(string.equals(substring)){
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Returns true or false, according to whether a string can be converted into an integer
     *
     * @param s the input string that is to be checked if it can be converted
     * @return returns true if string can be converted to an integer, returns false otherwise
     */
    public static boolean checkIfInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true or false, according to whether a string contains the special character '|'
     *
     * @param s  a string, to be checked if it contains '|'
     * @return returns true if the character '|'is present in the string, returns false otherwise
     */
    public static boolean isSpecialCharacterPresent(String s){
        if(s.contains("|")){
            System.out.println("Your input cannot contain the special character '|'");
            return true;
        }
        return false;
    }

    /**
     * Returns true or false, according to whether a string can be converted into a LocalDate object
     *
     * @param s  a string, to be checked if it can be converted into a LocalDate object
     * @return returns true if the string can be converted into a LocalDate object, returns false otherwise
     */
    public static boolean checkValidLocalDate(String s){
        try{
            LocalDate date = LocalDate.parse(s);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
