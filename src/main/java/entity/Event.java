package entity;

public class Event extends Task {
    private String at;

    /**
     * Create an event based on its task description and when it is at.
     *
     * @param taskDescription
     * @param at
     */
    public Event(String taskDescription, String at) {
        super(taskDescription);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", at);
    }
}
