public class DukeCommandValidator {
    static int MINIMUM_DONE_LENGTH = 5;
    static int MINIMUN_TODO_LENGTH = 6;
    static int MINIMUM_EVENT_LENGTH = 12;
    static int MINIMUM_DEADLINE_LENGTH = 15;

    public static int getCommand(String input){
        if(input.equals("bye")){
            return DukeCommands.EXIT;
        }
        if(input.equals("list")){
            return DukeCommands.LIST;
        }
        if(validateDoneCommand(input) == true){
            return DukeCommands.DONE;
        }
        if(validateAddCommand(input) == true) {
            return DukeCommands.ADD;
        }
        return DukeCommands.INVALID_COMMAND;
    }

    public static boolean validateTodo(String input){
        if(input.length()>=MINIMUN_TODO_LENGTH) {
            if (input.substring(0, 5).equals("todo ")) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateDeadline(String input){
        if(input.length()<MINIMUM_DEADLINE_LENGTH){
            return false;
        }
        if (!input.substring(0, 9).equals("deadline ")){
            return false;
        }
        // validate substring before backslash
        int indexOfBackslash = input.indexOf("/");
        if(indexOfBackslash < 11){
            return false;
        }
        // validate substring after backslash
        String stringAfterBackslash = input.substring(indexOfBackslash+1);
        int indexFirstSpaceAfterBackslash = stringAfterBackslash.indexOf(" ");
        if(indexFirstSpaceAfterBackslash < 1){
            return false;
        }
        if(stringAfterBackslash.length() == indexFirstSpaceAfterBackslash + 1){
            return false;
        }
        return true;
    }

    public static boolean validateEvent(String input){
        if(input.length()<MINIMUM_EVENT_LENGTH){
            return false;
        }
        if (!input.substring(0, 6).equals("event ")){
            return false;
        }
        // validate substring before backslash
        int indexOfBackslash = input.indexOf("/");
        if(indexOfBackslash < 8){
            return false;
        }
        // validate substring after backslash
        String stringAfterBackslash = input.substring(indexOfBackslash+1);
        int indexFirstSpaceAfterBackslash = stringAfterBackslash.indexOf(" ");
        if(indexFirstSpaceAfterBackslash < 1){
            return false;
        }
        if(stringAfterBackslash.length() == indexFirstSpaceAfterBackslash + 1){
            return false;
        }
        return true;
    }


    public static Boolean validateAddCommand(String input){

        if(input.length()>=MINIMUN_TODO_LENGTH) {
            if (input.substring(0, 5).equals("todo ")) {
                return true;
            }
        }

        if(input.length()>=MINIMUM_EVENT_LENGTH) {
            if (input.substring(0, 6).equals("event ")) {
                return true;
            }
        }

        if(input.length()>=MINIMUM_DEADLINE_LENGTH) {
            if (input.substring(0, 9).equals("deadline ")) {
                return true;
            }
        }

        return false;
    }

    public static boolean validateDoneCommand(String input){
        if(input.length()<=MINIMUM_DONE_LENGTH){
            return false;
        }
        String firstFiveChars = input.substring(0, 5);
        String sixthToLastChars = input.substring(5);
        if(firstFiveChars.equals("done ") && isInteger(sixthToLastChars)){
            return true;
        }
        return false;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}
