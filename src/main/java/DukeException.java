public class DukeException extends Exception{
    public DukeException () {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
    public DukeException (String errorMessage) {
        super(errorMessage);
    }

}
