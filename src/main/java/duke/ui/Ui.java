package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;


public class Ui {
    public String getCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static final String BORDER = "============================================================";
    public static final String HELP_MESSAGE = " HELP COMMANDS\n"
        + "todo: adds to list\n"
        + " Example: todo sleep\n\n"
        + "deadline: adds to list\n"
        + " Example: deadline homework /by tomorrow\n\n"
        + "event: adds to list\n"
        + " Example: event block event /at tuesday\n\n"
        + "List: view list\n"
        + " Example: list\n\n"
        + "Help: view help commands\n"
        + " Example: Help\n\n"
        + "Done: check task as done\n"
        + " Example: Done 2\n\n"
        + "Delete: deletes task from list\n"
        + " Example: delete 2\n\n"
        + "Duke.txt Format: \n"
        + "[✓] todo gym\n"
        + "[✓] deadline project meeting /by 21st feb\n"
        + "[ ] event football /at friday 2pm\n\n"
        + "Bye:  terminate program\n"
        + " Example: bye\n\n"
        + BORDER;

    public static void printGreeting() {
        String alfred = "⊂_ ヽ\n"
            + "　 ＼ ＼ ^^^^^^\n"
            + "　　 ＼ ( ͡° ͜ʖ ͡°) ...what can I do for you?\n"
            + "　　　 >　   ⌒ ヽ\n"
            + "　　　/ 　 へ   ＼\n"
            + "　　 / # /　 ＼ ＼...check out my six pack tho.\n"
            + "　　(　  (ヽ　　ヽ _ つ\n"
            + "   |  　| \\\n"
            + "　  | 丿 ＼ ⌒)...Enter HELP for command list!\n"
            + "　  | |　　) /\n"
            + "   ノ )　　Lﾉ\n"
            + "  (_／";
        printBorder();
        System.out.println("Welcome to Duke v1.0 ----------- Latest Update: 23/2/21");
        System.out.println("Developed by: Oscar Lai");
        printBorder();
        System.out.println("Good Day, I'm Alfred.\n" + alfred);
        printBorder();
    }

    public static void printExit() {
        String wave = " __      __\n"
            + "( _\\    /_ )\n"
            + " \\ _\\  /_ /\n"
            + "  \\ _\\/_ /_ _\n"
            + "  |_____/_/ /|\n"
            + "  (  (_)__)J-)\n"
            + "  (  /`.,   /\n"
            + "   \\/  ;   /\n"
            + "    | === |See you again!\n";
        System.out.println("Pleasure serving you...\n" + wave);
        System.out.println("Duke.txt file Updated!");
        printBorder();
        System.out.println("Developed by: Oscar Lai\n"
            + "Version 1.0");
        printBorder();
    }

    public static void printBorder() {
        System.out.println(BORDER);
    }

    /**
     * Print the number of task in list
     * @param list - ArrayList
     */
    public static void printNoOfTask(ArrayList<Task> list) {
        System.out.print("You have " + list.size() + " task");
        if (list.size() > 1) {
            System.out.print("s");
        }
        System.out.print(" in total!\n");
        Ui.printBorder();
    }

    /**
     * Prints Added Task
     * @param index - Index of task in List
     * @param list - ArrayList
     */
    public static void printAddedTask(int index, ArrayList<Task> list) {
        Task t = list.get(index);
        System.out.println("I have added [" + t.getType() + "]["
            + t.getStatusIcon() + "] \""
            + t.getName() + t.getDate() + "\"" + " to the List!");
        Ui.printNoOfTask(list);
    }

    /**
     * Prints task found that matches substring searchItem
     * Returns NONEXISTENT_TASK_ERROR message if no such item found
     * @param searchItem - item that is finding
     * @param list - ArrayList
     */
    public static void printFindTask(String searchItem, ArrayList<Task> list) {
        System.out.println("I have found the follow items: ");
        int foundCounter = 0;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().contains(searchItem)) {
                Task t = list.get(i);
                System.out.println(foundCounter+1 + ". " + "[" + t.getType() + "]["
                    + t.getStatusIcon() + "] \""
                    + t.getName() + t.getDate() + "\"" );
                foundCounter += 1;
            }
        }
        if(foundCounter == 0 )  {
            checkError("NONEXISTENT_TASK_ERROR");
        }
        printBorder();
    }

    /**
     * Check Error Method
     * Returns error code message
     * @param ERROR_MESSAGE - Error Message
     */
    public static void checkError(String ERROR_MESSAGE) {
        switch (ERROR_MESSAGE) {
            case "WRONG_DONE_FORMAT":
                System.out.println("Error! You must enter an integer after"
                    + " \"done\"!");
                printBorder();
                break;
            case "INDEX_EXCEEDS_LIST":
                System.out.println("Error! You do not have that "
                    + "many items in your list!");
                printBorder();
                break;
            case "EMPTY_LIST":
                System.out.println("Your list is empty! Add something!");
                printBorder();
                break;
            case "DONE_CHECKED_ERROR":
                System.out.println("You have already marked it as Done!");
                printBorder();
                break;
            case "INVALID_FORMAT":
                System.out.println("¯\\_(ツ)_/¯ That is an invalid format!");
                System.out.println("Enter HELP for commands!");
                printBorder();
                break;
            case "LIST_FULL":
                System.out.println("List is full!");
                printBorder();
                break;
            case "WRONG_DELETE_FORMAT":
                System.out.println("Error! You must enter a valid integer after"
                    + " \"delete\"!");
                printBorder();
                break;
            case "DELETE_EMPTY_LIST":
                System.out.println("Error! You cannot delete what does not exist!");
                printBorder();
                break;
            case "INVALID_COMMAND":
                System.out.println("¯\\_(ツ)_/¯ That is an invalid command!");
                System.out.println("Enter \"HELP\" for commands!");
                printBorder();
                break;
            case "NONEXISTENT_TASK_ERROR":
                System.out.println("Error! No such task found!");
                break;
            case "FILE_NOT_FOUND":
                System.out.println("Duke.txt not found in directory. "
                    + "Please try again!");
                printBorder();
        }
    }

}
