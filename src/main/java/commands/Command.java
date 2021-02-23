package commands;

import task.Task;
import task.TaskManager;
import parser.Parser;
import common.Constants;
import common.Messages;
import ui.TextUi;
import storage.Storage;

public abstract class Command {

    public static final TaskManager taskManager = new TaskManager();
    public static final Parser parser = new Parser();
    public static final Constants constants = new Constants();
    public static final Messages messages = new Messages();
    public static final TextUi textUi = new TextUi();
    public static final Storage storage = new Storage();

    /**
     * display message when task added
     * @param task details of task added
     */
    public static void printAddTask(Task task) {
        textUi.showAddTask();
        System.out.println(constants.INDENTATION + task.toString());
        System.out.println("Now you have " + taskManager.getTaskCount() + " tasks in the list.");
    }
}
