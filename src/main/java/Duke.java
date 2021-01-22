import java.util.Scanner;
public class Duke {
    static String[] list = new String[100];
    static int index = 0;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        String byeBye = "____________________________________________________________\n"+
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(greet);
        String command;
        while (true) {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            if (command.equals("bye")) break;

            System.out.println("____________________________________________________________");

            if (command.equals("list")) {
                for (int i=0; i<index; ++i) {
                    System.out.println((i+1)+". "+list[i]);
                }
            }
            else { //if command is anything other than 'list' and 'bye'
                list[index++] = command;
                System.out.println("added: " + command);
            }
            
            System.out.println("____________________________________________________________");
        }
        System.out.println(byeBye);
    }
}
