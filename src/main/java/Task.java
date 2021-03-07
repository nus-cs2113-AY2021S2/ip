import java.util.ArrayList;

/**
 * Does everything that a task is supposed to do
 */
public class Task {
    protected String taskDescription;
    protected boolean isDone;
    private static int taskNumber = 0;
    private static int numberOfCompletedTasks =0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int completedTaskCounter =0;
    private static Task newestTask;

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

    public static ArrayList<Task> getTaskList(){
        return tasks;
    }

    public static void addNewTask(Task newTask) {
        tasks.add(newTask);
        taskNumber++;
    }

    public static void completeTask(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        completedTaskCounter = taskNumber;
        numberOfCompletedTasks++;
    }

    public static Task getLatestTask(boolean adjustedTask) {
        if (adjustedTask) {
            return newestTask;
        } else {
            return tasks.get(getTaskNumber()-1);
        }
    }

    @Override
    public String toString(){
        return this.getStatusIcon() + " " + this.getTaskDescription();
    }

    public static void deleteTask(int task){
        newestTask = tasks.get(task);
        if (tasks.get(task).isDone) {
            numberOfCompletedTasks--;
        }
        tasks.remove(task);
        System.out.println("The deleted task is : " + newestTask);
        taskNumber--;
    }
}
