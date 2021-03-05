package Duke;

import Duke.Parser.DukeParser;
import Duke.Parser.TodoException;
import Duke.Storage.DukeStorage;
import Duke.Task.Task;
import Duke.TaskManager.TaskManager;
import Duke.UI.DukeUI;

public class Duke {
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        DukeStorage.createFileIfThereIsNone();
        taskManager = DukeStorage.loadData();
        DukeUI.printWelcomeMessage();
        DukeUI.printLine();
        while(true) {
            String[] listOfUserInputs = DukeUI.getUserInput();
            String userCommand = listOfUserInputs[0];
            String inputDetails = listOfUserInputs[1];
            if (userCommand.equalsIgnoreCase("bye")){
                DukeStorage.endDuke(taskManager);
                break;
            }
            if (userCommand.equalsIgnoreCase("list")) TaskManager.listOutTasks();
            else if (userCommand.equalsIgnoreCase("done" )) {
                TaskManager.markTaskAsDone(inputDetails);
            } else if (userCommand.equalsIgnoreCase("delete" )) {
                TaskManager.deleteTask(inputDetails);
            } else  {
                if (!DukeParser.isValidCommand(userCommand)) {
                    continue;
                }
                //process Todo, event or deadline
                processCommandWithException(userCommand, inputDetails);
            }
            DukeUI.printLine();
        }
    }

    private static void processCommandWithException(String userCommand, String inputDetails) {
        try {
            Task newTask = DukeParser.processUserRequest(userCommand, inputDetails);
            taskManager.addTask(newTask);
            DukeUI.notifyUserNewTask(newTask, taskManager);
        } catch (TodoException e) {
            DukeUI.informErrorToUser(e);
        }
    }
}
