package duke.parser;

import duke.commands.*;
import duke.exception.DukeException;

import java.io.FileNotFoundException;

public class Parser {

    public static Command parse(String fullCommand) {
        String activity = null;
        String action = null;
        try {
            String[] individualWords = fullCommand.split(" ", 2);
            activity = individualWords[0].toLowerCase();
            action = individualWords[1].toLowerCase();
            return commandCreation(activity, action);
        } catch (ArrayIndexOutOfBoundsException e) {
            return commandCreation(activity, null);
        }
    }

    private static Command commandCreation(String activity, String action) {
        switch (activity) {
        case "list":
            return new ListCommand(activity, action);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(activity, action);
        case "done":
            return new DoneCommand(activity, action);
        case "delete":
            return new DeleteCommand(activity, action);
        case "bye":
            return new ExitCommand(activity, action);
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            return null;
        }
    }
}
