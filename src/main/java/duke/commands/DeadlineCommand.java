package duke.commands;

import java.time.LocalDate;

import duke.ui.TextUI;
import duke.tasks.TaskList;
import duke.tasks.Deadline;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final String description;
    private final LocalDate date;

    public DeadlineCommand(TaskList taskList, String description, LocalDate date) {
        super(taskList);
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.addTask(new Deadline(description, date));
    }
}