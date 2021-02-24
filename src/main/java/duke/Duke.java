package duke;

import duke.TaskList.TaskActions;
import duke.exceptions.EmptyListException;
import duke.exceptions.TaskDoneException;
import duke.parser.Parser;
import duke.storage.StorageFile;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.TextUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private TextUI ui;
    private Parser parser;
    private StorageFile storage;
    private TaskActions actions;

    List<Task> tasks = new ArrayList<Task>();
    List<Task> findTasks = new ArrayList<Task>();

    public static void main(String[] args) {
        new Duke().run();
    }

    /** Runs the program until termination */
    public void run() {
        start();
        runCommandLoopUntilExit();
        exit();
    }

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     */
    private void start() {
        this.ui = new TextUI();
        this.parser = new Parser();
        this.storage = new StorageFile();
        this.actions = new TaskActions();

        ui.welcomeMessage();
        storage.load(tasks);
    }

    /** Reads the user command and executes it, until exit command is issued */
    private void runCommandLoopUntilExit() {
        int taskCounter = tasks.size();
        boolean isOn = true;
        while(isOn) {
            String line;
            line = parser.getUserInput().trim();

            int commandIndex = line.indexOf(' ');
            int timeIndex = line.indexOf('/');
            String command = parser.getFirstWord(line);

            if (line.equals("bye")) {
                isOn = false;
            } else if (line.equals("list")) {
                actions.listTasks(tasks);
            } else if (command.equals("done")) {
                try {
                    int index = parser.getIndexOfTask(line);
                    actions.markAsDone(tasks, index);
                    storage.writeToFile(tasks);
                } catch (EmptyListException e) {
                    System.out.println("\tIm afraid the item does not exist!");
                } catch (TaskDoneException e) {
                    System.out.println("\tTask has already been completed!");
                }
            } else if (command.equals("delete")) {
                try {
                    int index = parser.getIndexOfTask(line);
                    actions.deleteTask(tasks, index);
                    taskCounter--;
                    storage.writeToFile(tasks);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tYou can't delete something that does not exist!");
                }
            } else if (command.equals("todo")) {
                try {
                    String task = line.substring(commandIndex + 1);
                    tasks.add(new Todo(task));

                    ui.printAddedTask(tasks, taskCounter);
                    taskCounter++;
                    storage.writeToFile(tasks);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\tAre you sure you have nothing to do? :)");
                }
            } else if (command.equals("deadline")) {
                try {
                    String task = line.substring(commandIndex + 1, timeIndex - 1);
                    String time = line.substring(timeIndex + 4);
                    tasks.add(new Deadline(task, time));

                    ui.printAddedTask(tasks, taskCounter);
                    taskCounter++;
                    storage.writeToFile(tasks);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\tAre you sure you have no deadlines to meet? :)");
                }
            } else if (command.equals("event")) {
                try {
                    String task = line.substring(commandIndex + 1, timeIndex - 1);
                    String time = line.substring(timeIndex + 4);
                    tasks.add(new Event(task, time));

                    ui.printAddedTask(tasks, taskCounter);
                    taskCounter++;
                    storage.writeToFile(tasks);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\tAre you sure you have nothing going on? :)");
                }
            } else if (command.equals("find")){
                String search = line.substring(commandIndex + 1);
                actions.findTask(tasks, findTasks, search);
            } else {
                System.out.println("\tI am afraid a computer program is not able to understand what you're saying!");
            }
        }
    }

    private void exit() {
        storage.writeToFile(tasks);
        ui.showExitMessage();
    }
}
