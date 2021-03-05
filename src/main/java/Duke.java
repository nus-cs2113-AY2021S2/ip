import Duke.*;

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
            if (userCommand.equalsIgnoreCase("list")){
                listOutTasks();
            } else if (userCommand.equalsIgnoreCase("done" )) {
                markTaskAsDone(inputDetails);
            } else if (userCommand.equalsIgnoreCase("delete" )) {
                deleteTask(inputDetails);
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
            DukeUI.printLine();
            DukeUI.print(e.sendErrorMessage());
        }
    }

    private static void markTaskAsDone(String s) {
        Task selectedTask = taskManager.getTask(s);
        selectedTask.markAsDone();
        DukeUI.print("Nice! Following task is now marked as done:");
        DukeUI.print("[X] " + selectedTask.getDescription());
    }

    private static void listOutTasks() {
        DukeUI.printLine();
        int i = 0;
        while (i < taskManager.taskCount()) {
            i++;
            Task selectedTask = taskManager.getTaskWithInt(i);
            DukeUI.print(i + ". " + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        }
    }


    private static void deleteTask(String s) {
        Task selectedTask = taskManager.getTask(s);
        DukeUI.print("Noted. I've removed this task");
        DukeUI.print("\t" + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        taskManager.removeTask(s);
        DukeUI.print("Now you have " + taskManager.taskCount() + " tasks in the list.");
    }
}
