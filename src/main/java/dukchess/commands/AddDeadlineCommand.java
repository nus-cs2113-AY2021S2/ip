package dukchess.commands;

import static dukchess.utility.Utils.isNumeric;

import dukchess.entity.Deadline;
import dukchess.ui.Ui;

/**
 * Command for adding a new deadline
 */
public class AddDeadlineCommand extends Command {
    private static String addDeadline(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        return String.format("Gotcha, added this deadline: %s", newDeadline.toString());
    }

    /**
     * Performs input validation before adding a new deadline to the list of tasks.
     * @param commandArgs - expects a two-length array, first element is deadline's description,
     *                    second element is deadline's due date
     */
    public static void handleAddDeadline(String commandArgs) {
        if (commandArgs.length() == 0) {
            Ui.printErrorMessage("Oops, deadline description cannot be empty :(");
            return;
        }
        String[] deadlineArgs = commandArgs.split(" /by ");
        if (deadlineArgs.length != 2) {
            Ui.printErrorMessage("Oops, deadline due date cannot be empty :(");
            return;
        }
        String deadlineDueBy = deadlineArgs[1];
        if (isNumeric(deadlineDueBy) && Double.parseDouble(deadlineDueBy) <= 0) {
            Ui.printErrorMessage("A numeric deadline should never be negative, " +
                    "otherwise the time travel police is gonna come find you!");
            return;
        }
        String deadlineAdditionOutcome = addDeadline(deadlineArgs[0], deadlineArgs[1]);
        System.out.println(deadlineAdditionOutcome);
    }
}
