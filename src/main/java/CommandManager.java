public class CommandManager {

    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DONE_COMMAND = "done";

    private static final String EMPTY = "";

    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";
    private static final String WHITESPACE_DELIMITER = "\\s+";

    private static final int SIZE_LIMIT = 2;
    private static final int FIRST_WORD = 0;
    private static final int SECOND_WORD = 1;

    private static String command = EMPTY;
    private static String description = EMPTY;

    public static void getCommandAndDescription(String userInput) {
        String [] words = splitUserInput(userInput, WHITESPACE_DELIMITER);
        command = getCommand(words);
        description = getDescription(words);
    }

    public static String getCommand(String[] words) {
        return words[FIRST_WORD];
    }

    public static String getDescription(String[] words) {
        return words[SECOND_WORD];
    }

    public static String[] splitUserInput(String userInput, String delimiter) {
        String[] commandAndDescription = userInput.trim().split(delimiter, SIZE_LIMIT);
        boolean isValidCommandAndDescription = (commandAndDescription.length == SIZE_LIMIT);
        if (isValidCommandAndDescription) {
            return commandAndDescription;
        } else {
            String[] commandAndEmptyDescription = new String []
                    {commandAndDescription[FIRST_WORD] ,EMPTY};
            return commandAndEmptyDescription;
        }
    }

    public static String[] getTaskAndDate () {
        String[] taskAndDate = new String[SIZE_LIMIT];
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
        getCommandAndDescription(userInput);
        boolean isValidDescription = !description.isEmpty();
        if (isValidDescription) {
            switch (command) {
            case TODO_COMMAND:
                taskManager.toDo(description);
                break;
            case DEADLINE_COMMAND:
                taskManager.deadLine(getTaskAndDate());
                break;
            case EVENT_COMMAND:
                taskManager.event(getTaskAndDate());
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
