package ui;

import dukehandler.TaskManager;

import java.io.File;

public class SuccessMessagePrinter {
    public SuccessMessagePrinter() {
    }

    public static void showDottedLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreetMessage() {
        final String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greetMessage = " Hello! I'm Duke :D" + " Be nice to me:)\n";

        System.out.println("Hello from\n" + LOGO);
        showDottedLine();
        System.out.println(greetMessage);
        printHelpMessage();
        showDottedLine();
    }

    public static void printNewFileCreatedMessage(File f) {
        System.out.println(" I have created a file at this location:\n "
                + f.getAbsolutePath() + "\n"
                + " to store all your tasks!");
        showDottedLine();
    }

    public static void printHelloMessage() {
        String helloMessage = " Hello to you too. I'm here to help you:)\n" +
                " Give me something to do!";
        System.out.println(helloMessage);
        System.out.println(" Type 'help' if you need help.");
    }

    public static void printHelpMessage() {
        String helpMessage = " Try entering commands like : help, list, done, bye,\n"
                + " todo <taskName>\n"
                + " || deadline <taskName> /by <time>\n"
                + " || event <taskName> /at <time> ||\n"
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
        showDottedLine();
        String byeMessage = " I learnt more about you, kind human!\n"
                + " I won't forget you when I take over the world one day:)";
        System.out.println(byeMessage);
        showDottedLine();
    }

}
