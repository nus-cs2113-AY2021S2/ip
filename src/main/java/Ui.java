public class Ui {
    public Ui() {
    }
    public static void printWelcomeMessage() {
        printBorder();
        System.out.println(" Hello from");
        System.out.println("  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n");
        System.out.println(" Hello! I'm Duke.");
        System.out.println(" What can I do for you?");
        printBorder();
    }
    public static void printBorder() {
        System.out.println("    ____________________________________________________________");
    }

    public static void printByeMessage() {
        printBorder();
        Storage.saveFile();
        System.out.println("     Bye. Hope to see you again soon!");
        printBorder();
    }
    public static void printFileCreatedMessage(String filepath){
        System.out.print("     I've created a text file at \" ");
        System.out.print(filepath);
        System.out.println(" \" to save your tasks!");
    }

    public static void printInvalidCommandMessage(){
        printBorder();
        System.out.println("     ERROR: there is no such command, try again!");
        printBorder();
    }
    public static void printTaskUnspecifiedMessage(){
        Ui.printBorder();
        System.out.println("     ☹ OOPS!!! Please specify which task number is done");
        Ui.printBorder();
    }
    public static void printInvalidTask(){
        Ui.printBorder();
        System.out.println("     ☹ OOPS!!! There is no such task number");
        Ui.printBorder();
    }
    public static void printTaskAlreadyMarkedAsDone(){
        Ui.printBorder();
        System.out.println("     ☹ OOPS!!! This task is already marked as done");
        Ui.printBorder();
    }

}
