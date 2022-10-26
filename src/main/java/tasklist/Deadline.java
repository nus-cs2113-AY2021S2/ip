package tasklist;

import tasklist.Task;

public class Deadline extends Task {

    public String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    public String getStatusIcon() {
        return "[D]" + "[" + super.getStatusIcon() + "]";
    }


    @Override
    public String getDescription() {
        return super.getDescription() + " " + "<< " + by + " >>";
    }


}
