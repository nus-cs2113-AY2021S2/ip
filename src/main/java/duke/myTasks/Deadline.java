package duke.myTasks;

import duke.myExceptions.NoContent;
import duke.myExceptions.NoTime;
public class Deadline extends Event{

    public Deadline(String content) throws NoContent, NoTime {
        super(content);
    }

    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + name + "(by: " + time + ")";
    }
}
