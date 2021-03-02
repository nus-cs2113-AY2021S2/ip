package duke;

public class Ui {
    public static void printStartMessage() {
        String STARTMESSAGE = "____________________________________________________________\n"
                + "Hello! I'm Duke.Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(STARTMESSAGE);
    }

    public static void printLogo() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + LOGO);

    }

    public static void printExitMessage() {
        String ENDMESSAGE = "############################################################\n"
                + "Bye. Hope to see you again soon!\n"
                + "############################################################\n";
        System.out.println(ENDMESSAGE);
    }

    public static void printDivider() {
        System.out.println("************************************************************");
    }

}
