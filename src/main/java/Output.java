public class Output {

    //Coding standard: start every method with "print" for readability

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }
    public static void printGreet() {
        printLine();
        printLogo();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printExit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}