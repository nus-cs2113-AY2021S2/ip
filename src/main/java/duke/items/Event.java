package duke.items;

import static duke.main.UI.convertDateFormat;

public class Event extends Task {
    private String at;


    public Event(String description, String atInput) {
        super(description);
        this.at = atInput;

    }

    public void setAt(String atInput) {
        this.at = atInput;
    }
    public String getAt() {
        return this.at;
    }
    @Override
    public String getType() {
        return "E";
    }

    @Override
    public void print(){
        if (this.isDone) {
            System.out.println("[E][\u2713] " + description + " (at: " + at + ")" );
        } else {
            System.out.println("[E][\u2718] " + description + " (at: " + at + ")" );
        }
    }
}