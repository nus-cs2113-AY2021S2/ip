import java.util.Scanner;

public class MainPage {
    public static final String LIST_COMMAND = "list";
    public static final String BYE_COMMAND = "bye";
    public static final String DONE_COMMAND = "done";
    public static void displayUI(Scanner in){
        printWelcomeMessage();
        while (true){
            String input = in.nextLine();
            if (input.equals(LIST_COMMAND)) {
                TaskManager.printTaskList();
            }
            else if (input.equals(BYE_COMMAND)) {
                printExitMessage();
            }
            else if (input.startsWith(DONE_COMMAND)) {
                int taskNumberDone = StringManipulator.getTaskNumberDone(input);
                TaskManager.markTaskAsDone(taskNumberDone);
            }
            else{
                TaskManager.addTask(input);
            }
        }
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
        printDivider();
    }

    public static void printExitMessage(){
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
        System.exit(0);
    }

    public static void printDivider(){
        System.out.println("________________________________");
    }

}
