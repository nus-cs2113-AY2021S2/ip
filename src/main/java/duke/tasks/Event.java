package duke.tasks;

/**
 * Represents a task of type {@code Event}.
 * Events have an additional field {@code at} which refers to the time a particular task is happening.
 */
public class Event extends Task {
    protected String at;

    public Event(String inputJob, String at){
        super(inputJob);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    /** Label: [E] */
    @Override
    public String addLabel(String s){
        String label = "[E]";
        label += s;
        return label;
    }

    /**
     * String representation of an {@code Event} object is suffixed with the date/time of event.
     */
    @Override
    public String addEnd(String s){
        String end = " (" + "at: " + this.at + ")";
        return s.concat(end);
    }

}
