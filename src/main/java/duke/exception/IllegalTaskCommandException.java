package duke.exception;

public class IllegalTaskCommandException extends Exception{

    public IllegalTaskCommandException(String message) {
        super(message);
    }

    public static void printErrorLogo() {
        System.out.println("*****\n* ! *\n*****");
    }
}
