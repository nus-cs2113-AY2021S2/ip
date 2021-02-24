package exceptions;

public class ErrorHandler {

    /**
     * Print error message indicating invalid user's command.
     */
    public static void printErrorMsgInvalidInput() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Print error message indicating invalid index.
     */
    public static void printErrorMsgIndexCannotBeEmpty() {
        System.out.println("OOPS!!! Please enter a valid index.");
    }
}
