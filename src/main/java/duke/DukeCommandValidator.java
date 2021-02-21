package duke;

public class DukeCommandValidator {
    static final int MINIMUM_DONE_COMMAND_LENGTH = "done x".length();
    static final int MINIMUM_TODO_COMMAND_LENGTH = "todo x".length();
    static final int MINIMUM_EVENT_COMMAND_LENGTH = "event x /x x".length();
    static final int MINIMUM_INDEX_OF_BACKSLASH_FOR_EVENT = "event x /".indexOf('/');
    static final int MINIMUM_DEADLINE_COMMAND_LENGTH = "deadline x /x x".length();
    static final int MINIMUM_INDEX_OF_BACKSLASH_FOR_DEADLINE = "deadline x /".indexOf('/');
    static final int MINIMUM_DELETE_COMMAND_LENGTH = "delete x".length();

    public static int getCommand(String input){
        if(input.equals("bye")){
            return DukeCommands.EXIT;
        }
        if(input.equals("list")){
            return DukeCommands.LIST;
        }
        if(isDoneCommandValid(input) == true){
            return DukeCommands.DONE;
        }
        if(isAddCommandValid(input) == true) {
            return DukeCommands.ADD;
        }
        if(isDeleteCommandValid(input) == true) {
            return DukeCommands.DELETE;
        }
        return DukeCommands.INVALID_COMMAND;
    }

    private static boolean isDoneCommandValid(String input){
        if(input.length()< MINIMUM_DONE_COMMAND_LENGTH){
            return false;
        }
        String firstFiveChars = input.substring(0, "done ".length());
        String sixthToLastChars = input.substring("done ".length());
        if(firstFiveChars.equals("done ") && isInteger(sixthToLastChars)){
            return true;
        }
        return false;
    }

    private static Boolean isAddCommandValid(String input){
        if(input.length()>= MINIMUM_TODO_COMMAND_LENGTH) {
            if (input.substring(0, "todo ".length()).equals("todo ")) {
                return true;
            }
        }
        if(input.length()>= MINIMUM_EVENT_COMMAND_LENGTH) {
            if (input.substring(0, ("event ").length()).equals("event ")) {
                return true;
            }
        }
        if(input.length()>= MINIMUM_DEADLINE_COMMAND_LENGTH) {
            if (input.substring(0, "deadline ".length()).equals("deadline ")) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDeleteCommandValid(String input){
        if(input.length()< MINIMUM_DELETE_COMMAND_LENGTH){
            return false;
        }
        if(!input.substring(0,MINIMUM_DELETE_COMMAND_LENGTH - 1).equals("delete ")){
            return false;
        }
        // valid string after "delete "
        String indexToDelete = input.substring(MINIMUM_DELETE_COMMAND_LENGTH - 1);
        if(!isInteger(indexToDelete)){
            return false;
        }
        return true;
    }

    public static boolean isToDoValid(String input){
        if(input.length()>= MINIMUM_TODO_COMMAND_LENGTH) {
            if (input.substring(0, MINIMUM_TODO_COMMAND_LENGTH - 1).equals("todo ")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDeadlineValid(String input){
        if(input.length()< MINIMUM_DEADLINE_COMMAND_LENGTH){
            return false;
        }
        if (!input.substring(0, "deadline ".length()).equals("deadline ")){
            return false;
        }
        // validate substring before backslash
        int indexOfBackslash = input.indexOf("/");
        if(indexOfBackslash < MINIMUM_INDEX_OF_BACKSLASH_FOR_DEADLINE){
            return false;
        }
        // validate substring after backslash
        String stringAfterBackslash = input.substring(indexOfBackslash + 1);
        int indexFirstSpaceAfterBackslash = stringAfterBackslash.indexOf(" ");
        if(indexFirstSpaceAfterBackslash < 1){
            return false;
        }
        if(stringAfterBackslash.length() == indexFirstSpaceAfterBackslash + 1){
            return false;
        }
        return true;
    }

    public static boolean isEventValid(String input){
        if(input.length()< MINIMUM_EVENT_COMMAND_LENGTH){
            return false;
        }
        if (!input.substring(0, "event ".length()).equals("event ")){
            return false;
        }
        // validate substring before backslash
        int indexOfBackslash = input.indexOf("/");
        if(indexOfBackslash < MINIMUM_INDEX_OF_BACKSLASH_FOR_EVENT){
            return false;
        }
        // validate substring after backslash
        String stringAfterBackslash = input.substring(indexOfBackslash + 1);
        int indexFirstSpaceAfterBackslash = stringAfterBackslash.indexOf(" ");
        if(indexFirstSpaceAfterBackslash < 1){
            return false;
        }
        if(stringAfterBackslash.length() == indexFirstSpaceAfterBackslash + 1){
            return false;
        }
        return true;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}
