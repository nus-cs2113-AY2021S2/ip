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
/**
 * Returns a tick or X symbol to signify whether the task is done
 */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }
    /**
     * when called, marks the task as done
     */
    public void markAsDone(){
        this.isDone = true;
    }
    /**
     * Returns the description portion of the task
     */
    public String getTaskDescription(){
        return taskDescription;
    }
    /**
     * Returns the task number
     */
    public static int getTaskNumber(){
        return taskNumber;
    }
    /**
     * Returns the number of remaining tasks
     */
    public static int getNumberOfRemainingTasks(){
        if (taskNumber - numberOfCompletedTasks > 0) {
            return taskNumber - numberOfCompletedTasks;
        }
        else {return 0;}
    }
    /**
     * Returns the tasks
     */
    public static ArrayList<Task> getTaskList(){
        return tasks;
    }
    /**
     * adds a task to the task list, and increments task number
     */
    public static void addNewTask(Task newTask) {
        tasks.add(newTask);
        taskNumber++;
    }
    /**
     * Checks whether the task is alr marked as done, before marking it as done, and incrementing the number of completed tasks
     */
    public static boolean completeTask(int taskNumber) {
        if (tasks.get(taskNumber).isDone == true){
            System.out.println("This task has already been marked as done!");
            return false;
        }
        else {
            tasks.get(taskNumber).markAsDone();
            newestTask = tasks.get(taskNumber);
            completedTaskCounter = taskNumber;
            numberOfCompletedTasks++;
            return true;
        }
    }
    /**
     * Returns the most recent task
     */
    public static Task getLatestTask(boolean adjustedTask) {
        if (adjustedTask) {
            //return newestTask;
            return tasks.get(getTaskNumber()-1);
        } else {
            return newestTask;
        }
    }

    public static Task getAddedTask(){
        return tasks.get(getTaskNumber()-1);
    }

    @Override
    public String toString(){
        return this.getStatusIcon() + " " + this.getTaskDescription();
    }
    /**
     * deletes a task, decrements the number of tasks
     */
    public static void deleteTask(int task){
        newestTask = tasks.get(task);
//        if (tasks.get(task).isDone) {
//            numberOfCompletedTasks--;
//        }
        tasks.remove(task);
        System.out.println("The deleted task is : " + newestTask);
        taskNumber--;
    }
    /**
     * increments number of completed tasks
     */
    public static void increaseNumberOfCompletedTasks(){
        numberOfCompletedTasks++;
    }
    /**
     * Returns a tick or X symbol to signify whether the task is done
     */
    public boolean getIsDone(){
        return isDone;
    }
    /**
     * Prints out the tasks that match the query
     */
    public static void find(String query) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskDescription().contains(query)) {
                matches.add(task);
            }
        }
        for (int i = 0; i < matches.size(); i++) {
            int displayedNumber = i + 1;
            System.out.println(displayedNumber + ". " + matches.get(i));
        }
    }
}
