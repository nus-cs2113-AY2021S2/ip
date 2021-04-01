package duke;

import duke.taskexceptions.EmptyTaskDateException;
import duke.taskexceptions.NoTaskDateException;
import duke.taskexceptions.NoTaskNameException;
import duke.taskexceptions.TaskDateFormatException;
import duke.taskexceptions.KeywordFormatException;

/**
 * Command Class: Contains methods for parsing and making sense of the User's command.
 */
public class Parser {

    public static String commandWord;

    public static String taskInputString; //contains taskName and taskDate (from user's input)
    public static String taskName;
    public static String taskDate;

    public static int index; //contains index of task to be 'marked as done' OR 'deleted'
    public static String keyword; //tasks with 'keyword' are to be found

    public Parser() {

    }

    /**
     * Parse each command differently based on the format of the command.
     * For all commands, parses into commandWord to help identify the type of command during execution.
     * Parses commands into taskName, taskDate or index according to the format of the command
     * for execution.
     *
     * @param input entire input of user.
     * @throws NoTaskNameException if user does not input the taskName.
     * @throws NoTaskDateException if user does not input the taskDate.
     * @throws EmptyTaskDateException if user does not input the taskDate.
     * @throws TaskDateFormatException if user input the portion of command after taskName incorrectly.
     * @throws NumberFormatException if user does not input a TaskNumber(index).
     */
    public static void parse (String input) throws NoTaskNameException, NoTaskDateException, EmptyTaskDateException, TaskDateFormatException, NumberFormatException, KeywordFormatException {

        if (input.startsWith("todo")) {
            commandWord = "todo";
            parseAddToDoCommand(input);

        } else if (input.startsWith("deadline")) {
            commandWord = "deadline";
            parseAddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            commandWord = "event";
            parseAddEventCommand(input);
        }
        else if (input.startsWith("delete")) {
            commandWord = "delete";
            parseDeleteCommand(input);
        }
        else if (input.startsWith("done")) {
            commandWord = "done";
            parseDoneCommand(input);
        }
        else if (input.startsWith("list")) {
            commandWord = "list";
        }
        else if (input.startsWith("find")) {
            commandWord = "find";
            parseFindCommand(input);
        }
         else if (input.startsWith("bye")) {
            commandWord = "bye";
        }
        else {
            commandWord = "random";
        };
    }

    /**
     * Takes in User's input string and splits into 2 parts, the 'todo' command & the taskName.
     *
     * @param input entire input string of the user, made of taskType('todo') + taskName.
     * @throws NoTaskNameException if user does not input a description of the task.
     */
    private static void parseAddToDoCommand (String input) throws NoTaskNameException {
        separateTypeOfTaskAndTaskInputString(input);
        taskName = taskInputString;
    }

    /**
     * Takes in User's input string and splits into 3 parts, the 'deadline' command, the taskName & the taskDate.
     *
     * @param input entire input string of the user, made of taskType('deadline') + taskName + taskDate.
     * @throws NoTaskNameException if user does not input the taskName.
     * @throws NoTaskDateException if user does not input the taskDate.
     * @throws EmptyTaskDateException if user does not input the taskDate.
     * @throws TaskDateFormatException if user input the portion of command after taskName incorrectly.
     */
    private static void parseAddDeadlineCommand(String input) throws NoTaskNameException, NoTaskDateException, EmptyTaskDateException, TaskDateFormatException {
        separateTypeOfTaskAndTaskInputString(input);
        splitTaskNameAndDate(taskInputString);
    }

    /**
     * Takes in User's input string and splits into 3 parts, the 'event' command, the taskName & the taskDate.
     *
     * @param input entire input string of the user, made of taskType('event') + taskName + taskDate.
     * @throws NoTaskNameException if user does not input the taskName.
     * @throws NoTaskDateException if user does not input the taskDate.
     * @throws EmptyTaskDateException if user does not input the taskDate.
     * @throws TaskDateFormatException if user input the portion of command after taskName incorrectly.
     */
    private static void parseAddEventCommand(String input) throws NoTaskNameException, NoTaskDateException, EmptyTaskDateException, TaskDateFormatException {
        separateTypeOfTaskAndTaskInputString(input);
        splitTaskNameAndDate(taskInputString);

    }

    /**
     * Takes in User's input string and splits into 2 parts, the 'delete' command & the taskNumber.
     * Index is derived from taskNumber, which tells the position of task in current list.
     *
     * @param input entire input string of the user, made up of 'delete' + taskNumber.
     * @throws NumberFormatException if user does not input a TaskNumber(index).
     */
    private static void parseDeleteCommand(String input) throws NumberFormatException {
        String[] commandAndTaskNumber = input.split(" ");
        if (commandAndTaskNumber.length < 2) {
            throw new NumberFormatException(); //throws NumberFormatException() when user does not input a number after word 'done'
        }
        index = Integer.parseInt(commandAndTaskNumber[1]) - 1; //obtain index from task number(which starts from 1)
    }

    /**
     * Takes in User's input string and splits into 2 parts, the 'done' command & the taskNumber.
     * Index is derived from taskNumber, which tells the position of task in current list.
     *
     * @param input entire input string of the user, made up of 'done' + taskNumber.
     * @throws NumberFormatException if user does not input a TaskNumber(index).
     */
    private static void parseDoneCommand(String input) throws NumberFormatException {
        String[] commandAndTaskNumber = input.split(" ");
        if (commandAndTaskNumber.length < 2) {
            throw new NumberFormatException(); //throws NumberFormatException() when user does not input a number after word 'done'
        }
        index = Integer.parseInt(commandAndTaskNumber[1]) - 1; //obtain index from task number(which starts from 1)
    }

    private static void parseFindCommand(String input) throws KeywordFormatException {
        //format: find + keyword
        String[] commandAndKeyword= input.split(" ");
        if (commandAndKeyword.length < 2) {
            throw new KeywordFormatException(); //throws KeywordFormatException() when user does not input a keyword after 'find' command
        }
        keyword = commandAndKeyword[1]; //tasks with 'keyword' are to be found
    }

    /**
     * Complement all 'todo','deadline' and 'event' commands.
     * Removes taskType from user's entire input string
     * and stores remaining string (with taskName and taskDate combined) in static variable taskInputString.
     *
     * @param input entire input string of the user, made of taskType + taskName + taskDate.
     * @throws NoTaskNameException if user does not input the taskName.
     */
    private static void separateTypeOfTaskAndTaskInputString(String input) throws NoTaskNameException {
        //find position between taskType and rest of task description:
        int taskInputStringPosition = input.indexOf(" ") + 1;
        taskInputString = input.substring(taskInputStringPosition);

        boolean hasNoTaskInputString = false;
        if(taskInputStringPosition == 0) {
            hasNoTaskInputString = true; //since input.indexOf(" ") returns -1 if no TaskName
        }

        if (hasNoTaskInputString || taskInputString.isBlank() || taskInputString.startsWith("/")) {
            throw new NoTaskNameException();
        }
    }

    /**
     * Takes in the remaining 'taskInputString' of the user's input
     * and splits it into two parts, the taskName and taskDate.
     *
     * taskDate is derived from taskDateString which includes the complement word 'at' or 'by'.
     *
     * @param taskInput - essentially taskInputString, which does not include taskType.
     * @throws NoTaskDateException if user does not input the taskDate.
     * @throws EmptyTaskDateException if user does not input the taskDate.
     * @throws TaskDateFormatException if user input the portion of command after taskName incorrectly.
     */
    private static void splitTaskNameAndDate(String taskInput) throws NoTaskDateException, EmptyTaskDateException, TaskDateFormatException {
        int beforeDatePosition = taskInput.indexOf("/");
        if (beforeDatePosition == -1) {
            throw new TaskDateFormatException(); //if no '/', asks user to change TaskDateString format
        }
        taskName = taskInput.substring(0, beforeDatePosition);

        int dateStringPosition = beforeDatePosition + 1;
        String taskDateString = taskInput.substring(dateStringPosition);

        String[] taskDateStringWord = taskDateString.split(" ");
        if (!(taskDateStringWord[0].equals("at") || taskDateStringWord[0].equals("by"))) {
            throw new TaskDateFormatException(); //if incorrect complement word in non-empty string after '/', asks user to change TaskDateString format
        }

        int datePosition = taskDateString.indexOf(" ") + 1;
        if (datePosition == 0) {
            throw new NoTaskDateException(); //if no date after '/at:' or '/by:', asks user to 'add date'
        }
        taskDate = taskDateString.substring(datePosition);
        if (taskDate.isBlank()) {
            throw new EmptyTaskDateException(); //if empty date after '/at:' or '/by:', asks user to 'add date'
        }
    }

}
