import java.io.*;
import java.util.Scanner;
import duke.Deadline;
import duke.Task;
import duke.Event;
import duke.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    /**
     * final integer to represents various index to indicate task name and deadline/event date
     */
    static final int BY_AT_INDEX = 4;
    static final int FIND_INDEX = 5;
    static final int TODO_INDEX = 5;
    static final int EVENT_INDEX = 6;
    static final int DELETE_INDEX = 7;
    static final int DEADLINE_INDEX = 9;
    static final int MAX_NO_OF_TASKS = 100;

    private Ui ui;
    private TaskList tl;
    private Storage storage;
    final private static Task[] tasks = new Task[MAX_NO_OF_TASKS];
    final private static String filePath = "C:/Users/XPS/Desktop/duke.txt";

    /**
     * Initiate the Ui Class and Storage Class with the default filepath
     * @param filePath an default filepath to store duke.txt
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * main method to run Duke, use if else statement to differentiate different
     * command entered by the user
     */
    public void run() {
        int index = 0;
        String task;
        Scanner b = new Scanner(System.in);
        task = b.nextLine();
        while (true) {
            if (task.equalsIgnoreCase("bye")) {
                ui.showExitMessage();
                break;
            } else if (task.equals("list")) {
                tl.showAllTasks(index, tasks);
            } else if (task.contains("done")) {
                try {
                    String taskNo = task.substring(TODO_INDEX);
                    int newTaskNo = Parser.parseInt(taskNo);
                    tasks[newTaskNo - 1].setTaskStatus(true);
                    tl.markAsDone(newTaskNo-1, tasks);
                    storage.replaceTXT(tasks[newTaskNo - 1].getDescription());
                } catch (StringIndexOutOfBoundsException e) {
                    ui.doneIndexError();
                } catch (IOException e) {
                    ui.exceptionMessage();
                }
            } else if (task.contains("todo")) {
                try {
                    String taskName = task.substring(TODO_INDEX);
                    tasks[index] = new Todo(taskName, 'T');
                    index = tl.addTaskMessage(index, tasks[index]);
                    String textToAppend = "T | 0 | " + taskName;
                    storage.appendToFile(filePath, textToAppend);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.todoIndexError();
                } catch (FileNotFoundException e) {
                    ui.fileErrorMessage();
                } catch (IOException e) {
                    ui.exceptionMessage();
                    e.getMessage();
                }
            } else if (task.contains("deadline")) {
                try {
                    int slashSign = task.indexOf("/");
                    String ddlTaskName = task.substring(DEADLINE_INDEX, slashSign - 1);
                    String by = task.substring(slashSign + BY_AT_INDEX);
                    if (by.contains("-")) { Parser.parseDate(by); }
                    tasks[index] = new Deadline(ddlTaskName, 'D', by);
                    index = tl.addTaskMessage(index, tasks[index]);
                    String textToAppend = "D | 0 | " + ddlTaskName + " | " + by;
                    Storage.appendToFile(filePath, textToAppend);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.deadlineIndexError();
                } catch (FileNotFoundException e) {
                    ui.fileErrorMessage();
                } catch (IOException e) {
                    ui.exceptionMessage();
                    e.getMessage();
                }
            } else if (task.contains("event")) {
                try {
                    int slashSign = task.indexOf("/");
                    String eventTaskName = task.substring(EVENT_INDEX, slashSign - 1);
                    String at = task.substring(slashSign + BY_AT_INDEX);
                    tasks[index] = new Event(eventTaskName, 'E', at);
                    index = tl.addTaskMessage(index, tasks[index]);
                    String textToAppend = "E | 0 | " + eventTaskName + " | " + at;
                    storage.appendToFile(filePath, textToAppend);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.eventIndexError();
                } catch (FileNotFoundException e) {
                    ui.fileErrorMessage();
                } catch (IOException e) {
                    ui.exceptionMessage();
                    e.getMessage();
                }
            } else if (task.contains("delete")) {
                try {
                    int deleteIndex = Parser.parseInt(task.substring(DELETE_INDEX));
                    Task delete_task = tasks[deleteIndex - 1];
                    tl.removeTaskMessage(index, delete_task);
                    tl.removeTask(deleteIndex, index, tasks);
                }  catch (StringIndexOutOfBoundsException e) {
                    ui.deleteIndexError();
                }
            } else if (task.contains("find")) {
                String findTask = task.substring(FIND_INDEX);
                tl.findTasks(index, findTask, tasks);
            } else { ui.showErrorMessage(); }
            task = b.nextLine();
        }
    }

    /**
     * main method to run the program, initiate a new Duke Class with the specified
     * filepath to store task data and run the program
     * @param args contains the command-line arguments passed to the Duke program upon invocation
     */
    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}


