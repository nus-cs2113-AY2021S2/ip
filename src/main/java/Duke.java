import java.util.Scanner;

public class Duke {
    /**
     * Command line output constants
     * */
    final static String DIVLINE = "\t__________________________________________________________________\n";
    final static String GREETINGS = "\tHello! I'm Ayanga, your personal task manager.\n" +
            "\tWhat can I note down for you?\n" +
            "\tWave \"bye\" to me if you don't need me for now;\n" +
            "\tSay \"list\" and I will display your tasks.\n" +
            "\tSay \"done\" and a number to let me know you completed which task.\n";
    final static String PARTINGS = "\tBye. Hope you have done your work next time I see you!\n" +
            "\tAh, and also remember to take care of yourself and sleep early :)\n";
    private static boolean isExiting = false;
    private static Task[] tasks = new Task[100];   // list of task items
    private static int taskCount = 0;

    /**
     * Deals with raw input, extracts keyword and calls respective methods.
     * @param   prompt  is the raw input string
     * */
    public static void processPrompt(String prompt) throws IllegalKeywordException {
        String keyword = prompt.contains(" ") ? prompt.split(" ")[0] : prompt;
        switch (keyword) {
            case "bye":
                break;
            case "list":
                displayList(tasks, taskCount);
                break;
            case "done":
                try {
                    int taskIndex = Integer.parseInt(prompt.substring(5)) - 1;
                    completeTask(tasks, taskIndex);
                } catch (NumberFormatException e) {
                    System.out.println(DIVLINE + " :( OOPS!!! You are not specifying a valid task number." + DIVLINE);
                }
                break;
            case "deadline":
                addToList(tasks, prompt.substring(9), "deadline");
                break;
            case "event":
                addToList(tasks, prompt.substring(6), "event");
                break;
            case "todo":
                addToList(tasks, prompt.substring(5), "todo");
                break;
            default:
                throw new IllegalKeywordException();
        }
         return;
    }

    private static void displayList(Task[] tasks, int taskIndex) {
        System.out.println(DIVLINE);
        if (taskIndex == 0) {
            System.out.println("\tYou haven't noted down anything yet.");
            return;
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskIndex; i++) {
            System.out.println("\t" + (i + 1) + "." + tasks[i].toString());
        }
        System.out.println(DIVLINE);
    }

    private static void addToList(Task[] tasks, String prompt, String taskType) {
        int cutOffPoint = 0;
        switch (taskType) {
        case "todo":
            tasks[taskCount] = new Todo(prompt);
            break;
        case "deadline":
            cutOffPoint = prompt.indexOf("/by");
            tasks[taskCount] = new Deadline(prompt.substring(0, cutOffPoint - 1), prompt.substring(cutOffPoint + 4));
            break;
        case "event":
            cutOffPoint = prompt.indexOf("/at");
            tasks[taskCount] = new Event(prompt.substring(0, cutOffPoint - 1), prompt.substring(cutOffPoint + 4));
            break;
        }
        System.out.println(DIVLINE + "\tGot it. I've added this task:\n" +
                "\t  " + tasks[taskCount].toString());
        System.out.println("\tNow you have " + (taskCount + 1) + " tasks in the list."+ DIVLINE);
    }

    private static void completeTask(Task[] tasks, int taskIndex) {
        tasks[taskIndex].markAsDone();
        System.out.println("\tNice! I've marked this task as done: \n" +
                "\t" + tasks[taskIndex].toString());
    }

    public static void main(String[] args) {
        System.out.print(DIVLINE + GREETINGS + DIVLINE);
        Scanner in = new Scanner(System.in);
        int taskIndex = 0;
        while (!isExiting) {
            try{
                String prompt = in.nextLine();
                processPrompt(prompt);
            } catch (IllegalKeywordException e) {
                System.out.println(DIVLINE + "OOPS!!! I'm sorry, but I don't know what that means :-(" + DIVLINE);
            }
        }
        System.out.println(DIVLINE + PARTINGS + DIVLINE);
    }


}
