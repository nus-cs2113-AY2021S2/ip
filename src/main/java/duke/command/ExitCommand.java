package duke.command;

import duke.ui.Ui;

import java.util.ArrayList;
public class ExitCommand extends Command{
    public ExitCommand() {
        Ui.printGoodbyeMessage();
    }
}
