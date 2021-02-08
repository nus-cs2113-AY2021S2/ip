package ip.taskmaster;

public class DukeException extends Exception{
    protected String category;

    public DukeException(String category) {
        this.category = category;
    }
}
