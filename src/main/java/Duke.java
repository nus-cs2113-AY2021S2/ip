import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static int taskCount = 0;
    private static Task[] tasksObjectsArray = new Task[100];

    public static void printIntroMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

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

    public static void addTaskToTaskList(Task task){
        tasksObjectsArray[taskCount] = task;
        taskCount += 1;
        System.out.println("________________________________");
        System.out.println("added: " + task.getDescription());
        System.out.println("________________________________");
    }

    public static void markTaskAsDone(int taskNumber){
        System.out.println("Nice! I've marked this task as done:");
        tasksObjectsArray[taskNumber-1].markAsDone();
        System.out.println("[" + tasksObjectsArray[taskNumber-1].getStatusIcon() + "]"
                + tasksObjectsArray[taskNumber-1].getDescription());

    }

    public static Task[] removeNullFromList(Task[] tasks){
        return Arrays.copyOf(tasks, taskCount);
    }

    public static void printTaskList(Task[] tasks){
        int taskNumber = 1;
        System.out.println("________________________________");
        for (Task task: tasks){
            System.out.println(taskNumber + "[" + task.getStatusIcon() + "]" + task.getDescription());
            taskNumber +=1 ;
        }
        System.out.println("________________________________");
    }

    public static void main(String[] args) {
        printIntroMessage();
        while (true){
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("list")){
                printTaskList(removeNullFromList(tasksObjectsArray));
            }
            else if (input.equals("bye")) {
                printExitMessage();
            }
            else if (input.startsWith("done")) {
                int taskNumberDone = Integer.parseInt(input.substring(5));
                markTaskAsDone(taskNumberDone);
            }
            else{
                Task t = new Task(input);
                addTaskToTaskList(t);
            }
        }
    }
}

