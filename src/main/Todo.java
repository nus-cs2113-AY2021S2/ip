/**
 * Subclass of Task object
 *
 * @author Jeremy Teo
 * @version 0.1
 * @since 2021-02-28
 */
package main;

public class Todo extends Task{

    /**
     * Todo object constructor.
     *
     * @param description string description of task
     */
    public Todo(String description) {
        super(description);
    }


    /**
     * Get user status of the Todo Task
     *
     * @return formatted string Todo Task completion status
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "[T][Y]" : "[T][N]");
    }

}

