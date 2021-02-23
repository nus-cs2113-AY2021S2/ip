package duke;

import duke.command.Command;
import duke.record.Record;
import duke.exception.DukeException;

/**
 * This class is to build a personal assistant chatbot called "Happy"
 * (Customized from Duke)
 *
 * @author NgManSing
 */
public class Duke {
    public static final String NAME = "Happy";
    static Record record;

    private static void initializeDuke() {
        record = new Record();
        printWelcomeMsg();
    }

    public static void main(String[] args) {
        initializeDuke();
        Command command = new Command(record);
        boolean isContinue = true;
        while (isContinue) {
            try {
                isContinue = command.receiveCommand();
            } catch (DukeException e) {
                promptUserInputInvalid();
            }
        }
    }

    private static void promptUserInputInvalid() {
        System.out.println("I don't understand your input! Please try again!");
    }

    private static void printWelcomeMsg() {
        System.out.printf("Hello! I am %s :)\n", NAME);
        System.out.println("What can I do for you?");
    }

}