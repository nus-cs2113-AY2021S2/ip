import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Level 0: Greet
        lineBreak();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        lineBreak();
        //TODO: Refactor and Extract Method

        //Level 3: Mark as Done
        Scanner in = new Scanner(System.in);
        String userInput;
        Task[] taskList = new Task[100]; //TODO: Remove magic literals, refactor and extract constant
        int listCount = 0;
        //TODO: Edit to coincide with SLAP abstraction
        while (true){
            userInput = in.nextLine();
            if (userInput.equals("list")){
                lineBreak();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listCount; i++){
                    Task currentTask = taskList[i];
                    System.out.println(i+1 + "." + currentTask.toString());
                }
                lineBreak();
                continue;
            }

            if (userInput.startsWith("done")) {
                String[] words = userInput.split(" ");
                Task currentTask = taskList[Integer.parseInt(words[1]) - 1];
                currentTask.markAsDone();
                lineBreak();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + currentTask.toString());
                lineBreak();
                continue;
            }

            if (userInput.equals("bye")) {
                break;
            }

            lineBreak();
            System.out.println("Got it. I've added this task:");

            if (userInput.startsWith("todo")) {
                taskList[listCount++] = new Todo(userInput.substring(5));
            } else if (userInput.startsWith("deadline")) {
                int dividerPosition = userInput.indexOf("/by");
                String by = userInput.substring(dividerPosition + 4);
                taskList[listCount++] = new Deadline(userInput.substring(9, dividerPosition - 1), by);
            } else if (userInput.startsWith("event")) {
                int dividerPosition = userInput.indexOf("/at");
                String by = userInput.substring(dividerPosition + 4);
                taskList[listCount++] = new Event(userInput.substring(6, dividerPosition - 1), by);
            }

            System.out.println("  " + taskList[listCount - 1].toString());
            System.out.println("Now you have " + listCount + " tasks in the list.");
            lineBreak();
        }

        lineBreak();
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
    }

    public static void lineBreak() {
        final String HORIZONTAL_LINE = "____________________________________________________________";
        System.out.println(HORIZONTAL_LINE);
    }
}
