package duke;

import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.EmptyOrWrongInputException;
import duke.task.TaskManager;

public class CommandManager {

    protected String command;
    protected String description;

    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";
    private static final String DONE_COMMAND = "done";

    private static final String WHITESPACE_DELIMITER = "\\s+";
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";

    public static final int SPLIT_SIZE = 2;
    private static final int FIRST_HALF = 0;
    private static final int SECOND_HALF = 1;

    private static final int ARRAY_INDEX_FOR_DATE = 1;
    private static final int EXTRA_INDEX_FOR_ARRAY = 1;

    public CommandManager(String userInput) {
        String[] words = splitUserInput(userInput, WHITESPACE_DELIMITER);
        this.command = words[FIRST_HALF];
        this.description = words[SECOND_HALF];
    }

    public static String[] splitUserInput(String userInput, String delimiter) {
        String[] sentences = userInput.trim().split(delimiter, SPLIT_SIZE);
        return sentences.length == SPLIT_SIZE ? sentences : new String[] { sentences[0] , "" }; // else case: no description;
    }

    public String getCommand () {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public static void checkDescriptionIsValid(String description) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    public static void checkDateIsValid(String date) throws EmptyDateException {
        if (date.isEmpty()) {
            throw new EmptyDateException();
        }
    }

    public static String[] getDescriptionAndDate(String command, String description) throws EmptyDateException {
        String[] descriptionAndDate = new String[SPLIT_SIZE];
        switch(command) {
        case DEADLINE_COMMAND:
            descriptionAndDate = splitUserInput(description, DEADLINE_DELIMITER);
            break;
        case EVENT_COMMAND:
            descriptionAndDate = splitUserInput(description, EVENT_DELIMITER);
            break;
        }
        checkDateIsValid(descriptionAndDate[ARRAY_INDEX_FOR_DATE]);
        return descriptionAndDate;
    }

    public static void executeCommand(String command, String description, TaskManager taskManager)
            throws EmptyOrWrongInputException, EmptyDescriptionException, EmptyDateException {
        switch (command) {
        case TODO_COMMAND:
            checkDescriptionIsValid(description);
            taskManager.addToDo(description);
            break;
        case DEADLINE_COMMAND:
            checkDescriptionIsValid(description);
            taskManager.addDeadline(getDescriptionAndDate(command, description));
            break;
        case EVENT_COMMAND:
            checkDescriptionIsValid(description);
            taskManager.addEvent(getDescriptionAndDate(command, description));
            break;
        case DONE_COMMAND:
            int taskNumber = Integer.parseInt(description)-EXTRA_INDEX_FOR_ARRAY;
            taskManager.markAsDone(taskNumber);
            break;
        case EXIT_COMMAND:
            taskManager.printExitMessage();
            System.exit(0);
            break;
        case LIST_COMMAND:
            taskManager.listTasks();
            break;
        default:
            throw new EmptyOrWrongInputException();
        }
    }
}
