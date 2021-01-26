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
        String[] tasks = new String[100];
        int taskCount = 0;
        String userInput = sc.nextLine();
        while(!userInput.equals("bye")){
            if(!userInput.equals("list")){
                tasks[taskCount] = userInput;
                System.out.println("\t____________________________________________________________\n" +
                        "\tadded: " + userInput +
                        "\n\t____________________________________________________________");
                taskCount++;
            }
            else{
                System.out.println("\t____________________________________________________________");
                for(int i=0; i<taskCount; i++)
                    System.out.println("\t" + (i+1) + "." + tasks[i]);
                System.out.println("\t____________________________________________________________");
            }
            userInput = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n " +
                "\tBye. Hope to see you again soon!" +
                "\n\t____________________________________________________________");
    }
}
