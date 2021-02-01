public class DukeCommandValidator {
    static int MINIMUM_DONE_LENGTH = 5;

    public static int getCommand(String input){
        if(input.equals("bye")){
            return DukeCommands.EXIT;
        }
        if(input.equals("list")){
            return DukeCommands.LIST;
        }
        if(input.length()>MINIMUM_DONE_LENGTH){
            if(validateDoneCommand(input) == true){
                return DukeCommands.DONE;
            }
        }
        return DukeCommands.ADD;
    }

    public static boolean validateDoneCommand(String input){
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
