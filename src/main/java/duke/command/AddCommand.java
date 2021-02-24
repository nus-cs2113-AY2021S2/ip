package duke.command;

import duke.Keyword;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class AddCommand extends Command {

    String at;
    String by;
    final int LENGTH_OF_TODO = 4;
    final int LENGTH_OF_EVENT = 5;
    final int LENGTH_OF_DEADLINE = 8;
    final int START_INDEX_OF_BY = 3;
    final int START_INDEX_OF_AT = 3;
    final int START_INDEX_OF_EVENT = 5;
    final int START_INDEX_OF_DEADLINE = 8;

    public AddCommand(String fullCommand) {
        super(fullCommand);
    }

    public boolean isValidInput() {
        if (fullCommand.contains("todo") && fullCommand.length() != LENGTH_OF_TODO) {
            return true;
        }
        if (fullCommand.contains("event") && fullCommand.length() != LENGTH_OF_EVENT) {
            return true;
        }
        if (fullCommand.contains("deadline") && fullCommand.length() != LENGTH_OF_DEADLINE) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!isValidInput()) {
            Ui.printCommandIsInvalid();
            return;
        }
        if (fullCommand.contains("todo")) {
            Todo todo = new Todo(fullCommand.replaceFirst("todo", ""));
            taskList.addTask(todo);
            Keyword.setKeywords("T");
            Ui.printTodoDescription();
            todo.printDescription();
            System.out.println("\n");
            Ui.printTaskSize();
        } else if (fullCommand.contains("event")) {
            at = fullCommand.substring(fullCommand.indexOf("/") + START_INDEX_OF_AT);
            fullCommand = fullCommand.substring(START_INDEX_OF_EVENT, fullCommand.indexOf("/"));
            Event event = new Event(fullCommand, at);
            taskList.addTask(event);
            Keyword.setKeywords("E");
            Ui.printEventDescription();
            event.printDescription();
            Ui.printAtDescription();
            Ui.printTaskSize();
        } else if (fullCommand.contains("deadline")) {
            by = fullCommand.substring(fullCommand.indexOf("/") + START_INDEX_OF_BY);
            fullCommand = fullCommand.substring(START_INDEX_OF_DEADLINE, fullCommand.indexOf("/"));
            Deadline deadline = new Deadline(fullCommand, by);
            taskList.addTask(deadline);
            Keyword.setKeywords("D");
            Ui.printDeadlineDescription();
            deadline.printDescription();
            Ui.printByDescription();
            Ui.printTaskSize();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
