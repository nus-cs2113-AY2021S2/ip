public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String division_line = "____________________________________________________________\n";
        String say_hi = "Hello! I'm Duke\n" +
                        "What can I do for you?\n";
        String say_bye = "Bye. Hope to see you again soon!\n";
        System.out.println(division_line + say_hi + division_line);
        System.out.println(say_bye + division_line);
    }
}
