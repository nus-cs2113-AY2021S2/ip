public class UI {
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String divider = "-----------------------------------";

    public static void showWelcomeScreen(){
        System.out.println("Hello from\n" + logo);
        System.out.println(divider);
        System.out.println("Hello there! I'm Duke");
        System.out.println("I have the high ground! How may I help you?");
        System.out.println(divider);
    }

    public static void showDivider(){
        System.out.println(divider);
        System.out.println("What is thy bidding, my master?");
        System.out.println(divider);
    }
}
