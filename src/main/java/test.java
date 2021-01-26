public class test {
    private static final String helpMessage = "HELP COMMANDS\n"
        + "any input - adds to timetable\n"
        + "list - view timetable\n"
        + "HELP - view help commands\n"
        + "done [index] - tick as done\n"
        + "bye  - terminate program\n"
        + border;

    public static void printGreeting(){
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
        System.out.println("Good Day, I'm Alfred.\n" + alfred);
        System.out.println("Enter HELP for commands");
        printBorder();
    }

    public static void printExit(){
        String wave = " __      __\n"
            + "( _\\    /_ )\n"
            + " \\ _\\  /_ / \n"
            + "  \\ _\\/_ /_ _\n"
            + "  |_____/_/ /|\n"
            + "  (  (_)__)J-)\n"
            + "  (  /`.,   /\n"
            + "   \\/  ;   /\n"
            + "    | === |See you again!";
        printBorder();
        System.out.println("Pleasure serving you...\n" + wave);
        printBorder();
    }
}
