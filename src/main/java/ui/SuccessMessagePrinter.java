package ui;

import dukehandler.TaskList;

import java.io.File;

public class SuccessMessagePrinter {
    private final TaskList taskList;
    static final String DOTTED_LINE = "____________________________________________________________";
    public SuccessMessagePrinter(TaskList taskList) {
        this.taskList = taskList;
    }

    public void showDottedLine() {
        System.out.println("____________________________________________________________");
    }

    public void printGreetMessage() {
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
        showDottedLine();
    }

    public void printNewFileCreatedMessage(File f) {
        System.out.println(" I have created a file at this location:\n "
                + f.getAbsolutePath() + "\n"
                + " to store all your tasks!");
        showDottedLine();
    }

    public void printHelloMessage() {
        String helloMessage = " Hello to you too. I'm here to help you:)\n" +
                " Give me something to do!";
        System.out.println(helloMessage);
        System.out.println(" Type 'help' if you need help.");
    }

    public void printHelpMessage() {
        String helpMessage = " Try entering commands like : help, list, done, bye,\n"
                + " todo <taskName>\n"
                + " || deadline <taskName> /by <time>\n"
                + " || event <taskName> /at <time> ||\n"
                + " Remember: be nice!";
        System.out.println(helpMessage);
    }

    public void printAddedTask() {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + (taskList.tasks.get(taskList.tasks.size() - 1)).toString());
        System.out.println(" Now you have " + taskList.tasks.size() + " task"
                + (taskList.tasks.size() == 1 ? " " : "s ") + "in the list.");
    }

    public void printTaskMarkedDone(int index) {
        System.out.println(" Nice! I've marked this task as done:\n "
                + taskList.tasks.get(index).toString());
    }

    public void printRemovedTask(int removeIndexInt) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + taskList.tasks.get(removeIndexInt - 1).toString());
        System.out.println(" Now you have " + (taskList.tasks.size() - 1) + " task"
                + (taskList.tasks.size() - 1 == 1 ? " " : "s ") + "in the list.");
    }

    public void printByeMessage() {
        String byeMessage = " I learnt more about you, kind human!\n"
                + " I won't forget you when I take over the world one day:)";
        System.out.println(byeMessage);
        showDottedLine();
    }

}
