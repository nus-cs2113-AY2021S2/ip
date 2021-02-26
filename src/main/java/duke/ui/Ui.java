package duke.ui;


import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Interacts with users.
 */
public class Ui {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * greets the user when user first run the application.
     */
    public static void helloMessage() {
        showLine();
        System.out.println("Hello! I'm Julia");
        System.out.println("What can I do for you?");
        System.out.println();
        showLine();
    }

    /**
     * @param tasks arraylist of tasks.
     * prints the tasks in the list.
     */
    public static void printTasks(ArrayList <Task> tasks){
        for (int i = 0; i <tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i).toString());
        }
    }

    /**
     * prints the message and tasks list.
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list: ");
        printTasks(TaskList.getTasks());

    }

    /**
     * when user enters 'bye', the program prints this message and exits.
     */
    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println();
        showLine();
        System.exit(0);
    }


    /**
     * @return user's input
     * reads user's input
     */
    public static String readCommand(){
        String input = scanner.nextLine();
        return input;
    }


    /**
     * prints a line as separator
     */
    public static void showLine(){
        System.out.println("*********************************************");
    }



}
