package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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

    private static final String DELIM = "-";
    private static final String PATH = ".\\src\\main\\java\\duke\\data\\duke.txt";

    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        printHello();
        checkSavedData();
        runUserCommand();
        printBye();
    }

    /**
     * ----------------------------------------------- START OF METHODS -----------------------------------------------
     */

    private static void runUserCommand() {
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
                saveData();
                break;

            case TODO_COMMAND:
                runTodo(input);
                saveData();
                break;

            case DEADLINE_COMMAND:
                runDeadline(input);
                saveData();
                break;

            case EVENTS_COMMAND:
                runEvent(input);
                saveData();
                break;

            case DELETE_COMMAND:
                runDeleteCommand(input);
                saveData();
                break;

            default:
                runUnknownCommand(input);
            }
        }

    }


    /**
     * FILE IO
     */

    private static void checkSavedData() {
        try {
            readFromFile();
            System.out.println("Existing records found! Data loaded..." + '\n');
        } catch (FileNotFoundException e) {
            System.out.println("No previous records! Starting a new record..." + '\n');
            createNewFile();
        }
    }

    private static void createNewFile() {
        File file = new File(PATH);
        try {
            if (file.createNewFile()) {
                System.out.println("New file created!" + '\n');
            }
        } catch (IOException e) {
            System.out.println("Error creating new file...");
            e.printStackTrace();
        }
    }

    private static void saveData() {
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong when saving...");
        }
    }

    private static void readFromFile() throws FileNotFoundException {

        File dataFile = new File(PATH);
        Scanner reader = new Scanner(dataFile);

        while (reader.hasNext()) {
            int lineNumber = 0;
            String line = reader.nextLine();
            lineNumber++;

            try {
                Task task = formTask(line);
                taskList.add(task);
                Task.taskCount++;
            } catch (DataErrorException e) {
                printDataErrorWarning(lineNumber);
            }
        }
    }

    private static void writeToFile() throws IOException {
        FileWriter writer = new FileWriter(PATH);

        for (Task t : taskList) {
            writer.write(formLine(t) + '\n');
        }
        writer.close();
    }

    private static String formLine(Task t) {
        String line = "";

        if (t instanceof Todo) {
            line += "T" + DELIM;
            line += (t.isDone() ? "1" : "0") + DELIM;
            line += t.job;
        } else if (t instanceof Deadline) {
            line += "D" + DELIM;
            line += (t.isDone() ? "1" : "0") + DELIM;
            line += t.job + DELIM;
            line += ((Deadline) t).getBy();
        } else if (t instanceof Event) {
            line += "E" + DELIM;
            line += (t.isDone() ? "1" : "0") + DELIM;
            line += t.job + DELIM;
            line += ((Event) t).getAt();
        }

        return line;
    }

    private static Task formTask(String line) throws DataErrorException {
        String[] words = line.split(DELIM);

        // type of task
        String type = words[0];

        // done
        boolean isDone = "1".equals(words[1]);

        // job description
        String job = words[2];

        Task newTask = null;

        switch (type) {
        case "D":
            newTask = new Deadline(job, words[3]);
            break;
        case "E":
            newTask = new Event(job, words[3]);
            break;
        case "T":
            newTask = new Todo(job);
            break;
        default:
            throw new DataErrorException();
        }

        return newTask;
    }


    /**
     * READ AND PARSE USER INPUT
     */

    private static int parseCommand(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return BYE_COMMAND;
        }

        if (input.equalsIgnoreCase("list")) {
            return LIST_COMMAND;
        } else if (input.equalsIgnoreCase("help")) {
            return HELP_COMMAND;
        } else if (startsWith(input, "done")) {
            return DONE_COMMAND;
        } else if (startsWith(input, "todo")) {
            return TODO_COMMAND;
        } else if (startsWith(input, "deadline")) {
            return DEADLINE_COMMAND;
        } else if (startsWith(input, "event")) {
            return EVENTS_COMMAND;
        } else if (startsWith(input, "delete")) {
            return DELETE_COMMAND;
        } else {
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

        System.out.println("TASK LIST:");

        for (Task task : taskList) {
            System.out.print(numbering + ". ");
            task.printTask();
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
            if (taskList.contains(taskList.get(index))) {
                printTaskDeleted(index);
                taskList.remove(index);
            }
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
        System.out.print("   ");
        taskList.get(index).printTask();
        System.out.println("Tasks remaining: " + (taskList.size() - 1) + "\n");
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

    private static void printDataErrorWarning(int line) {
        System.out.println("Error forming task. Check formatting at line " + line + " in data file!");
        System.out.println();
    }

    private static void printHelp() {
        String commandList = "LIST - \n" +
                "FORMAT: list";

        String commandDone = "DONE - \n" +
                "FORMAT: done [(int) number]";

        String commandTodo = "TODO - \n" +
                "FORMAT: todo [(str) description]";

        String commandDeadline = "DEADLINE - \n" +
                "FORMAT: deadline [(str) description] /by [(str) deadline]";
        
        String commandEvent = "EVENT - \n" +
                "FORMAT: event [(str) description] /at [(str) time]";

        String commandDelete = "DELETE - \n" +
                "FORMAT: delete [(int) index]";


        System.out.println("COMMAND LIST:");
        System.out.println("-------------");
        System.out.println(commandTodo + '\n');
        System.out.println(commandDeadline + '\n');
        System.out.println(commandEvent + '\n');
        System.out.println(commandList + '\n');
        System.out.println(commandDone + '\n');
        System.out.println(commandDelete + '\n');
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
