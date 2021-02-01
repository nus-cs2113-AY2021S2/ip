import java.util.Scanner;

public class Menu {
    private static String line = "\u2500".repeat(60);
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void printGreeting() {
        System.out.println(line);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(line);
    }

    public static void printBye() {
        System.out.println(line);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void printText(String text) {
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }
}
