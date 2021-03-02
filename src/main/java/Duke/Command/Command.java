package Duke.Command;

import Duke.Ui.Ui;

/**
 * A command class to represent the different commands
 */

public class Command {

    /**
     * Return the exit message when buy command is given
     *
     * @return bye command message
     */

    public static String exitCommand() {
        System.out.println(Ui.goodBye());
        return Ui.goodBye();
    }
}


