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
        if (taskNumber - numberOfCompletedTasks > 0) {
            return taskNumber - numberOfCompletedTasks;
        }
        else {return 0;}
    }

    public static ArrayList<Task> getTaskList(){
        return tasks;
    }

    public static void addNewTask(Task newTask) {
        tasks.add(newTask);
        taskNumber++;
    }

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

    public static void deleteTask(int task){
        newestTask = tasks.get(task);
//        if (tasks.get(task).isDone) {
//            numberOfCompletedTasks--;
//        }
        tasks.remove(task);
        System.out.println("The deleted task is : " + newestTask);
        taskNumber--;
    }

    public static void increaseNumberOfCompletedTasks(){
        numberOfCompletedTasks++;
    }

    public boolean getIsDone(){
        return isDone;
    }

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
