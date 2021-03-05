package duke.exception;

/**
 * Represents the exceptions for Duke, allows extensions for other exceptions
 */

public class DukeException extends Throwable {
    private static String WARNING_DIVIDER = "\txxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n";
    /**
     * Creates DukeExceptions for methods to throw.
     * @param errorMessage Error message.
     */

    public DukeException(String errorMessage){
        System.out.print(WARNING_DIVIDER + "\tERROR: " + errorMessage + WARNING_DIVIDER);
    }
}