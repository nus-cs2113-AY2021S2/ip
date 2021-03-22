package Duke.Parser;
import Duke.UI.Ui;
import java.util.Scanner;

public class Parser {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final char INPUT_COMMENT_MARKER = '#';
    /***
     *  Show error message if error appear
     ***/
    public static boolean checkError (String commandArgs){
        if (commandArgs.isEmpty()){
            Ui.showError();
            return false;
        }else{
            return true;
        }
    }

    /***
     *  Trims the input of when there is a empty space
     ***/
    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" }; // else case: no parameters
    }

    /***
     *  Receive users input
     ***/
    public static String getUserInput() {
        System.out.print("Enter command: ");
        String inputLine = SCANNER.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty() || inputLine.trim().charAt(0) == INPUT_COMMENT_MARKER) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine;
    }
}
