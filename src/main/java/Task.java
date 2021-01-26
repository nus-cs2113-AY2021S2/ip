public class Task {

    static Boolean[] isDone = new Boolean[100];
    static String[] taskList = new String[100];
    static int numberOfTasks = 0;
    static String [] words;

    public static void addTask(String description) {
        taskList[++numberOfTasks] = description;
        isDone[numberOfTasks] = false;
        System.out.println("____________________________________________________________");
        System.out.println("    added: " + description);
        System.out.println("____________________________________________________________");
    }

    public static void listTask() {
        for (int i = 1; i <= numberOfTasks; ++i) {
            System.out.print(i + ". [");
            if (isDone[i]) {
                System.out.print("X]");
            }
            else {
                System.out.print(" ]");
            }
            System.out.println(taskList[i]);
        }
    }

    public static void doneTask(String command) {
        words = command.split(" " );
        isDone[Integer.parseInt(words[1])] = true;
        System.out.println("____________________________________________________________");
        System.out.println("This task has been done. Good job!");
        System.out.println("[X] " + taskList[Integer.parseInt(words[1])]);
        System.out.println("____________________________________________________________");
    }
}

