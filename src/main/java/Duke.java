

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // Strings
        String lineDivider = "____________________________________________________________\n";
        String dukeGreeting = lineDivider + " Hello! I'm Duke\n What can I do for you?\n" ;
        String dukeFarewell =  lineDivider + " Bye. Hope to see you again soon!\n" + lineDivider;

        System.out.println(dukeGreeting + dukeFarewell);

    }


}
