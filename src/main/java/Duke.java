import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static int taskCount = 0;
    private static String[] list = new String[100];
    private static Task[] tasksObjects = new Task[100];

    public static void printIntroMessage(){
        System.out.println("________________________________");
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
        System.out.println("________________________________");
    }

    public static void printExitMessage(){
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
        System.exit(0);
    }

    public static void addTaskToList(Task task){
        list[taskCount] = taskCount+1 +". [" + task.getStatusIcon() + "]" + task.getDescription();
        tasksObjects[taskCount] = task;
        taskCount += 1;
    }

    public static String[] removeNullFromStringList(String[] tasks){
        return Arrays.copyOf(tasks, taskCount);
    }

    public static void printList(String[] list){
        for (String item: list){
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printIntroMessage();

        while (true){
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            if (input.equals("list")){
                System.out.println("________________________________");
                printList(removeNullFromStringList(list));
                System.out.println("________________________________");
            }
            else if (input.equals("bye")) {
                printExitMessage();
                break;
            }
            else if (input.startsWith("done")) {
                System.out.println("Nice! I've marked this task as done:");
                int taskNumberDone = Integer.parseInt(input.substring(5));
                System.out.println("[" + tasksObjects[taskNumberDone-1].getStatusIcon() + "]"
                        + tasksObjects[taskNumberDone-1].getDescription());
                tasksObjects[taskNumberDone-1].markAsDone();
                list[taskNumberDone-1] = taskNumberDone +". [" + tasksObjects[taskNumberDone-1].getStatusIcon() + "]"
                        + tasksObjects[taskNumberDone-1].getDescription();

            }
            else{
                Task t = new Task(input);
                addTaskToList(t);
                System.out.println("________________________________");
                System.out.println("added: " + t.getDescription());
                System.out.println("________________________________");
            }
        }
    }
}

