package duke.task;

public class StringManipulator {
    
    public StringManipulator(){
        // TODO: 02-Feb-21
    }

    public static String getFirstWord(String input){
        return input.split(" ")[0];
    }

    public static String getStringAfterSlash(String input){
        return input.substring(input.indexOf("/")+1+2);
    }

    public static String getIndexOfStringAfterWhiteSpace(String input){
        int firstWhiteSpaceIndex = input.indexOf(" ");
        if (firstWhiteSpaceIndex == -1){
            return input.substring(input.length());
        }
        return input.substring(firstWhiteSpaceIndex+1);
    }

    public static String getStringAfterWhiteSpaceAndBeforeSlash(String input){
        int firstWhiteSpaceIndex = input.indexOf(" ");
        int firstSlashIndex = input.indexOf("/");
        return input.substring(firstWhiteSpaceIndex+1, firstSlashIndex);
    }

    public static int getTaskNumberDone(String input){
        return Integer.parseInt(input.substring(5));
    }
}
