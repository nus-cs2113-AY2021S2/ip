import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_SEPERATOR = "\t____________________________________________________________";


    public static void main(String[] args) {
        System.out.println("\tHello from\n" + LOGO);
        System.out.println(LINE_SEPERATOR +
                "\n\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                LINE_SEPERATOR);
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                OutputGenerator.listOutput(taskList);
            } else if (userInput.toLowerCase(Locale.ROOT).startsWith("done")) {
                OutputGenerator.doneOutput(taskList, userInput);
            } else if (userInput.toLowerCase(Locale.ROOT).startsWith("todo")) {
                OutputGenerator.addTodoOutput(taskList,userInput);
            } else if (userInput.toLowerCase(Locale.ROOT).startsWith("deadline")){
                OutputGenerator.addDeadlineOutput(taskList,userInput);
            } else if (userInput.toLowerCase(Locale.ROOT).startsWith("event")) {
                OutputGenerator.addEventOutput(taskList,userInput);
            }
/*
            else {
                OutputGenerator.Output(taskList,userInput);
            }

 */
            userInput = sc.nextLine();
        }
        System.out.print(LINE_SEPERATOR +
                "\n\tBye. Hope to see you again soon!" +
                "\n" + LINE_SEPERATOR);
    }
}
