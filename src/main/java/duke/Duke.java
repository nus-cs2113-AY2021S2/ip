package duke;

import java.io.IOException;

/**
 * The main driver for the Duke program
 * a to-do list tracker that allows users to input 3 types of tasks:
 * Todos, Events, and Deadlines
 * as well as the functions to delete, find, or mark a task as done.
 */
public class Duke {

    public static void main(String[] args) {
        try {
            Storage.loadData(Storage.filepath);
        } catch (IOException e) {
            Ui.showLoadingError(e);
        }

        Ui.welcomeMessage();


        Parser.input = Parser.getInput(Parser.in);
        Command command;

        while (!Parser.isBye()) {
            if (Parser.isList()) {
                command = Command.LIST;
            } else if (Parser.isDone()) {
                command = Command.DONE;
            } else if (Parser.isTodo()) {
                command = Command.TODO;
            } else if (Parser.isEvent()) {
                command = Command.EVENT;
            } else if (Parser.isDelete()) {
                command = Command.DELETE;
            } else if (Parser.isDeadline()) {
                command = Command.DEADLINE;
            } else {
                command = Command.INVALID;
            }

            try {
                Parser.executeCommand(Parser.input, command);
            } catch (InvalidCommandException e) {
                Ui.showInvalidCommandError();
            } catch (EmptyInputException e) {
                Ui.showEmptyInputError();
            } catch (StringIndexOutOfBoundsException e) {
                Ui.showNoTimeAddedError();
            } catch (InvalidEventTimeException e) {
                Ui.showInvalidEventTimeError();
            } catch (InvalidDeadlineTimeException e) {
                Ui.showInvalidDeadlineTimeError();
            } catch (NumberFormatException e) {
                Ui.showInvalidIntegerTaskIndexError();
            } catch (IndexOutOfBoundsException e) {
                Ui.showTaskIndexNotExistsError();
            }

            Storage.saveData();

            System.out.println();
            Parser.input = Parser.getInput(Parser.in);
        }


        Ui.exitMessage();

    }


}
