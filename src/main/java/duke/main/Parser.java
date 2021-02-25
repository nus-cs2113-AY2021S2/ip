package duke.main;

import duke.command.*;
import duke.task.*;

public class Parser {
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String FIND = "find";


    public static Command parse(String input) {
        String[] command = getCommand(input);

        switch (command[0]) {
            case BYE:
                return new ExitCommand(input);
            case LIST:
                return new ListCommand(input);
            case DONE:
                return new DoneCommand(input);
            case DELETE:
                return new DeleteCommand(input);
            case FIND:
                return new FindCommand(input);
            default:
                return new AddCommand(input);
        }
    }

    private static String[] getCommand(String input) {
        String[] command = input.trim().split(" ");
        return command;
    }
}

