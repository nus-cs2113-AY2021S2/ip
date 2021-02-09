package duke;

public class IllegalTaskCommandException extends Exception{

    public IllegalTaskCommandException(String message) {
        super(message);
    }

    public static void printErrorLogo() {
        System.err.println("*****\n* ! *\n*****");
    }
}
