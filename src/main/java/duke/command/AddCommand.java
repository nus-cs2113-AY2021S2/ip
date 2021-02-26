package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.exception.EmptyDescriptionException;
import duke.exception.EmptyStringException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Add a task to TaskList.
 */
public class AddCommand extends Command {
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    private static final String EMPTY_DESCRIPTION = "";

    /**
     * Construct AddCommand using input values after being parsed.
     *
     * @param commandWord the command to identify the type of task.
     * @param description the description to be added to the TaskList.
     * @param tasks the TaskList for data to be stored into.
     * @throws EmptyDescriptionException if description entered by user are empty.
     */
    public AddCommand(String commandWord, String description, TaskList tasks) throws EmptyDescriptionException {
        super();

        if (description.equals(EMPTY_DESCRIPTION)) {
            throw new EmptyDescriptionException();
        }

        this.description = description;
        this.commandWord = commandWord;
        setTasks(tasks);
    }

    @Override
    public CommandResult execute() {
        switch (commandWord) {
        case TODO_COMMAND:
            ToDo todo = new ToDo(description);
            addTodo(todo);
            return new CommandResult(addMessage(todo));
        case DEADLINE_COMMAND:
            try {
                Deadline deadline = new Parser().parseDeadline(description);
                addDeadline(deadline);
                return new CommandResult(addMessage(deadline));
            } catch (EmptyStringException emptyStringException) {
                return new InvalidCommand(commandWord, description, emptyStringException).execute();
            }
        case EVENT_COMMAND:
            try {
                Event event = new Parser().parseEvent(description);
                addEvent(event);
                return new CommandResult(addMessage(event));
            } catch (EmptyStringException emptyStringException) {
                return new InvalidCommand(commandWord, description, emptyStringException).execute();
             }
        }
        return null;
    }

    private void addTodo(ToDo todo) {
        tasks.addTask(todo);
    }

    private void addDeadline(Deadline deadline) {
        tasks.addTask(deadline);
    }

    private void addEvent(Event event) {
        tasks.addTask(event);
    }

    private String addMessage (Task task) {
        String addMessage = "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
        return addMessage;
    }


}
