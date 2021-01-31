import java.util.Scanner;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        printGreeting(logo);

        Scanner in = new Scanner(System.in);

        while (true){
            String line = in.nextLine();

            if (line.equals("list")){
                printList();
                continue;
            }
            else if (line.contains("done")){
                markDone(line);
                continue;
            }
            else if (line.equals("bye")) {
                printBye();
                break;
            }
            else if (line.contains("todo")){
                addTodo(line);
                continue;
            }
            else if (line.contains("deadline")){
                addDeadline(line);
                continue;
            }
            else if (line.contains("event")){
                addEvent(line);
                continue;
            }
        }
    }

    private static void addEvent(String line) {
        System.out.println("Got it. I've added this task: ");
        String[] words = line.split(" ", 2);
        String detailWords = words[1];
        String[] info = detailWords.split(" /at ", 2);
        String taskDescription = info[0];
        String atTime = info[1];
        tasks[taskCount] = new Event(taskDescription,atTime);
        System.out.println(tasks[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    private static void addDeadline(String line) {
        System.out.println("Got it. I've added this task: ");
        String[] words = line.split(" ", 2);
        String detailWords = words[1];
        String[] info = detailWords.split(" /by ", 2);
        String taskDescription = info[0];
        String byDate = info[1];
        tasks[taskCount] = new Deadline(taskDescription,byDate);
        System.out.println(tasks[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    private static void addTodo(String line) {
        System.out.println("Got it. I've added this task: ");
        String[] words = line.split(" ", 2);
        String taskDescription = words[1];
        tasks[taskCount] = new Todo(taskDescription);
        System.out.println(tasks[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void printBye() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    private static void markDone(String line) {
        System.out.println("Nice! I've marked this task as done: ");
        String[] words = line.split(" ", 2);
        int doneIndex = Integer.parseInt(words[1]);
        Task doneTask = tasks[doneIndex-1];
        doneTask.markAsDone();
        System.out.println("[" + doneTask.getStatusIcon() + "] "+doneTask.description);
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list: ");
        int index = 0;
        while (index < taskCount){
            System.out.println((index+1)+"." +tasks[index].toString());
            index++;
        }
    }

    private static void printGreeting(String logo) {
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }
}
