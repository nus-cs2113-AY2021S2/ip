public class Task {
    protected String taskDescription;
    protected boolean isDone;
    private static int taskNumber = 0;
    private static int numberOfCompletedTasks =0;
    private static Task[] tasks = new Task[100];
    private static int taskNumberCompleted =0;

    public Task(String task) {
        this.taskDescription = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getTaskDescription(){
        return taskDescription;
    }

    public static int getTaskNumber(){
        return taskNumber;
    }

    public static int getNumberOfRemainingTasks(){
        return taskNumber - numberOfCompletedTasks;
    }

    public static Task[] getTaskList(){
        return tasks;
    }

    public static void addNewTask(Task newTask) {
        tasks[taskNumber] = newTask;
        taskNumber++;
    }

    public static void completeTask(int taskNumber) {
        tasks[taskNumber].markAsDone();
        taskNumberCompleted = taskNumber;
        numberOfCompletedTasks++;
    }

    public static Task getLatestTask(boolean getCompletedTask) {
        if (getCompletedTask) {
            return tasks[taskNumberCompleted];
        } else {
            return tasks[getTaskNumber() - 1];
        }
    }

    @Override
    public String toString(){
        return this.getStatusIcon() + " " + this.getTaskDescription();
    }

}
