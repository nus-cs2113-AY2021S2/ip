public class Ui {
    public static void printMenu() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! Im Duke\n" + logo + "What can I do for you?");
        System.out.println("> Use the keyword todo");
        System.out.println("> Use the keyword deadlines followed \"/by\"");
        System.out.println("> Use the keyword event followed by \"/at\"");
        System.out.println("> Use the keyword list to print");
        System.out.println("> Use keyword \"Done <Number>\"  to mark task as done!");
        System.out.println("> Use the keyword \"bye\" to exit");
        System.out.println("Input here: ");
    }
}
