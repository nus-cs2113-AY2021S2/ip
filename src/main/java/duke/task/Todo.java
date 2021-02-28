package duke.task;

/*
Class Todo for creating todo tasks
*/
public class Todo extends Task {

    /*
    Constructor for Todo Object
    Initialize description, taskType variables
    */
    public Todo(String description) {
        super(description, 'T');
    }

    /*
    Overrides toString() method from superclass
    Returns a string of task details
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
