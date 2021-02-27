package duke.ui;

public class TextUi {
    public static void showDividingLine() {
        System.out.println("_____________________________________________________");
    }

    public static void showLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
    }

    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.print("What can I do for you?\n");
    }

    public static void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon! :3");
    }

    public static void showUnrecognizedCommandError() {
        System.out.println("Uh oh this command is not available :<");
        System.out.println("Refer to the user guide for more help :>");
    }
}
