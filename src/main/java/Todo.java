public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    /**
     * Format the todo task as [T][status] description .
     * @return the todo task
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
