public class Duke {

    public static String border = "____________________________________________________________";

    public static void main(String[] args) {
        greeting();
        exit();
    }

    public static void greeting(){
        String logo =  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(border + "\n" + "Hello! I'm \n" + logo );
        System.out.println("What can I do for you?");
    }

    public static void exit(){
        System.out.println(border + "\n" + "Bye. Hope to see you again soon!\n" + border);
    }
}
