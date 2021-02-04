public class CommandManager {

    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DONE_COMMAND = "done";

    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";
    private static final String WHITESPACE_DELIMITER = "\\s+";

    private static int SIZE_OF_SENTENCE_COMPONENT = 2;
    private static int FIRST_COMPONENT = 0;
    private static int SECOND_COMPONENT = 1;

    public static String[] splitUserInput(String userInput, String delimiter) {
        final String[] commandAndDescription = userInput.trim().split(delimiter, SIZE_OF_SENTENCE_COMPONENT);
        boolean isValidCommandAndDescription = (commandAndDescription.length == SIZE_OF_SENTENCE_COMPONENT);
        if (isValidCommandAndDescription) {
            return commandAndDescription;
        } else {
            String[] commandAndEmptyDescription = new String []{commandAndDescription[FIRST_COMPONENT] ,""};
            return commandAndEmptyDescription;
        }
        //return words.length == SIZE_OF_SENTENCE_COMPONENT ? words : new String[] { words[FIRST_COMPONENT] , "" }; // else case: no parameters
    }

    public static String[] getTaskAndDate (String command, String description) {
        String[] taskAndDate = new String[SIZE_OF_SENTENCE_COMPONENT];
        switch(command) {
        case DEADLINE_COMMAND:
            taskAndDate = splitUserInput(description, DEADLINE_DELIMITER);
            break;
        case EVENT_COMMAND:
            taskAndDate = splitUserInput(description, EVENT_DELIMITER);
            break;
        }
        return taskAndDate;
    }

    public static void executeCommand(String userInput, TaskManager taskManager) {
        String[] commandAndDescription = splitUserInput(userInput, WHITESPACE_DELIMITER);
        String command = commandAndDescription[FIRST_COMPONENT];
        String description = commandAndDescription[SECOND_COMPONENT];
        boolean isValidDescription = !description.isEmpty();
        if (isValidDescription) {
            switch (command) {
            case TODO_COMMAND:
                taskManager.toDo(description);
                break;
            case DEADLINE_COMMAND:
                taskManager.deadLine(getTaskAndDate(command,description));
                break;
            case EVENT_COMMAND:
                taskManager.event(getTaskAndDate(command,description));
                break;
            case DONE_COMMAND:
                int taskNumber = Integer.parseInt(description);
                taskManager.markAsDone(taskNumber);
                break;
            default:
                taskManager.echo(userInput);
                break;
            }
        } else {
            switch(command) {
            case EXIT_COMMAND:
                taskManager.printExitMessage();
                System.exit(0);
                break;
            case LIST_COMMAND:
                taskManager.listTasks();
                break;
            default:
                taskManager.echo(userInput);
                break;
            }
        }
    }

}
