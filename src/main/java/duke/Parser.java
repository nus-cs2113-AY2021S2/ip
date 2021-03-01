package duke;

import java.time.LocalDate;

public class Parser {
    public static boolean checkForSubstring(String[] input, String substring){
        for(String string : input){
            if(string.equals(substring)){
                return true;
            }
        }
        return false;
    }

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

    public static boolean checkIfInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isSpecialCharacterPresent(String s){
        if(s.contains("|")){
            System.out.println("Your input cannot contain the special character '|'");
            return true;
        }
        return false;
    }

    public static boolean checkValidLocalDate(String s){
        try{
            LocalDate date = LocalDate.parse(s);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
