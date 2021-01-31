public class DukeCommandValidator {

    public String getCommand(String input){
        switch (input){
        case "bye": return "bye";
        case "list": return "list";
        default: return detailCheck(input);
        }
    }

    private String detailCheck(String input){
        validateDoneCommand(input);
        return input;
    }

    public String validateDoneCommand(String input){
        final int MINIMUM_DONE_LENGTH = 5;
        if(input.length()<MINIMUM_DONE_LENGTH){
            return
        }
        String firstFiveChars = input.substring(0, 5);
        String sixthToLastChars = input.substring(5);
        if(firstFiveChars.equals("done ") && isInteger(sixthToLastChars)){
            int taskIndex = Integer.parseInt(sixthToLastChars) - 1;
            if(taskIndex < currentTaskLength){
                return taskIndex;
            }
        }
        return INVALID_TASK_ID;
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
