public class CommandManager {

    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DONE_COMMAND = "done";

    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";
    private static final String COMMAND_AND_ARG_DELIMITER = "\\s+";

    public static String[] splitUserInput(String userInput, String delimiter) {
        final String[] split = userInput.trim().split(delimiter, 2);
        return split.length == 2 ? split : new String[] { split[0] , "" }; // else case: no parameters
    }

    public static void executeCommand(String userInput, TaskManager taskManager) {
        String[] splitCommandAndArg = splitUserInput(userInput, COMMAND_AND_ARG_DELIMITER);
        String command = splitCommandAndArg[0];
        String argument = splitCommandAndArg[1];
        String description, deadline, eventDate;
        if (!argument.equals("")) {
            switch (command) {
            case TODO_COMMAND:
                taskManager.toDo(argument);
                break;
            case DEADLINE_COMMAND:
                String[] descriptionAndDeadline = splitUserInput(argument, DEADLINE_DELIMITER);
                description = descriptionAndDeadline[0];
                deadline = descriptionAndDeadline[1];
                taskManager.deadLine(description,deadline);
                break;
            case EVENT_COMMAND:
                String[] descriptionAndEvent = splitUserInput(argument, EVENT_DELIMITER);
                description = descriptionAndEvent[0];
                eventDate = descriptionAndEvent[1];
                taskManager.event(description,eventDate);
                break;
            case DONE_COMMAND:
                int taskNumber = Integer.parseInt(argument);
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
