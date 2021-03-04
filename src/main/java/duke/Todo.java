package duke;

public class Todo extends Task{
    /**
     * Constructor for the Todo class
     *
     * @param description  the description of the event
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string form which is todo task
     *
     * @return string description, todo
     */
    @Override
    public String getType() {
        return "todo";
    }

    /**
     * Returns the todo task in string form
     *
     * @return String form of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}