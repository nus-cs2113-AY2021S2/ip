public class DukeException extends Exception {

    private static final String HEADER = "☹ OOPS!!! ";

    public DukeException(String errorMessage) {
        super(getHeader() + errorMessage);
    }

    public DukeException(String errorMessage, Throwable err) {
        super(getHeader() + errorMessage, err);
    }

    private static String getHeader() {
        return HEADER;
    }
}
