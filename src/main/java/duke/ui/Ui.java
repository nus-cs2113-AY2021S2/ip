package duke.ui;

import java.io.IOException;

public class Ui {

    private static final String LOGO =
            "▒▒▒▒▒▒▒█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n"
                    + "▒▒▒▒▒▒▒█░▒▒▒▒▒▒▒▓▒▒▓▒▒▒▒▒▒▒░█\n"
                    + "▒▒▒▒▒▒▒█░▒▒▓▒▒▒▒▒▒▒▒▒▄▄▒▓▒▒░█░▄▄\n"
                    + "▒▒▄▀▀▄▄█░▒▒▒▒▒▒▓▒▒▒▒█░░▀▄▄▄▄▄▀░░█\n"
                    + "▒▒█░░░░█░▒▒▒▒▒▒▒▒▒▒▒█░░░░░░░░░░░█\n"
                    + "▒▒▒▀▀▄▄█░▒▒▒▒▓▒▒▒▓▒█░░░█▒░░░░█▒░░█\n"
                    + "▒▒▒▒▒▒▒█░▒▓▒▒▒▒▓▒▒▒█░░░░░░░▀░░░░░█\n"
                    + "▒▒▒▒▒▄▄█░▒▒▒▓▒▒▒▒▒▒▒█░░█▄▄█▄▄█░░█\n"
                    + "▒▒▒▒█░░░█▄▄▄▄▄▄▄▄▄▄█░█▄▄▄▄▄▄▄▄▄█\n"
                    + "▒▒▒▒█▄▄█░░█▄▄█░░░░░░█▄▄█░░█▄▄█\n"
                    + " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    public static void printPresentDirectory() {
        System.out.println("Present project directory is: " + System.getProperty("user.dir"));
    }

    public static void printSuccessfulCreateFolderMessage() {
        System.out.println("Folder created successfully.");
    }

    public static void printFolderExistsMessage() {
        System.out.println("Folder already exists.");
    }

    public static void printErrorMessage(IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

    public static void printFileExistsMessage() {
        System.out.println("Text file already exists.");
    }

    public static void printSearchWordNotFoundMessage() {
        System.out.println("No matches found! Please try again.");
    }

    public void printGreeting() {
        System.out.print(LOGO);
        System.out.print("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public void printExitMessage() {
        System.out.print("Bye. Hope to see you again soon!\n");
    }


}
