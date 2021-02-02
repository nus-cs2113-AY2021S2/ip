public class StringManipulator {

    public static String getFirstWord(String input){
        return input.split(" ")[0];
    }

    public static String getStringAfterSlash(String input){
        return input.substring(input.indexOf("/")+1+2);
    }

    public static String getStringAfterWhiteSpaceFor(String input){
        int firstWhiteSpaceIndex = input.indexOf(" ");
        return input.substring(firstWhiteSpaceIndex+1);
    }

    public static String getStringAfterWhiteSpaceAndBeforeSlash(String input){
        int firstWhiteSpaceIndex = input.indexOf(" ");
        int firstSlashIndex = input.indexOf("/");
        return input.substring(firstWhiteSpaceIndex+1, firstSlashIndex);
    }
}
