import MyExceptions.NoContent;
import MyExceptions.NoTime;

public class Event extends Todo{
    protected String time;

    Event(String content) throws NoContent, NoTime{
        if (content.equals("")) {
            throw new NoContent();
        }
        if (content.indexOf("/") == -1) {
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
