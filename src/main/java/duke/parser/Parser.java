package duke.parser;

import duke.command.*;
import duke.ui.Ui;
import duke.storage.Storage;

import java.util.Scanner;

/**
 * Represents a parser that parses commands input by user.
 */
public class Parser {
    public static final String EXIT_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String DONE_COMMAND = "done";
    public static final String TASK_TYPE_TODO_COMMAND = "todo";
    public static final String TASK_TYPE_DEADLINE_COMMAND = "deadline";
    public static final String TASK_TYPE_EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";

    /**
     * Returns nothing.
     * Parses and executes commands until the user inputs the command to exit.
     *
     * @return not applicable.
     */
    public static void getInput() {
        boolean isExit = false;
        while (!isExit) {
            Scanner sc = new Scanner(System.in);
            String inputCommand = sc.nextLine();
            if (inputCommand.startsWith(DONE_COMMAND)) {
                new MarkAsDoneCommand(inputCommand);
            } else if (inputCommand.startsWith(TASK_TYPE_TODO_COMMAND)) {
                new AddTodoCommand(inputCommand);
            } else if (inputCommand.startsWith(TASK_TYPE_DEADLINE_COMMAND)) {
                new AddDeadlineCommand(inputCommand);
            } else if (inputCommand.startsWith(TASK_TYPE_EVENT_COMMAND)) {
                new AddEventCommand(inputCommand);
            } else if (inputCommand.startsWith(DELETE_COMMAND)) {
                new DeleteCommand(inputCommand);
            } else if (inputCommand.equals(LIST_COMMAND)) {
                new ListCommand();
            } else if (inputCommand.equals(EXIT_COMMAND)) {
                Ui.printGoodbyeMessage();
                isExit = true;
            } else if (inputCommand.equals("save")) {
                Storage.saveData();
            } else if (inputCommand.startsWith("find")) {
                new FindCommand(inputCommand);
            } else {
                Ui.printInvalidMessage(inputCommand);
            }
            System.out.println(Ui.LINE);
        }

    }
}

