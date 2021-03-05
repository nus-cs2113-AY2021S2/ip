/**
 * Handles parsing of the user input and execution of the respective commands requested by user.
 */
public class Parser {
    private static final String TODO_CMD = "todo";
    private static final String DEADLINE_CMD = "deadline";
    private static final String EVENT_CMD = "event";
    private static final String FIND_CMD = "find";
    private static final String LIST_CMD = "list";
    private static final String DONE_CMD = "done";
    private static final String DELETE_CMD = "delete";
    private static final String BYE_CMD = "bye";
    private static final String TODO_CMD_VALID_START_STR = "todo ";
    private static final String DEADLINE_CMD_VALID_START_STR = "deadline ";
    private static final String EVENT_CMD_VALID_START_STR = "event ";
    private static final String FIND_CMD_VALID_START_STR = "find ";
    private static final String DONE_CMD_VALID_START_STR = "done ";
    private static final String DELETE_CMD_VALID_START_STR = "delete ";
    private static final String BY_VALID_STR = " /by ";
    private static final String AT_VALID_STR = " /at ";
    private static final int INVALID_INDEX = -1;
    private static final int TODO_CMD_DESCRIPTION_INDEX = 5;
    private static final int DEADLINE_CMD_DESCRIPTION_INDEX = 0;
    private static final int EVENT_CMD_DESCRIPTION_INDEX = 0;
    private static final int FIND_CMD_KEYWORD_INDEX = 5;
    private static final int DEADLINE_CMD_REQUEST_INDEX = 9;
    private static final int EVENT_CMD_REQUEST_INDEX = 6;
    private static final int DONE_CMD_TASK_NUM_INDEX = 5;
    private static final int DELETE_CMD_TASK_NUM_INDEX = 7;
    private static final int NUM_CHARS_TO_BY_CONTENT_INDEX = 4;
    private static final int NUM_CHARS_TO_AT_CONTENT_INDEX = 4;

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private boolean isExit;

    public Parser(Ui ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
        this.isExit = false;
    }

    /**
     * Parses the command entered by the user and execute the respective command method.
     *
     * @param command command entered by user.
     */
    public void handleCommand(String command) {
        try {
            if (command.startsWith(TODO_CMD)) {
                storeTodoTask(command);
            } else if (command.startsWith(DEADLINE_CMD)) {
                storeDeadlineTask(command);
            } else if (command.startsWith(EVENT_CMD)) {
                storeEventTask(command);
            } else if (command.startsWith(FIND_CMD)) {
                findTask(command);
            } else if (command.equals(LIST_CMD)) {
                displayStoredTasks();
            } else if (command.startsWith(DONE_CMD)) {
                markTaskAsDone(command);
            } else if (command.startsWith(DELETE_CMD)) {
                deleteTask(command);
            } else if (command.equals(BYE_CMD)) {
                exitMsg();
            } else {
                invalidCommand();
            }
        } catch (DukeException e) {
            commandErrorMsg(e);
        }
    }

    private void storeTodoTask(String command) throws DukeException {
        if (command.equals(TODO_CMD)) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        if (!command.startsWith(TODO_CMD_VALID_START_STR)) {
            throw new DukeException("OOPS!!! The correct syntax for todo command is: 'todo task_description'");
        }

        String description = command.substring(TODO_CMD_DESCRIPTION_INDEX).strip();
        storeTask(new Todo(description));
    }

    private void storeDeadlineTask(String command) throws DukeException {
        if (command.equals(DEADLINE_CMD)) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }

        if (!command.startsWith(DEADLINE_CMD_VALID_START_STR)) {
            throw new DukeException("OOPS!!! The correct syntax for deadline command is: " +
                    "'deadline task_description /by due_date'");
        }

        String request = command.substring(DEADLINE_CMD_REQUEST_INDEX).strip();
        int indexOfBy = request.indexOf(BY_VALID_STR);
        if (indexOfBy == INVALID_INDEX) {
            throw new DukeException("OOPS!!! The correct syntax for deadline command is: " +
                    "'deadline task_description /by due_date'");
        }

        String description = request.substring(DEADLINE_CMD_DESCRIPTION_INDEX, indexOfBy).strip();
        String by = request.substring(indexOfBy + NUM_CHARS_TO_BY_CONTENT_INDEX).strip();
        storeTask(new Deadline(description, by));
    }

    private void storeEventTask(String command) throws DukeException {
        if (command.equals(EVENT_CMD)) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }

        if (!command.startsWith(EVENT_CMD_VALID_START_STR)) {
            throw new DukeException("OOPS!!! The correct syntax for event command is: " +
                    "'event task_description /at timeframe'");
        }

        String request = command.substring(EVENT_CMD_REQUEST_INDEX).strip();
        int indexOfAt = request.indexOf(AT_VALID_STR);
        if (indexOfAt == INVALID_INDEX) {
            throw new DukeException("OOPS!!! The correct syntax for event command is: " +
                    "'event task_description /at timeframe'");
        }

        String description = request.substring(EVENT_CMD_DESCRIPTION_INDEX, indexOfAt).strip();
        String at = request.substring(indexOfAt + NUM_CHARS_TO_AT_CONTENT_INDEX).strip();
        storeTask(new Event(description, at));
    }

    private void storeTask(Task taskToStore) {
        tasks.addTask(taskToStore);
        storage.saveStoredTasksData();
        ui.printStoreTaskMsg(taskToStore, tasks.getTaskCount());
    }

    private void findTask(String command) throws DukeException {
        if (command.equals(FIND_CMD)) {
            throw new DukeException("OOPS!!! The keyword for a find command cannot be empty.");
        }

        if (!command.startsWith(FIND_CMD_VALID_START_STR)) {
            throw new DukeException("OOPS!!! The correct syntax for find command is: 'find keyword'");
        }

        String keyword = command.substring(FIND_CMD_KEYWORD_INDEX).strip();
        TaskList tasksFound = tasks.findTasksByKeyword(keyword);
        ui.printTasksFound(tasksFound);
    }

    private void displayStoredTasks() {
        if (tasks.isEmpty()) {
            ui.printEmptyTaskListMsg();
            return;
        }
        ui.printStoredTasks();
    }

    private void markTaskAsDone(String command) throws DukeException {
        if (command.equals(DONE_CMD)) {
            throw new DukeException("OOPS!!! The task number of a done command cannot be empty.");
        }

        if (!command.startsWith(DONE_CMD_VALID_START_STR)) {
            throw new DukeException("OOPS!!! The correct syntax for done command is: 'done task_number'");
        }

        String taskNumber = command.substring(DONE_CMD_TASK_NUM_INDEX).strip();
        int indexOfTaskToMark;
        try {
            indexOfTaskToMark = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The correct syntax for done command is: 'done task_number'");
        }

        boolean isValidTaskIndex = (indexOfTaskToMark >= 0) && (indexOfTaskToMark < tasks.getTaskCount());
        if (!isValidTaskIndex) {
            throw new DukeException("OOPS!!! You entered an invalid task number!");
        }
        Task taskToMark = tasks.getTaskAt(indexOfTaskToMark);
        taskToMark.markAsDone();
        storage.saveStoredTasksData();
        ui.printMarkTaskAsDoneMsg(taskToMark);
    }

    private void deleteTask(String command) throws DukeException {
        if (command.equals(DELETE_CMD)) {
            throw new DukeException("OOPS!!! The task number of a delete command cannot be empty.");
        }

        if (!command.startsWith(DELETE_CMD_VALID_START_STR)) {
            throw new DukeException("OOPS!!! The correct syntax for delete command is: 'delete task_number'");
        }

        String taskNumber = command.substring(DELETE_CMD_TASK_NUM_INDEX).strip();
        int indexOfTaskToDelete;
        try {
            indexOfTaskToDelete = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The correct syntax for delete command is: 'delete task_number'");
        }

        boolean isValidTaskIndex = (indexOfTaskToDelete >= 0) && (indexOfTaskToDelete < tasks.getTaskCount());
        if (!isValidTaskIndex) {
            throw new DukeException("OOPS!!! You entered an invalid task number!");
        }
        Task deletedTask = tasks.deleteTask(indexOfTaskToDelete);
        storage.saveStoredTasksData();
        ui.printDeleteTaskMsg(deletedTask);
    }

    private void exitMsg() {
        isExit = true;
        ui.printExitMsg();
        storage.saveStoredTasksData();
    }

    private void invalidCommand() throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private void commandErrorMsg(DukeException e) {
        ui.printCommandErrorMsg(e.getMessage());
    }

    /**
     * Indicates if the user wants to terminate the program.
     *
     * @return a boolean indicating if the user had entered a "bye" command to terminate the program.
     */
    public boolean isExit() {
        return isExit;
    }
}
