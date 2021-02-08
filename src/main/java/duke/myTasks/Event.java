package duke.myTasks;
import duke.myExceptions.NoContent;
import duke.myExceptions.NoTime;


public class Event extends Todo{
    protected String time;

    public Event(String content) throws NoContent, NoTime{
        if (content.equals("")) {
            throw new NoContent();
        }
        if (!content.contains("/")) {
            throw new NoTime();
        }
        String name = content.substring(0, content.indexOf('/') - 1);
        String time = content.substring(content.indexOf('/') + 4);
        this.name = name;
        this.time = time;
    }

    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + name + "(at: " + time + ")";
    }
}
