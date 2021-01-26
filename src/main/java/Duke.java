import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while(!userInput.equals("bye")){
            System.out.println("\t____________________________________________________________\n\t" +
                    userInput +
                    "\n\t____________________________________________________________");
            userInput = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n " +
                "\tBye. Hope to see you again soon!" +
                "\n\t____________________________________________________________");
    }
}
