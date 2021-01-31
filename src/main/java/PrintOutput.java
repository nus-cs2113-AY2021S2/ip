public class PrintOutput {

    public static final String BORDER = "============================================================";
    public static final String HELP_MESSAGE = " HELP COMMANDS\n"
        + "todo: adds to list\n"
        + " Example: todo sleep\n\n"
        + "deadline: adds to list\n"
        + " Example: deadline homework /by tomorrow\n\n"
        + "event: adds to list\n"
        + " Example: event block event /at tuesday\n\n"
        + "List: view list\n"
        + " Example: list\n\n"
        + "Help: view help commands\n"
        + " Example: Help\n\n"
        + "Done: check task as done\n"
        + " Example: Done 2\n\n"
        + "Bye:  terminate program\n"
        + " Example: bye\n\n"
        + BORDER;

    public static void printGreeting() {
        String alfred = "⊂_ヽ\n"
            + "　 ＼ ＼ what\n"
            + "　　  ＼ ( ͡° ͜ʖ ͡°) can I \n"
            + "　　　 >　   ⌒    ヽ\n"
            + "　　　/ 　   へ      ＼\n"
            + "　　/　　  /　  ＼   do for you?\n"
            + "　　(　  (ヽ　　    ヽ _   つ\n"
            + "   |  　| \\ \n"
            + "　  | 丿 ＼ ⌒)\n"
            + "　  | |　　) /\n"
            + "   ノ )　　Lﾉ \n"
            + "  (_／";
        printBorder();
        System.out.println("Welcome to Duke v1.4 ----------- Latest Update: 31/1/21");
        System.out.println("Developed by: Oscar Lai");
        printBorder();
        System.out.println("Good Day, I'm Alfred.\n" + alfred);
        System.out.println("Enter HELP for commands");
        printBorder();
    }

    public static void printExit() {
        String wave = " __      __\n"
            + "( _\\    /_ )\n"
            + " \\ _\\  /_ / \n"
            + "  \\ _\\/_ /_ _\n"
            + "  |_____/_/ /|\n"
            + "  (  (_)__)J-)\n"
            + "  (  /`.,   /\n"
            + "   \\/  ;   /\n"
            + "    | === |See you again!\n";
        printBorder();
        System.out.println("Pleasure serving you...\n" + wave);
        printBorder();
        System.out.println("Developed by: Oscar Lai\n"
            + "Version 1.4");
        printBorder();
    }

    public static void printBorder() {
        System.out.println(BORDER);
    }
}
