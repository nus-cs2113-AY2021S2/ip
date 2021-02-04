public class UI {
    public static void taskAdded(){
        System.out.println("The following task has been added:");
        System.out.println(Task.getLatestTask(false));
        System.out.println("You now have " + Task.getTaskNumber() + " tasks in your list!");
    }

    public static void taskCompleted(){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Task.getLatestTask(true));
        System.out.println("You still have " + Task.getNumberOfRemainingTasks() + " tasks to complete in your list!");
    }
}
