package duke.command;
import duke.Duke;
public class ExitCommand extends Command{
    public ExitCommand() {
        Duke.printGoodbyeMessage();
    }
}
