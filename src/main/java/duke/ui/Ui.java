package duke.ui;

public class Ui {
    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void printStartingMessage() {
        printHorizontalLine();
        System.out.println(" Hello from");
        System.out.println("  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n");
        printHorizontalLine();
        System.out.println(" Hello! I'm Duke.");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

    public void printByeMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public void printFileCreatedMessage(String filePath) {
        System.out.println(" I've created a text file at " + filePath + " to save your tasks!");
    }
}
