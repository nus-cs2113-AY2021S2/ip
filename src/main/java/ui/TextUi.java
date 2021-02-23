package ui;

import common.Constants;

/**
 * Contain methods to be shown to user when using the program
 */
public class TextUi {

    public static final Constants constants = new Constants();

    public static void showLogo() {
        // logo/loading
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void showGreeting() {
        // greeting
        String greeting = "Hello! I'm Duke\n" + "What can I do for you? Enter help to view commands.";
        System.out.println(greeting);
    }

    public static void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showAddTask() {
        System.out.println("Got it. I've added this task:");
    }

    public static void showHelp() {
        final String helpMessage = constants.INDENTATION + "todo <description> - Add new todo task\n"
                    + constants.INDENTATION + "deadline <description> /by <date> - Add new deadline task \n"
                    + constants.INDENTATION + "event <description> /at <location> - Add new event task\n"
                    + constants.INDENTATION + "list - List all tasks\n"
                    + constants.INDENTATION + "done <index> - Mark task number _ as done\n"
                    + constants.INDENTATION + "delete <index> - Delete task number _ \n"
                    + constants.INDENTATION + "find <keyword> - Search for task with keyword in description\n"
                    + constants.INDENTATION + "exit - Save tasks list and exit program";
        System.out.println(helpMessage);
    }
}
