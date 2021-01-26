import java.util.Scanner;

public class Duke {

    public static String echoCommand(){
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        System.out.println(command);
        return command;
    }

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */

        System.out.println("Hello! I'm 343 Guilty Spark! You may call me Spark!");
        System.out.println("What can I do for you?");
        while (!echoCommand().equalsIgnoreCase("Bye")){
            echoCommand();
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
