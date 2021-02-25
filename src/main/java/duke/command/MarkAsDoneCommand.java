package duke.command;

import duke.Duke;

public class MarkAsDoneCommand extends Command{
    public MarkAsDoneCommand(String description) {
        Duke.markAsDone(description);
    }
}
