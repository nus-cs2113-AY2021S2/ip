package commands;

public class Help extends Command {

    /**
     * show list of commands available
     */
    public static void execute() {
        textUi.showHelp();
    }
}
