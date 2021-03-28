package duke;

import duke.command.CommandHandler;
import duke.record.Record;
import duke.exception.DukeException;

/**
 * Represents a personal assistant chatbot called "Happy"
 * (Customized from Duke)
 *
 * @author NgManSing
 * @version v1.0
 */
public class Duke {
    public static final String APP_NAME = "Happy";
    private static Record record;

    /**
     * Call this method to start the program. This method keeps on looping to ask for user inputs and perform related
     * functionalities until false is returned from {@code command.handleCommand()}. When DukeException is thrown,
     * this method will notify users that their inputted command is invalid.
     *
     * @param args Dummy command-line arguments (not used)
     */
    public static void main(String[] args) {
        initializeDuke();
        CommandHandler commandHandler = new CommandHandler(record);
        boolean isContinue = true;
        while (isContinue) {
            try {
                isContinue = commandHandler.handleCommand();
            } catch (DukeException e) {
                promptUserInputInvalid();
            }
        }
    }

    private static void initializeDuke() {
        record = new Record();
        printWelcomeMsg();
    }

    private static void printWelcomeMsg() {
        System.out.printf("Hello! I am %s :)\n", APP_NAME);
        System.out.println("What can I do for you?");
    }

    private static void promptUserInputInvalid() {
        System.out.println("I don't understand your input! Please try again!");
    }

}