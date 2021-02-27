package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;
import duke.tasks.Deadline;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final String description;
    private final String date;

    public DeadlineCommand(TaskList taskList, String description, String date) {
        super(taskList);
        this.description = description;
        this.date = date;
    }

    public void execute(TaskList taskList, TextUI ui) {
        taskList.addTask(new Deadline(description, date));
    }
}