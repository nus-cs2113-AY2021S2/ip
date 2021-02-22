package ui;

import dukehandler.TaskManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.File;

/**
 * Prints all messages that are non-errors.
 * Includes Greeting and Farewell Messages.
 */
public class SuccessMessagePrinter {
    static final String DOTTED_LINE
            = "____________________________________________________________";

    public SuccessMessagePrinter() {
    }

    public static void printCurrentTimeAndDate() {
        String time = " Local Time: ";
        LocalDateTime dateToday = LocalDateTime.now();
        time += dateToday.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
        System.out.println("\n  " + "-".repeat(time.length()) + "\n "
                + time + "\n  " + "-".repeat(time.length()));

    }

    public static void printGreetMessage() {
        final String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greetMessage = " Hello! I'm Duke :D" + " Be nice to me:)\n";

        System.out.println("Hello from\n" + LOGO);
        System.out.println(DOTTED_LINE);

        System.out.println(greetMessage);
        printHelpMessage();
        System.out.println(DOTTED_LINE);
    }

    public static void printNewFileCreatedMessage(File f) {
        System.out.println(" I have created a file at this location:\n "
                + f.getAbsolutePath() + "\n"
                + " to store all your tasks!");
        System.out.println(DOTTED_LINE);
    }

    public static void printHelloMessage() {
        String helloMessage = " Hello to you too. I'm here to help you:)\n" +
                " Give me something to do!";
        System.out.println(helloMessage);
        System.out.println(" Type 'help' if you need help.");
    }

    public static void printHelpMessage() {
        String helpMessage =
                " Try entering commands like : help, list, bye,\n"
                        + " done <task number>,\n delete <task number>\n"
                        + " add new todo <taskName>\n"
                        + " add deadline <taskName> /by <date YYYY-MM-DD> <time hh:mm>\n"
                        + " add event    <taskName> /at <date YYYY-MM-DD> <time hh:mm>\n"
                        + " find <common keyword in tasks>\n"
                        + " print type <task type> (to filter based on type)\n"
                        + " print date <task date YYYY-MM-DD> (to filter based on date)\n"
                        + " Remember: be nice!";

        System.out.println(helpMessage);
    }

    public static void printAddedTask() {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + (TaskManager.tasks.get(TaskManager.tasks.size() - 1)).toString());
        System.out.println(" Now you have " + TaskManager.tasks.size() + " task"
                + (TaskManager.tasks.size() == 1 ? " " : "s ") + "in the list.");
    }

    public static void printTaskMarkedDone(int index) {
        System.out.println(" Nice! I've marked this task as done:\n "
                + TaskManager.tasks.get(index).toString());
    }

    public static void printRemovedTask(int removeIndexInt) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + TaskManager.tasks.get(removeIndexInt - 1).toString());
        System.out.println(" Now you have " + (TaskManager.tasks.size() - 1) + " task"
                + (TaskManager.tasks.size() - 1 == 1 ? " " : "s ") + "in the list.");
    }

    public static void printByeMessage() {
        System.out.println(DOTTED_LINE);
        String byeMessage = " I learnt more about you, kind human!\n"
                + " I won't forget you when I take over the world one day:)";
        System.out.println(byeMessage);
        System.out.println(DOTTED_LINE);
    }

}
