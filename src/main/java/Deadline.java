import MyExceptions.NoContent;
import MyExceptions.NoTime;

public class Deadline extends Event{

    Deadline(String content) throws NoContent, NoTime {
        super(content);
    }

    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + name + "(by: " + time + ")";
    }
}
