package duke.ui;

//import duke classes
import duke.task.Task;
import duke.task.TaskList;

//import java utils
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Interacts with users.
 */
public class Ui {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Greets the user when user first run the application.
     */
    public static void helloMessage() {
        String logo =
                "\n"
                        + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n"
                        + "░░░████████░░░░░░░░░░░█░░█░░░░░░░░░░░░\n"
                        + "░░░█░░░░░░░██░░░░░░░░░█░░█░░░░░░░░░░░░\n"
                        + "░░░█░░░░░░░░█░░█░░░█░░█░█░░██████░░░░░\n"
                        + "░░░█░░░░░░░░█░░█░░░█░░██░░░█░░░░░░░░░░\n"
                        + "░░░█░░░░░░░░█░░█░░░█░░█░█░░█████░░░░░░\n"
                        + "░░░█░░░░░░░██░░██░██░░█░█░░█░░░░░░░░░░\n"
                        + "░░░█░░░░░██░░░░░███░░░█░░█░█████░░░░░░\n"
                        + "░░░███████░░░░░░░░░░░░█░░█░░░░░░░░░░░░\n"
                        + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n";


        String greetings = "\n(¯`·._.·(¯`·._.· HI I'M DUKE ·._.·´¯)·._.·´¯)\n"
                            + "How can I help you?\n"
                            + "(¯`·._.·(¯`·._.· (^ v ^) ·._.·´¯)·._.·´¯)\n";
        System.out.println(logo + greetings);
        showLine();
    }

    /**
     * Print all tasks in the list.
     *
     * @param tasks arraylist of tasks.
     */
    public static void printTasks(ArrayList <Task> tasks){
        for (int i = 0; i <tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i).toString());
        }
    }

    /**
     * Prints all task in the list after a command is done.
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list: ");
        printTasks(TaskList.getTasks());

    }

    /**
     * Sets response to be Duke's goodbye.
     */
    public static void byeMessage() {
        System.out.println("Bye:( Hope to see you again soon!");
        System.exit(0);
    }


    /**
     * Get user's input
     *
     * @return user's input
     */
    public static String readCommand(){
        String input = scanner.nextLine();
        return input;
    }


    /**
     * Prints a line as separator
     */
    public static void showLine(){
        String line = "____________________________________________________________";
        System.out.println(line);
    }



}