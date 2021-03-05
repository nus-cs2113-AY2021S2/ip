public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount=0;

    /**
     * Get the total number of tasks.
     * @return the total number of tasks.
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Set the total number of tasks to a specific value.
     * @param taskCount the new number of task
     */
    public static void setTaskCount(int taskCount) {
        Task.taskCount = taskCount;
    }

    /**
     * Constructor method for a task object
     * @param description description of a task, including name, time, etc.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    /**
     * Get the completion status as an icon.
     * If the task is done, it will be marked as [✓].
     * If the task is still pending, it will be marked as [✘].
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    /**
     * Set the description to a specific description for the task
     * @param description the new description of the task, including name, time etc.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Mark a task as done, change the completion status icon
     */
    public void markAsDone(){
        isDone = true;
        System.out.println(this.getStatusIcon()+" "+this.description);
    }

    /**
     * Format the task as [status] description.
     * @return the task
     */
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() +"]" + this.description;
    }

    /**
     * Load the completion status of a task when reading from the data file.
     * @param s status icon
     */
    public void loadStatus(String s) {
        if (s.equals("\u2713"))
            isDone = true;
    }

    /**
     * Update the task count when the list of tasks is modified.
     * @return new task count after modification
     */
    public static int updateTaskCount() {
        taskCount--;
        return taskCount;
    }
}
