package duke.task;

public class StringParser {

    public static final int OUT_OF_BOUND_INDEX = -1;
    public static final int DONE_TASK_NUMBER_INDEX = 5;
    public static final int DELETED_TASK_NUMBER_INDEX = 7;

    public static String getFirstWord(String input){
        return input.split(" ")[0];
    }

    public static String getStringAfterSlash(String input){
        return input.substring(input.indexOf("/")+1+2+1);
    }

    public static String getIndexOfStringAfterWhiteSpace(String input){
        int firstWhiteSpaceIndex = input.indexOf(" ");
        if (firstWhiteSpaceIndex == OUT_OF_BOUND_INDEX){
            return input.substring(input.length());
        }
        return input.substring(firstWhiteSpaceIndex+1);
    }

    public static String getStringAfterWhiteSpaceAndBeforeSlash(String input){
        int firstWhiteSpaceIndex = input.indexOf(" ");
        int firstSlashIndex = input.indexOf("/");
        if (firstWhiteSpaceIndex == OUT_OF_BOUND_INDEX) {
            return input.substring(input.length());
        }
        if (firstSlashIndex == OUT_OF_BOUND_INDEX) {
            return input.substring(firstWhiteSpaceIndex+1, input.length());
        }
        return input.substring(firstWhiteSpaceIndex+1, firstSlashIndex);
    }

    public static int getTaskNumberDone(String input){
        return Integer.parseInt(input.substring(DONE_TASK_NUMBER_INDEX));
    }

    public static int getTaskNumberDeleted(String input){
        return Integer.parseInt(input.substring(DELETED_TASK_NUMBER_INDEX));
    }
}
