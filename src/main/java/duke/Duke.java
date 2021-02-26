package duke;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {

    /** Constants used for displaying messages */
    private static final String NEWLINE = System.lineSeparator();
    private static final String BORDER = "____________________________________________________________" + NEWLINE;
    public static final String LOGO =
            "                                      ,::::," + "\n"
            + "                          ,,,,:::::::':::::::" + "\n"
            + "        ,::::.     ..:::~~           \\::::::" + "\n"
            + "       ::::::::::~''      ':       __     ':." + "\n"
            + "       ::::::/        __    .o.. : u ::     ':." + "\n"
            + "         :::,       :: u :.' '. ' ':::'     '::    ,, .::,," + "\n"
            + "          `::       ':::' /.  : .\\         .::   ::::::::::" + "\n"
            + "          ::.               '':'            ::'  :::,'''.:::'" + "\n"
            + "          `::                  :          ,::'  ,:::',,,':::'" + "\n"
            + "           `::,                 .     ..:::.   ::::::::::::'" + "\n"
            + "              ':::::,,,....     :..::::~    ``:::::::::::'" + "\n"
            + "              :::::::::::::::::'~   .          ''::::::'" + "\n"
            + "              ::::::::::::::::::.      .         `::::'" + "\n"
            + "              ::::::::::::::::::::,     .          ::" + "\n"
            + "               ::::::::::::::::::::      :         ::" + "\n"
            + "                  ::::::::::::::::'      .       .::'" + "\n"
            + "                  :: '::::::::::::'       .     .:':" + "\n"
            + "                   ::    ~~::::''        . ,,,::::,,:::::" + "\n"
            + "                   :::,,,,,,,,,,,....:::::::::::::::::::::" + "\n"
            + "                  :::::::::::::::::   :::::::::::::::::::::" + "\n"
            + "                 .:::::::::::::::::,,  :::::::::::::::::''" + "\n"
            + "                .::::::::::::::::::::,   :::::::::::::'" + "\n"
            + "                `:::::::::::::::::::::,'" + "\n";
    public static final String GREET_MESSAGE = BORDER
            + LOGO + NEWLINE
            + "Hello, I'm Panda!" + NEWLINE
            + "What would you like to do today?" + NEWLINE
            + "Tip: use \"help\" to view all valid commands" + NEWLINE
            + BORDER + NEWLINE;
    public static final String GOODBYE_MESSAGE = BORDER
            + "Alright! Goodbye :)" + NEWLINE
            + BORDER;
    public static final String HELP_PAGE = BORDER
            + "HELP PAGE" + NEWLINE
            + "This is the list of all valid commands:" + NEWLINE + NEWLINE
            + "\thelp" + NEWLINE
            + "\t - displays all valid commands" + NEWLINE + NEWLINE
            +"\tbye" + NEWLINE
            + "\t- stops the task manager" + NEWLINE + NEWLINE
            + "\tlist" + NEWLINE
            + "\t- displays all tasks in the list" + NEWLINE + NEWLINE
            + "\ttodo     | <task>" + NEWLINE
            + "\t- adds specified task to the list" + NEWLINE + NEWLINE
            + "\tdeadline | <task>  | /by | <deadline>" + NEWLINE
            + "\t- adds specified task and deadline and to the list" + NEWLINE + NEWLINE
            + "\tevent    | <task>  | /at | <timing>" + NEWLINE
            + "\t- adds specified task and timing to the list" + NEWLINE + NEWLINE
            + "\tdone     | <index>" + NEWLINE
            + "\t- marks existing task matching the specified index"
            + "as completed in the list" + NEWLINE  + NEWLINE
            + "<> indicates an input field and | is a field separator." + NEWLINE
            + BORDER + NEWLINE;


    /** Constants used to set status of a task */
    private static final String DEFAULT_STATUS = " ";
    private static final String DONE_STATUS = "X";


    /** Invalid input messages */
    private static final String DEFAULT_INVALID_MESSAGE
            = "I'm sorry, I don't quite understand :( Could you try again?";
    private static final String MISSING_FIELDS_MESSAGE
            = "I think you missed some fields! Try again?";
    private static final String INVALID_INDEX_MESSAGE
            = "Squeal! Second field must be a number.";
    private static final String OUTSIDE_RANGE_INDEX_MESSAGE
            = "Squeal? There is no task in the list with index ";
    private static final String INVALID_TASK_MESSAGE
            = "Squeal... Are you sure that is a task?";


    /** Methods that display messages */
    public static void printGreeting() {
        System.out.print(GREET_MESSAGE);
    }

    public static void printGoodbye() {
        System.out.print(GOODBYE_MESSAGE);
    }

    public static void printInvalidInputMessage(String message) {
        String closeString = NEWLINE + BORDER + NEWLINE;
        if (message == null) {
            System.out.print(BORDER + DEFAULT_INVALID_MESSAGE + closeString);
            return;
        }
        System.out.print(BORDER + message + closeString);
    }

    public static void printHelpPage() {
        System.out.print(HELP_PAGE);
    }


    /** List of tasks being maintained and number of tasks it has */
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int tasksCount = 0;


    /** Methods that print part of or full list */
    public static void echo() {
        System.out.print(BORDER);
        System.out.print("New task added: " + NEWLINE);
        System.out.print("\t");
        tasks.get(tasksCount-1).printTask();
        printNumberOfTasks();
    }

    public static void printNumberOfTasks() {
        System.out.print(NEWLINE + "There ");
        System.out.print(tasksCount > 1 ? "are " : "is ");
        System.out.print(tasksCount);
        System.out.print(tasksCount > 1 ? " tasks" : " task");
        System.out.print(" in your list." + NEWLINE);
        System.out.print(BORDER + NEWLINE);
    }

    public static void printList() {
        System.out.print(BORDER);
        System.out.print("Here are the tasks in your list:" + NEWLINE);
        for (int i=0; i<tasksCount; i++) {
            System.out.print("\t" + (i + 1) + ". ");
            tasks.get(i).printTask();
            System.out.print(NEWLINE);
        }
        System.out.print(BORDER + NEWLINE);
    }


    /** Methods that modify the list */
    public static void addTodo(String description, String status) {
        tasks.add(new Task(description));
        tasks.get(tasksCount).setStatus(status);
        tasks.get(tasksCount).setType("T");
        tasksCount++;
    }

    public static void addDeadline(String task, String status, String deadline) {
        tasks.add(new Deadline(task, deadline));
        tasks.get(tasksCount).setStatus(status);
        tasks.get(tasksCount).setType("D");
        tasksCount++;
    }

    public static void addEvent(String task, String status, String time) {
        tasks.add(new Event(task, time));
        tasks.get(tasksCount).setStatus(status);
        tasks.get(tasksCount).setType("E");
        tasksCount++;
    }

    public static void markInList(int index) {
        tasks.get(index-1).setStatus(DONE_STATUS);
        System.out.print(BORDER);
        System.out.print("Nice! This task is now done:" + NEWLINE);
        System.out.print("\t");
        tasks.get(index-1).printTask();
        printNumberOfTasks();
    }

    public static void deleteTask(int index) {
        System.out.print(BORDER);
        System.out.print("This task has been removed:" + NEWLINE);
        System.out.print("\t");
        tasks.get(index-1).printTask();
        tasks.remove(index-1);
        tasksCount--;
        printNumberOfTasks();
    }

    
    /** Parses user's input and determines command to be run */
    public static void processInput(String userInput) {
        if (userInput.equals("list")) {
            printList();
            return;
        }
        if (userInput.equals("help")) {
            printHelpPage();
            return;
        }
        userInput = userInput.trim();
        String[] tokens = userInput.split(" ", 2);
        switch(tokens[0]) {
        case "todo":
            runTodoCommand(tokens[1]);
            break;
        case "deadline":
            runDeadlineCommand(tokens[1]);
            break;
        case "event":
            runEventCommand(tokens[1]);
            break;
        case "done":
            runDoneCommand(tokens[1]);
            break;
        case "delete":
            runDeleteCommand(tokens[1]);
            break;
        default:
            printInvalidInputMessage(null);
            break;
        }
    }


    /** Methods that run commands */
    public static void runTodoCommand(String description) {
        try {
            String taskDescription = getTodoDescription(description);
            addTodo(taskDescription, DEFAULT_STATUS);
            echo();
        } catch (ArrayIndexOutOfBoundsException e) {
            printInvalidInputMessage(MISSING_FIELDS_MESSAGE);
        } catch (DukeException e) {
            printInvalidInputMessage(INVALID_TASK_MESSAGE);
        }
    }

    public static void runDeadlineCommand(String description) {
        try {
            String[] words = getDeadlineOrEventDescription(" /by ", description);
            addDeadline(words[0], DEFAULT_STATUS, words[1]);
            echo();
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            printInvalidInputMessage(MISSING_FIELDS_MESSAGE);
        }
    }

    public static void runEventCommand(String description) {
        try {
            String[] words = getDeadlineOrEventDescription(" /at ", description);
            addEvent(words[0], DEFAULT_STATUS, words[1]);
            echo();
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            printInvalidInputMessage(MISSING_FIELDS_MESSAGE);
        }
    }

    public static void runDoneCommand (String description) {
        try {
            int index = getTaskIndex(description);
            markInList(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            printInvalidInputMessage(MISSING_FIELDS_MESSAGE);
        } catch (NumberFormatException e) {
            printInvalidInputMessage(INVALID_INDEX_MESSAGE);
        } catch (DukeException e) {
            printInvalidInputMessage(OUTSIDE_RANGE_INDEX_MESSAGE + description);
        }
    }

    public static void runDeleteCommand (String description) {
        try {
            int index = getTaskIndex(description);
            deleteTask(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            printInvalidInputMessage(MISSING_FIELDS_MESSAGE);
        } catch (NumberFormatException e) {
            printInvalidInputMessage(INVALID_INDEX_MESSAGE);
        } catch (DukeException e) {
            printInvalidInputMessage(OUTSIDE_RANGE_INDEX_MESSAGE + description);
        }
    }

    public static int getTaskIndex(String description) throws DukeException {
        int index = Integer.parseInt(description);
        boolean isPossibleIndex = index > 0;
        boolean isValidIndex = index <= tasksCount;
        if (!isPossibleIndex || !isValidIndex) {
            throw new DukeException();
        }
        return index;
    }

    public static String getTodoDescription(String description) throws DukeException {
        try {
            /* Task description contains only numbers */
            Long.parseLong(description);
            throw new DukeException();
        } catch (NumberFormatException e) {
            return description;
        }
    }

    public static String[] getDeadlineOrEventDescription(String keyword, String sentence) throws DukeException {
        String[] words = sentence.split(keyword, 2);
        if (words.length < 2) {
            throw new DukeException();
        }
        return words;
    }


    /** Methods to load from and save to disk */
    public static void loadFromDisk() {
        String fileName = "duke.txt";
        File f = new File(fileName);
        try {
            if (!f.exists()) {
                throw new FileNotFoundException();
            }
            Scanner scan = new Scanner(f);
            while (scan.hasNext()) {
                convertTextToListItem(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            //will create the file when saving
        }
    }

    public static void convertTextToListItem(String line) {
        line = line.trim();
        String[] tokens = line.split(" \\| ");
        switch(tokens[0]) {
        case "T":
            try {
                String status = Integer.parseInt(tokens[1]) == 1 ? DONE_STATUS : DEFAULT_STATUS;
                addTodo(tokens[2], status);
            } catch (NumberFormatException e) {
                return;
            }
            break;
        case "D":
            try {
                String status = Integer.parseInt(tokens[1]) == 1 ? DONE_STATUS : DEFAULT_STATUS;
                addDeadline(tokens[2], status, tokens[3]);
            } catch (NumberFormatException e) {
                return;
            }
            break;
        case "E":
            try {
                String status = Integer.parseInt(tokens[1]) == 1 ? DONE_STATUS : DEFAULT_STATUS;
                addEvent(tokens[2], status, tokens[3]);
            } catch (NumberFormatException e) {
                return;
            }
            break;
        default:
            break;
        }
    }

    public static void saveToDisk(String text) {
        String fileName = "duke.txt";
        File f = new File(fileName);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(fileName);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.print("Failed to save to disk." + NEWLINE);
        }
    }

    public static String convertListToText() {
        StringBuilder text = new StringBuilder();
        for (int i=0; i<tasksCount; i++) {
            text.append(tasks.get(i).toString());
            text.append(NEWLINE);
        }
        return text.toString();
    }


    /** Main method */
    public static void main(String[] args) {
        printGreeting();
        loadFromDisk();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            processInput(userInput);
            userInput = in.nextLine();
        }
        String textToOverwrite = convertListToText();
        saveToDisk(textToOverwrite);
        printGoodbye();
    }
}
