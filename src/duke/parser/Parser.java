package duke.parser;

import duke.exception.DukeException;
import duke.preparetask.PrepareDeadline;
import duke.preparetask.PrepareEvent;
import duke.preparetask.PrepareTodo;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

/**
 * Parses user input into command for task objection creation.
 */
public class Parser {

    /**
     * @param userInput contains the full user input
     * @throws Exception if user input does not contains
     * "bye","todo","deadline","event","list","done","delete","find"
     */
    public static void parseCommand(String userInput) {

        try {
            if (!(userInput.contains("bye") || userInput.contains("todo") || userInput.contains("deadline") ||
                    userInput.contains("event") || userInput.contains("list") || userInput.contains("done") ||
                    userInput.contains("delete") || userInput.contains("find"))){
                throw new DukeException();
            }
            if (userInput.contains("todo")) {
                new PrepareTodo(userInput);
                System.out.println(TextUi.DIVIDER);
            } else if (userInput.contains("deadline")) {
                new PrepareDeadline(userInput);
                System.out.println(TextUi.DIVIDER);
            } else if (userInput.contains("event")) {
                new PrepareEvent(userInput);
                System.out.println(TextUi.DIVIDER);
            } else if (userInput.contains("list")) {
                TaskList.list();
                System.out.println(TextUi.DIVIDER);
            } else if (userInput.contains("done")) {
                TaskList.done(userInput);
                System.out.println(TextUi.DIVIDER);
            } else if (userInput.contains("delete")) {
                TaskList.delete(userInput);
                System.out.println(TextUi.DIVIDER);
            } else if (userInput.contains("find")) {
                TaskList.find(userInput);
                System.out.println(TextUi.DIVIDER);
            }
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(TextUi.DIVIDER);
        }

    }
}
