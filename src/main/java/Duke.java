import java.util.Scanner;

public class Duke {

    public static int taskCount = 1;
    public static Task[] tasks = new Task[101];

    public static void goodbye() {
        System.out.println("\n" +
                "░██████╗░░█████╗░░█████╗░██████╗░██████╗░██╗░░░██╗███████╗\n" +
                "██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝██╔════╝\n" +
                "██║░░██╗░██║░░██║██║░░██║██║░░██║██████╦╝░╚████╔╝░█████╗░░\n" +
                "██║░░╚██╗██║░░██║██║░░██║██║░░██║██╔══██╗░░╚██╔╝░░██╔══╝░░\n" +
                "╚██████╔╝╚█████╔╝╚█████╔╝██████╔╝██████╦╝░░░██║░░░███████╗\n" +
                "░╚═════╝░░╚════╝░░╚════╝░╚═════╝░╚═════╝░░░░╚═╝░░░╚══════╝\n" +
                "Hope to see you again soon!");
    }

    public static void welcome() {
        System.out.println("\n" +
                "██╗░░██╗███████╗██╗░░░░░██╗░░░░░░█████╗░\n" +
                "██║░░██║██╔════╝██║░░░░░██║░░░░░██╔══██╗\n" +
                "███████║█████╗░░██║░░░░░██║░░░░░██║░░██║\n" +
                "██╔══██║██╔══╝░░██║░░░░░██║░░░░░██║░░██║\n" +
                "██║░░██║███████╗███████╗███████╗╚█████╔╝\n" +
                "╚═╝░░╚═╝╚══════╝╚══════╝╚══════╝░╚════╝░\n" +
                "from\n" + "\n" +
                "██████╗░██╗░░░██╗██╗░░██╗███████╗\n" +
                "██╔══██╗██║░░░██║██║░██╔╝██╔════╝\n" +
                "██║░░██║██║░░░██║█████═╝░█████╗░░\n" +
                "██║░░██║██║░░░██║██╔═██╗░██╔══╝░░\n" +
                "██████╔╝╚██████╔╝██║░╚██╗███████╗\n" +
                "╚═════╝░░╚═════╝░╚═╝░░╚═╝╚══════╝\n");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }
    public static void showList() {
        if (taskCount > 1) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < taskCount; i++) {
                System.out.println(i + ". " + tasks[i].getTaskType() + " " + tasks[i].getStatusIcon()
                        + tasks[i].getDescription() + tasks[i].getDateTime());
            }
        } else {
            System.out.println("Please input a task.");
        }
    }

    public static void done(String taskDone) {
        int taskIndex = Integer.parseInt(taskDone);
        if (taskIndex >= taskCount || taskIndex < 0){
            System.out.println("You have not added task " + taskIndex + "yet! Please try again.");
        } else {
            tasks[taskIndex].updateDoneStatus();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[taskIndex].getTaskType() + tasks[taskIndex].getStatusIcon()
                    + tasks[taskIndex].getDescription());
        }
    }

    public static void todo(String taskToAdd) {
        Todo t = new Todo(taskToAdd);
        tasks[taskCount] = t;
        System.out.println("Got it. I've added this task:");
        System.out.println(t.getTaskType() + t.getStatusIcon() + " " + t.getDescription());
        System.out.println("Now you have" + taskCount + " tasks in the list.");
        taskCount++;
    }

    public static void event(String taskToAdd) {
        int splitIndex = taskToAdd.indexOf('/');
        String description = taskToAdd.substring(0, splitIndex);
        String dateTime = taskToAdd.substring(splitIndex + 3);
        Event e = new Event(description, dateTime);
        tasks[taskCount] = e;
        System.out.println("Got it. I've added this task:");
        System.out.println(e.getTaskType() + e.getStatusIcon() + e.getDescription() + " " + e.getDateTime());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        taskCount++;
    }

    public static void deadline(String taskToAdd) {
        int splitIndex = taskToAdd.indexOf('/');
        String description = taskToAdd.substring(0, splitIndex);
        String dateTime = taskToAdd.substring(splitIndex + 3);
        Deadline d = new Deadline(description, dateTime);
        tasks[taskCount] = d;
        System.out.println("Got it. I've added this task:");
        System.out.println(d.getTaskType() + d.getStatusIcon() + d.getDescription() + " " + d.getDateTime());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        taskCount++;
    }

    public static void getInput() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String inputCommand = sc.nextLine();
            if (inputCommand.equals("list")) {
                showList();
            } else if (inputCommand.equals("bye")){
                goodbye();
                break;
            } else {
                String taskType = inputCommand.split(" ", 2)[0];
                String taskToAdd = inputCommand.split(" ", 2)[1];
                switch (taskType) {
                case "done":
                    done(taskToAdd);
                    break;
                case "todo":
                    todo(taskToAdd);
                    break;
                case "deadline":
                    deadline(taskToAdd);
                    break;
                case "event":
                    event(taskToAdd);
                    break;
                default:
                    System.out.println(inputCommand);
                }
            }
        }
    }

    public static void main(String[] args) {
        welcome();
        getInput();
    }
}