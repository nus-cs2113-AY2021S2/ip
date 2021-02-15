import exceptions.IllegalCommandException;
import exceptions.IllegalListException;
import exceptions.IllegalTaskException;
import exceptions.IllegalTaskRedoException;
import list.*;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class Duke {


    public static final String CHAT_BOT_NAME = "Arthur";
    public static final String FILE_PATH = "duke.txt";
    public static ArrayList<TaskList> tasks = new ArrayList<>();
    public static duke dukeList = new duke(CHAT_BOT_NAME);

    public static void main(String[] args) {
        loadFromFile();
        dukeList.greet();
        processCommands();
    }

    public static void loadFromFile() {
        try {
            File fd = new File(FILE_PATH); // create a File for the given file path
            Scanner s = new Scanner(fd); // create a Scanner using the File as the source
            readDataFile(s);
            printOldList();
        } catch (java.io.IOException e) {
            dukeList.printNoSavedFile();
        }
    }

    private static void readDataFile(Scanner s) {
        int i = 0;
        dukeList.printLoading();
        while (s.hasNext()) {
            selectCommand(s.nextLine());
            if (s.nextLine().equals("true")) {
                TaskList t = tasks.get(i);
                t.setDone();
                tasks.set(i, t);
            }
            i++;
        }
    }

    private static void printOldList() {
        try {
            printAllLists();
        } catch (IllegalListException e) {
            dukeList.printEmptyFile();
        }
    }

    public static void saveToFile() {
        boolean hasSaved = true;
        do {
            hasSaved = writeToFile(hasSaved);
        } while (!hasSaved);
    }

    private static boolean writeToFile(boolean hasSaved) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            hasSaved = true;
            for (TaskList t : tasks) {
                hasSaved = writeTaskToFile(fw, t);
            }
            fw.close();
        } catch (java.io.IOException e) {
            File file = new File(FILE_PATH);
            hasSaved = false;
        }
        return hasSaved;
    }

    private static boolean writeTaskToFile(FileWriter fw, TaskList t) {
        boolean hasSaved = true;
        if (t instanceof Event) {
            Event temp = (Event) t;
            try {
                fw.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }

        } else if (t instanceof Deadline) {
            Deadline temp = (Deadline) t;
            try {
                fw.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }

        } else if (t instanceof Todo) {
            Todo temp = (Todo) t;
            try {
                fw.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }
        }
        return hasSaved;
    }


    private static void processCommands() {
        String line;
        Scanner in = new Scanner(System.in);
        boolean hasToContinue = true;

        dukeList.printHelp();
        while (hasToContinue) {
            line = in.nextLine();
            hasToContinue = selectCommand(line);
        }

    }

    private static boolean selectCommand(String line) {
        switch (line) {
        case "bye":
            exit();
            return false;
        case "list":
            try {
                printAllLists();
            } catch (IllegalListException e) {
                dukeList.printEmptyList();
            }
            break;
        case "help":
            dukeList.printHelp();
            break;

        default:
            handleAmendList(line);
            break;
        }
        return true;
    }

    private static void handleAmendList(String line) {
        try {
            amendList(line);
        } catch (IllegalCommandException e) {
            dukeList.printCommandDoesNotExist();
        } catch (IllegalTaskException e) {
            dukeList.printInvalidTaskPhrase();
        } catch (IllegalTaskRedoException e) {
            printTaskAlreadyCompletedPhrase();
        }
        dukeList.printDottedLines();
    }

    private static void printAllLists() throws IllegalListException {
        int i = 1;
        if (tasks.size() == 0) {
            throw new IllegalListException();
        }
        dukeList.printListName();
        for (TaskList t : tasks) {
            System.out.print(i + ". ");
            t.printTask();
            i++;
        }
        printNumberOfTasksLeft();
        dukeList.printDottedLines();
    }

    private static void amendList(String line) throws IllegalCommandException, IllegalTaskException,
            IllegalTaskRedoException {
        String[] sentence = line.split(" ");
        if (sentence.length < 2) {
            throw new IllegalCommandException();
        }
        switch (sentence[0]) {
        case "done":
            try {
                markTaskAsDone(sentence);
            } catch (IllegalCommandException e) {
                throw new IllegalCommandException();
            } catch (IllegalTaskRedoException e) {
                throw new IllegalTaskRedoException();
            } catch (IllegalTaskException e) {
                throw new IllegalTaskException();
            }
            break;

        case "todo": {
            addTaskInTodoList(line);
            break;
        }
        case "deadline": {
            addTaskInDeadlineList(line);
            break;
        }
        case "event": {
            addTaskInEventList(line);
            break;
        }
        case "delete": {
            try {
                deleteTask(sentence);
            } catch (IllegalCommandException e) {
                throw new IllegalCommandException();
            } catch (IllegalTaskException e) {
                throw new IllegalTaskException();
            }

            break;
        }
        default:
            throw new IllegalCommandException();
        }
        saveToFile();
    }

    private static void addTaskInDeadlineList(String line) {
        String[] commandWords = (line.split(" ", 2));
        String description = commandWords[1];
        Deadline newTask = new Deadline(description);
        tasks.add(newTask);
    }

    private static void addTaskInEventList(String line) {
        String[] commandWords = (line.split(" ", 2));
        String description = commandWords[1];
        Event newTask = new Event(description);
        tasks.add(newTask);
    }

    private static void addTaskInTodoList(String line) {
        String[] commandWords = (line.split(" ", 2));
        String description = commandWords[1];
        Todo newTask = new Todo(description);
        tasks.add(newTask);
    }

    private static void deleteTask(String[] sentence) throws IllegalCommandException, IllegalTaskException {
        int index;
        try {
            index = getIndex(sentence);
        } catch (IllegalCommandException e) {
            throw new IllegalCommandException();
        } catch (IllegalTaskException e) {
            throw new IllegalTaskException();
        }

        TaskList t = tasks.get(index - 1);
        dukeList.printDeletingTask();
        t.printTask();
        tasks.remove(index - 1);
        if (tasks.size() > 0) {
            printNumberOfTasksLeft();
        }
    }

    private static int getIndex(String[] sentence) throws IllegalCommandException, IllegalTaskException {
        if (sentence.length > 2) {
            throw new IllegalCommandException();
        }
        int index = getIndexFromCommand(sentence[1]);
        if (index > tasks.size() || index == -1) {
            throw new IllegalTaskException();
        }
        return index;
    }

    private static void markTaskAsDone(String[] sentence) throws IllegalCommandException, IllegalTaskException,
            IllegalTaskRedoException {
        int index;
        try {
            index = getIndex(sentence);
        } catch (IllegalCommandException e) {
            throw new IllegalCommandException();
        } catch (IllegalTaskException e) {
            throw new IllegalTaskException();
        }

        TaskList t = tasks.get(index - 1);
        try {
            t.markAsDone();
        } catch (IllegalTaskRedoException e) {
            throw new IllegalTaskRedoException();
        }
        tasks.set(index - 1, t);
        printNumberOfTasksLeft();
    }


    public static int getIndexFromCommand(String index) {
        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException nfe) {
            return -1;
        }

    }

    public static void printNumberOfTasksLeft() {
        if (getAreAllTasksDone() && tasks.size() > 0) {
            dukeList.printCompletedTasks();
        } else if (getAreAllTasksNotDone() && tasks.size() > 0) {
            dukeList.printNoTasksDone();
        } else {
            int tasksLeft = getNumberOfTaskRemaining();
            dukeList.printSomeTasksRemaining(tasksLeft);
        }
    }

    public static boolean getAreAllTasksDone() {
        boolean areAllTasksDone = true;
        for (TaskList t : tasks) {
            if (!t.getIsTaskDone()) {
                areAllTasksDone = false;
                break;
            }
        }
        return areAllTasksDone;
    }

    public static boolean getAreAllTasksNotDone() {
        boolean areAllTasksNotDone = true;
        for (TaskList t : tasks) {
            if (t.getIsTaskDone()) {
                areAllTasksNotDone = false;
                break;
            }
        }
        return areAllTasksNotDone;
    }


    private static int getNumberOfTaskRemaining() {
        int counter = 0;
        for (TaskList t : tasks) {
            if (!t.getIsTaskDone()) {
                counter++;
            }
        }
        return counter;
    }

    public static void printTaskAlreadyCompletedPhrase() {
        String phrase;
        if (getAreAllTasksDone()) {
            phrase = "This job was already completed!!!!" + System.lineSeparator()
                    + "Good job Crewmate! You completed all your tasks in this list! (─‿─)" + System.lineSeparator();


        } else {
            phrase = "What are you doing??? This job was already completed!! (;¬_¬)" + System.lineSeparator()
                    + "You still have " + getNumberOfTaskRemaining()
                    + " tasks left in this list Crewmate! Hurry up!! ＼(｀0´)／";
        }
        System.out.println(phrase);
    }


    public static void exit() {
        if (tasks.size() > 0 && getAreAllTasksDone()) {
            dukeList.printGoodEnding();
        } else if (tasks.size() > 0 && getAreAllTasksNotDone()) {
            dukeList.printBadEnding();
        } else {
            dukeList.printTraitor();
        }
    }

}
