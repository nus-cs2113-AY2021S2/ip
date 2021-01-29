public class Ui {
    public static void printMenu()
    {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! Im Duke\n" + logo + "What can I do for you?");
        System.out.println("1 - Input Task");
        System.out.println("2 - Print List of Tasks");
        System.out.println("3 - Mark Task as Done");
        System.out.println("Type \"bye\" to exit the System");
        System.out.println("\nChoose an option: ");
    }
}
