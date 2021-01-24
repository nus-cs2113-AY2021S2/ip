import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static int taskCount = 0;
    private static String[] list = new String[100];

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

    public static void addTaskToList(String task){
        list[taskCount] = taskCount+1 +". " + task;
        taskCount += 1;
    }

    public static String[] removeNullFromList(String[] tasks){
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
            if (input.equals("bye")){
                printExitMessage();
            }
            switch (input) {
            case "list":
                System.out.println("________________________________");
                printList(removeNullFromList(list));
                System.out.println("________________________________");
                break;
            default:
                addTaskToList(input);
                System.out.println("________________________________");
                System.out.println("added: " + input);
                System.out.println("________________________________");
                break;
            }
        }
    }
}
