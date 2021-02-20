package duke;


import exceptions.DukeException;
import tasks.*;

public class Parser {
    static final int TODOLENGTH = 4;
    static final int EVENTLENGTH = 5;
    static final int DEADLINELENGTH = 8;
    static final int DELETELENGTH = 6;

    public static void processUserRequest(String task) {
        if (task.equalsIgnoreCase("list")) {
            Ui.printTaskList();
        }
        else if (task.contains("done")){
            TaskList.markTaskDone(task);
        }
        else if (task.contains("delete")){
            TaskList.deleteTask(task);
        }
        else {
            TaskList.addTask(task);
        }
    }

    public static Deadline processAddDeadline(String userInput) throws DukeException {
        if (userInput.length() == DEADLINELENGTH ) {
            throw new DukeException(TaskType.DEADLINE);
        }
        String description, by;
        userInput = userInput.substring(DEADLINELENGTH+1).trim();
        if (userInput.contains("/")){
            int idx = userInput.indexOf('/');
            description =userInput.substring(0, idx);
            by = userInput.substring(idx+3).trim();
        }
        else {
            description =userInput;
            by = "";
        }
        return new Deadline(description, by);
    }

    public static Event processAddEvent(String userInput) throws DukeException {
        if (userInput.length() == EVENTLENGTH) {
            throw new DukeException(TaskType.EVENT);
        }
        String description, at;
        userInput = userInput.substring(EVENTLENGTH+1).trim();
        if (userInput.contains("/")){
            int idx = userInput.indexOf('/');
            description =userInput.substring(0, idx);
            at = userInput.substring(idx+3).trim();
        }
        else {
            description =userInput;
            at = "";
        }

        return new Event(description, at);
    }

    public static Todo processAddTodo(String userInput) throws DukeException {
        if (userInput.length() == TODOLENGTH) {
            throw new DukeException(TaskType.TODO);
        }
        String description = userInput.substring(TODOLENGTH+1);
        return new Todo(description);
    }
}
