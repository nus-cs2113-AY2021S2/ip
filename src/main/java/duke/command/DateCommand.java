package duke.command;

import duke.common.Messages;
import duke.common.Utils;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class DateCommand extends Command {
    public DateCommand(String commandArgs) {
        super(CommandType.DATE, commandArgs);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        LocalDateTime date = Utils.getDateFromUserInput(commandArgs);

        ArrayList<Task> matches = new ArrayList<>();
        // Add matching deadlines
        matches.addAll(tasks.getTasks().stream()
            .filter(t -> t instanceof Deadline)
            .map(t -> (Deadline) t)
            .filter(d -> d.getDeadline().toLocalDate().equals(date.toLocalDate()))
            .collect(toList()));
        // Add matching events
        matches.addAll(tasks.getTasks().stream()
            .filter(t -> t instanceof Event)
            .map(t -> (Event) t)
            .filter(e -> e.getDatetime().toLocalDate().equals(date.toLocalDate()))
            .collect(toList()));
        if (matches.size() == 0) {
            ui.printText(Messages.MESSAGE_NOT_FOUND_DATES + commandArgs);
            return;
        }

        ui.printText("Here are deadlines/events on " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ":");
        matches.stream()
                .forEach(m -> ui.printText(" " + tasks.getTaskNumber(m) + ". " + m));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
