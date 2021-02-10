package duke.Controller;

import duke.Controller.Task;

public class toDo extends Task {
    public toDo() {};
    public toDo(String description) {
        super(description);
    }

    public String printDescription() {
        return "[T]" + super.printDescription();
    }
}