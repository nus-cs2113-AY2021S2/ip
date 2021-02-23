package exceptions;

public class ErrorHandler {
    public static void printErrorMsgInvalidInput() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printErrorMsgIndexCannotBeEmpty() {
        System.out.println("OOPS!!! Please enter a valid index.");
    }
}
