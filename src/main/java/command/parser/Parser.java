package command.parser;

import file.storage.Storage;
import ui.UI;
import exceptions.IllegalCommandException;
import exceptions.IllegalListException;
import exceptions.IllegalTaskException;
import exceptions.IllegalTaskRedoException;
import task.list.Deadline;
import task.list.Event;
import task.list.TaskList;
import task.list.Todo;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static final int EMPTY = 0;
    public static final int NUMBER_OF_COMMAND_ARGUMENTS = 2;
    public static final int DESCRIPTION_INDEX_IN_COMMANDS = 1;
    public static final int WRONG_INDEX = -1;
    public static final int COMMAND_KEYWORD_POSITION = 0;

    public static void processCommands(ArrayList<TaskList> tasks) {
        String line;
        Scanner in = new Scanner(System.in);
        boolean hasToContinue = true;
        UI.printHelp();
        while (hasToContinue) {
            line = in.nextLine();
            hasToContinue = selectCommand(line, tasks);
        }
    }

    public static boolean selectCommand(String line, ArrayList<TaskList> tasks) {
        switch (line) {
        case "bye":
            exit(tasks);
            return false;
        case "list":
            try {
                printAllLists(tasks);
            } catch (IllegalListException e) {
                UI.printEmptyList();
            }
            break;
        case "help":
            UI.printHelp();
            break;
        default:
            handleAmendList(line, tasks);
            break;
        }
        return true;
    }

    private static void handleAmendList(String line, ArrayList<TaskList> tasks) {
        try {
            amendList(line, tasks);
        } catch (IllegalCommandException e) {
            UI.printCommandDoesNotExist();
        } catch (IllegalTaskException e) {
            UI.printInvalidTaskPhrase();
        } catch (IllegalTaskRedoException e) {
            UI.printTaskAlreadyCompletedPhrase(tasks);
        }
        UI.printDottedLines();
    }

    public static void printAllLists(ArrayList<TaskList> tasks) throws IllegalListException {
        int i = 1;
        if (tasks.size() == EMPTY) {
            throw new IllegalListException();
        }
        UI.printListName();
        for (TaskList t : tasks) {
            System.out.print(i + ". ");
            t.printTask();
            i++;
        }
        printNumberOfTasksLeft(tasks);
        UI.printDottedLines();
    }

    private static void amendList(String line, ArrayList<TaskList> tasks) throws IllegalCommandException,
            IllegalTaskException, IllegalTaskRedoException {
        String[] sentence = line.split(" ");
        if (sentence.length < NUMBER_OF_COMMAND_ARGUMENTS) {
            throw new IllegalCommandException();
        }
        switch (sentence[COMMAND_KEYWORD_POSITION]) {
        case "done":
            try {
                markTaskAsDone(sentence, tasks);
            } catch (IllegalCommandException e) {
                throw new IllegalCommandException();
            } catch (IllegalTaskRedoException e) {
                throw new IllegalTaskRedoException();
            } catch (IllegalTaskException e) {
                throw new IllegalTaskException();
            }
            break;
        case "todo": {
            addTaskInTodoList(line, tasks);
            break;
        }
        case "deadline": {
            addTaskInDeadlineList(line, tasks);
            break;
        }
        case "event": {
            addTaskInEventList(line, tasks);
            break;
        }
        case "delete": {
            try {
                deleteTask(sentence, tasks);
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
        Storage.saveToFile(tasks);
    }

    private static void addTaskInDeadlineList(String line, ArrayList<TaskList> tasks) {
        String description = getTaskDescription(line);
        Deadline newTask = new Deadline(description);
        tasks.add(newTask);
    }

    private static String getTaskDescription(String line) {
        String[] commandWords = (line.split(" ", NUMBER_OF_COMMAND_ARGUMENTS));
        return commandWords[DESCRIPTION_INDEX_IN_COMMANDS];
    }

    private static void addTaskInEventList(String line, ArrayList<TaskList> tasks) {
        String description = getTaskDescription(line);
        Event newTask = new Event(description);
        tasks.add(newTask);
    }

    private static void addTaskInTodoList(String line, ArrayList<TaskList> tasks) {
        String description = getTaskDescription(line);
        Todo newTask = new Todo(description);
        tasks.add(newTask);
    }

    private static void deleteTask(String[] sentence, ArrayList<TaskList> tasks) throws IllegalCommandException,
            IllegalTaskException {
        int index;
        try {
            index = getIndex(sentence, tasks);
        } catch (IllegalCommandException e) {
            throw new IllegalCommandException();
        } catch (IllegalTaskException e) {
            throw new IllegalTaskException();
        }

        TaskList t = tasks.get(index - 1);
        UI.printDeletingTask();
        t.printTask();
        tasks.remove(index - 1);
        if (tasks.size() > EMPTY) {
            printNumberOfTasksLeft(tasks);
        }
    }

    private static int getIndex(String[] sentence, ArrayList<TaskList> tasks) throws IllegalCommandException,
            IllegalTaskException {
        if (sentence.length > NUMBER_OF_COMMAND_ARGUMENTS) {
            throw new IllegalCommandException();
        }
        int index = getIndexFromCommand(sentence[1]);
        if (index > tasks.size() || index == -1) {
            throw new IllegalTaskException();
        }
        return index;
    }

    private static void markTaskAsDone(String[] sentence, ArrayList<TaskList> tasks) throws IllegalCommandException, IllegalTaskException,
            IllegalTaskRedoException {
        int index;
        try {
            index = getIndex(sentence, tasks);
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
        printNumberOfTasksLeft(tasks);
    }


    public static int getIndexFromCommand(String index) {
        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException nfe) {
            return WRONG_INDEX;
        }
    }

    public static void printNumberOfTasksLeft(ArrayList<TaskList> tasks) {
        if (UI.getAreAllTasksDone(tasks) && tasks.size() > EMPTY) {
            UI.printCompletedTasks();
        } else if (UI.getAreAllTasksNotDone(tasks) && tasks.size() > EMPTY) {
            UI.printNoTasksDone();
        } else {
            int tasksLeft = UI.getNumberOfTaskRemaining(tasks);
            UI.printSomeTasksRemaining(tasksLeft);
        }
    }

    public static void exit(ArrayList<TaskList> tasks) {
        if (tasks.size() > EMPTY && UI.getAreAllTasksDone(tasks)) {
            UI.printGoodEnding();
        } else if (tasks.size() > EMPTY && UI.getAreAllTasksNotDone(tasks)) {
            UI.printBadEnding();
        } else {
            UI.printTraitor();
        }
    }
}