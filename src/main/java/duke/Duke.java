package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final int MAX_TASK = 100;

    private static final int BYE_COMMAND = 0;
    private static final int LIST_COMMAND = 1;
    private static final int HELP_COMMAND = 2;
    private static final int DONE_COMMAND = 3;
    private static final int TODO_COMMAND = 4;
    private static final int DEADLINE_COMMAND = 5;
    private static final int EVENTS_COMMAND = 6;
    private static final int UNKNOWN_COMMAND = 7;
    private static final int DELETE_COMMAND = 8;

    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        printHello();
        runUserCommand();
        printBye();
    }

    private static void runUserCommand() {
        //Task[] taskList = new Task[MAX_TASK];
        Scanner in = new Scanner(System.in);


        //Loop to receive response.
        while (true) {

            String input = in.nextLine();
            int command = parseCommand(input);


            // If list is full, will only allow LIST and BYE command to pass
            try {
                checkListCapacity(command);
            } catch (FullListException e) {
                printListFullWarning();
                continue;
            }

            switch (command) {
            case BYE_COMMAND:
                return;

            case LIST_COMMAND:
                runList();
                break;

            case HELP_COMMAND:
                printHelp();
                break;

            case DONE_COMMAND:
                runDone(input);
                break;

            case TODO_COMMAND:
                runTodo(input);
                break;

            case DEADLINE_COMMAND:
                runDeadline(input);
                break;

            case EVENTS_COMMAND:
                runEvent(input);
                break;

            case DELETE_COMMAND:
                runDeleteCommand(input);
                break;

            default:
                runUnknownCommand(input);
            }
        }

    }


    /**
     * READ AND PARSE USER INPUT
     */

    private static int parseCommand(String input) {
        // BYE command
        if (input.equalsIgnoreCase("bye")) {
            return BYE_COMMAND;
        }

        // LIST command
        if (input.equalsIgnoreCase("list")) {
            return LIST_COMMAND;
        }

        //HELP COMMAND
        else if (input.equalsIgnoreCase("help")) {
            return HELP_COMMAND;
        }

        // DONE command
        else if (startsWith(input, "done")) {
            return DONE_COMMAND;
        }

        // TO-DO COMMAND
        else if (startsWith(input, "todo")) {
            return TODO_COMMAND;
        }

        // DEADLINE COMMAND
        else if (startsWith(input, "deadline")) {
            return DEADLINE_COMMAND;
        }

        // EVENTS COMMAND
        else if (startsWith(input, "event")) {
            return EVENTS_COMMAND;
        }

        //DELETE COMMAND
        else if (startsWith(input, "delete")) {
            return DELETE_COMMAND;
        }

        // UNKNOWN command
        else {
            return UNKNOWN_COMMAND;
        }
    }

    private static String parseJob(String input, String delimiter) throws InvalidCommandException {

        String[] words = input.split(" ");

        if (words.length < 2) {
            throw new InvalidCommandException();
        }

        return getJobString(words, delimiter);
    }

    private static String parseDate(String input, String delimiter) throws InvalidCommandException {

        String[] words = input.split(delimiter);

        if (words.length == 1) {
            throw new InvalidCommandException();
        }

        return words[1].trim();
    }

    private static String getJobString(String[] words, String delimiter) {

        String job = words[1];

        for (int i = 2; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(delimiter)) {
                break;
            }
            job += " " + words[i];
        }
        return job;
    }

    private static boolean startsWith(String input, String command) {
        return input.toUpperCase().startsWith(command.toUpperCase());
    }


    private static boolean isAllowedWhenListFull(int command) {
        return (command == LIST_COMMAND || command == BYE_COMMAND);
    }


    private static void checkListCapacity(int command) throws FullListException {
        if (taskList.size() == MAX_TASK && !Task.isFull) {
            Task.isFull = true;
        }

        if (Task.isFull && !isAllowedWhenListFull(command)) {
            throw new FullListException();
        }
    }


    /**
     * COMMAND RUNNER METHODS
     */

    private static void runDone(String input) {
        String[] word = input.split(" ");
        int jobNumber = 0;

        try {
            jobNumber = Integer.parseInt(word[1]) - 1;

            // error handling - no jobs
            if (taskList.size() == 0) {
                printNoTaskWarning();
                return;
            }

            markJobAsDone(taskList.get(jobNumber));

        } catch (NumberFormatException e) {
            printInvalidInputWarning(input);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            printInvalidIndexWarning(jobNumber);
        }

    }

    private static void markJobAsDone(Task task) {
        task.setDone(true);
        System.out.print("Congrats! You've completed: \n   ");
        task.printTask();
        System.out.println();
    }

    private static void runList() {
        int numbering = 1;

        // error handling - no jobs
        if (taskList.size() == 0) {
            printNoTaskWarning();
            return;
        }

        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(numbering + ". ");
            taskList.get(i).printTask();
            numbering++;
        }
        System.out.println();

    }

    private static void runTodo(String input) {

        String job;

        try {
            job = parseJob(input, "");
        } catch (InvalidCommandException e) {
            printInvalidInputWarning(input);
            return;
        }

        Todo newTask = new Todo(job);

        taskList.add(newTask);
        Task.taskCount++;

        printTaskAdded(newTask);
    }

    private static void runDeadline(String input) {
        String job;
        String by;

        try {
            job = parseJob(input, "/by");
            by = parseDate(input, "/by");
        } catch (InvalidCommandException e) {
            printInvalidInputWarning(input);
            return;
        }

        Deadline newTask = new Deadline(job, by);
        taskList.add(newTask);
        Task.taskCount++;

        printTaskAdded(newTask);

    }

    private static void runEvent(String input) {
        String job, at;

        try {
            job = parseJob(input, "/at");
            at = parseDate(input, "/at");
        } catch (InvalidCommandException e) {
            printInvalidInputWarning(input);
            return;
        }

        Event newTask = new Event(job, at);
        taskList.add(newTask);
        Task.taskCount++;

        printTaskAdded(newTask);

    }

    private static void runUnknownCommand(String input) {
        System.out.println("No idea what " + input + " means!");
        System.out.println("Enter \"help\" for a list of available commands and format\n");
    }

    private static void runDeleteCommand(String input) {

        String[] words = input.split(" ");
        int index = 0;

        try {
            index = Integer.parseInt(words[1]) - 1;
            
            printTaskDeleted(index);
            taskList.remove(index);
        } catch (NumberFormatException e) {
            printInvalidInputWarning(input);
        } catch (IndexOutOfBoundsException e) {
            printInvalidIndexWarning(index);
        }
    }



    /**
     * PRINTING METHODS
     */

    private static void printTaskAdded(Task task) {
        System.out.println("Added to list: ");
        task.printTask();
        printNumTasksLeft();
        System.out.println();
    }

    private static void printTaskDeleted(int index) {
        System.out.println("Task " + (index + 1) + " has been deleted:");
        System.out.print("  ");
        taskList.get(index).printTask();
        System.out.println("Tasks remaining: " + (taskList.size()) + "\n");
    }

    private static void printNumTasksLeft() {
        String output = Integer.toString(taskList.size());
        output += (taskList.size() == 1) ? " task" : " tasks";
        output += " in the list";

        System.out.println(output);
    }

    private static void printInvalidInputWarning(String input) {
        System.out.println("Wrong format: " + input + "! Enter \"help\" for a list of available commands and format\n");
    }

    private static void printNoTaskWarning() {
        System.out.println("You don't have any tasks ! Enter a task");
        System.out.println("Enter \"help\" for a list of available commands and format\n");
    }

    private static void printInvalidIndexWarning(int jobNumber) {
        String smaller = "Enter a valid job number. Use the list command to view your current tasks.";
        String larger = "You don't have that many jobs! Use the list command to view your current tasks.";

        System.out.println(jobNumber <= 0 ? smaller : larger);
        System.out.println("Enter \"help\" for a list of available commands and format\n");
    }

    private static void printListFullWarning() {
        System.out.println("List is full!");
        System.out.println("Use the \"list\" command to view your tasks.");
        System.out.println("Enter \"bye\" to exit... \n");
    }

    private static void printHelp() {
        String commandList = "LIST - \n" +
                "FORMAT: list";

        String commandDone = "DONE - \n" +
                "FORMAT: done [(int) number]";

        String commandTodo = "TODO - \n" +
                "FORMAT: todo [(str) job]";

        String commandDeadline = "DEADLINE - \n" +
                "FORMAT: deadline [(str) job] /by [(str) deadline]";


        System.out.println("COMMAND LIST:");
        System.out.println("-------------");
        System.out.println(commandTodo + '\n');
        System.out.println(commandDeadline + '\n');
        System.out.println(commandList + '\n');
        System.out.println(commandDone + '\n');
        System.out.println("To exit, enter \"bye\"\n");

    }

    private static void printHello() {
        // Start - Greets user
        String line = "____________________________________________________________\n";
        String hello_message = "Hello I'm Diuk! \nWhat would you like to do today?\n";

        System.out.print(line);
        System.out.print(hello_message);
        System.out.print(line);
    }

    private static void printBye() {
        String line = "____________________________________________________________\n";
        String bye_message = "Bye! Hit me up if you feel like being productive again ;)\n";

        System.out.print(line);
        System.out.print(bye_message);
        System.out.print(line);
    }
}
