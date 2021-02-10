import java.util.Scanner;

public class Duke {
    /**
     * Command line output constants
     * */
    final static String DIVLINE = "\t__________________________________________________________________\n";
    final static String GREETINGS = "\tHello! I'm Ayanga, your personal task manager.\n" +
            "\tWhat can I note down for you?\n" +
            "\tTo add a todo item, simply write \"todo <DESCRIPTION>\".\n" +
            "\tTo add a deadline, write \"deadline <DESCRIPTION> /by <TIME>\".\n" +
            "\tTo add an event, write \"event <DESCRIPTION> /at <VENUE>\".\n" +
            "\tSay \"list\" and I will display your tasks.\n" +
            "\tWrite \"done <NUMBER OF TASK>\" to let me know when you have completed a task.\n" +
            "\tWave \"bye\" to me if you don't need me for now.\n";
    final static String PARTINGS = "\tBye. Hope you have done your work next time I see you!\n" +
            "\tAh, and also remember to take care of yourself and sleep early :)\n";
    private static boolean isExiting = false;       // control the loop in main
    private static Task[] tasks = new Task[100];    // list of task items
    private static int taskCount = 0;               // keep track of total task numbers

    /**
     * Deals with raw input, extracts keyword and calls respective methods.
     * Catches the exceptions of each method and print corresponding response.
     * Throws an exception if keyword is invalid.
     * @param   prompt  is the raw input string
     * */
    public static void processPrompt(String prompt) throws IllegalKeywordException {
        String keyword = prompt.contains(" ") ? prompt.split(" ")[0] : prompt;
        switch (keyword) {
        case "bye":
            isExiting = true;
            break;
        case "list":
            try {
                displayList();
            } catch (EmptyListException e) {
                System.out.println(DIVLINE + "\t:( OOPS!!! You haven't noted down anything yet.");
                System.out.print(DIVLINE);
            }
            break;
        case "done":
            try {
                int taskIndex = Integer.parseInt(prompt.substring(5)) - 1;
                completeTask(taskIndex);
            } catch (NumberFormatException e) {
                System.out.println(DIVLINE + "\t:( OOPS!!! You are not specifying a valid task number.");
                System.out.print(DIVLINE);
            } catch (TaskAlreadyDoneException e) {
                System.out.println(DIVLINE + "\tThe task is already done. :)");
                System.out.print(DIVLINE);
            }
            break;
        case "deadline":
            try {
                addDeadlineToList(prompt.substring(9));
            } catch (IllegalDeadlinePrompt illegalDeadlinePrompt) {
                System.out.println(DIVLINE + "\t:( OOPS!!! You are not specifying a valid deadline with time.");
                System.out.print(DIVLINE);
            }
            break;
        case "event":
            try {
                addEventToList(prompt.substring(6));
            } catch (IllegalEventPrompt illegalEventPrompt) {
                System.out.println(DIVLINE + "\t:( OOPS!!! You are not specifying a valid event with venue.");
                System.out.print(DIVLINE);
            }
            break;
        case "todo":
            try {
                addTodoToList(prompt.substring(4));
            } catch (IllegalTodoPrompt illegalTodoPrompt) {
                System.out.println(DIVLINE + "\t:( OOPS!!! The description of a todo cannot be empty.");
                System.out.print(DIVLINE);
            }
            break;
        default:
            throw new IllegalKeywordException();
        }
         return;
    }

    /**
     * */
    private static void displayList() throws EmptyListException {
        if (taskCount == 0) {
            throw new EmptyListException();
        }
        System.out.print(DIVLINE);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t" + (i + 1) + "." + tasks[i].toString());
        }
        System.out.print(DIVLINE);
    }

    private static void addDeadlineToList(String description) throws IllegalDeadlinePrompt {
        int splitPoint = description.indexOf("/by");
        if (splitPoint==-1){
            throw new IllegalDeadlinePrompt();
        }
        tasks[taskCount] = new Deadline(description.substring(0, splitPoint - 1),
                                        description.substring(splitPoint + 4));
        printAddSuccessMessage(tasks[taskCount]);
        taskCount++;
    }

    private static void addEventToList(String description) throws IllegalEventPrompt {
        int splitPoint = description.indexOf("/at");
        if (splitPoint==-1){
            throw new IllegalEventPrompt();
        }
        tasks[taskCount] = new Event(description.substring(0, splitPoint - 1),
                                     description.substring(splitPoint + 4));
        printAddSuccessMessage(tasks[taskCount]);
        taskCount++;
    }

    private static void addTodoToList(String description) throws IllegalTodoPrompt {
        if (description.startsWith(" ")) {
            tasks[taskCount] = new Todo(description.substring(1));
            printAddSuccessMessage(tasks[taskCount]);
            taskCount++;
        } else {
            throw new IllegalTodoPrompt();
        }
    }

    private static void printAddSuccessMessage(Task task) {
        System.out.print(DIVLINE);
        System.out.println("\tGot it. I've added this task:\n" +
                "\t  " + task.toString());
        System.out.println("\tNow you have " + (taskCount+1) + " tasks in the list.");
        System.out.print(DIVLINE);
    }

    private static void completeTask(int taskIndex) throws TaskAlreadyDoneException {
        if (tasks[taskIndex].getIsDone()){
            throw new TaskAlreadyDoneException();
        }
        tasks[taskIndex].markAsDone();
        System.out.print(DIVLINE);
        System.out.println("\tNice! I've marked this task as done: \n" +
                "\t" + tasks[taskIndex].toString());
        System.out.print(DIVLINE);
    }

    public static void main(String[] args) {
        System.out.print(DIVLINE + GREETINGS + DIVLINE);
        Scanner in = new Scanner(System.in);
        while (!isExiting) {
            try{
                String prompt = in.nextLine();
                processPrompt(prompt);
            } catch (IllegalKeywordException e) {
                System.out.println(DIVLINE + "\t:( OOPS!!! I'm sorry, but I don't know what that means.");
                System.out.print(DIVLINE);
            }
        }
        System.out.println(DIVLINE + PARTINGS + DIVLINE);
    }


}
