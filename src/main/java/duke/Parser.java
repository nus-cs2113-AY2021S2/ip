package duke;

import duke.taskexceptions.EmptyTaskDateException;
import duke.taskexceptions.NoTaskDateException;
import duke.taskexceptions.NoTaskNameException;
import duke.taskexceptions.TaskDateFormatException;
import duke.taskexceptions.KeywordFormatException;

public class Parser {

    public static String commandWord;

    public static String taskInputString; //contains taskName and taskDate (from user's input)
    public static String taskName;
    public static String taskDate;

    public static int index; //contains index of task to be 'marked as done' OR 'deleted'
    public static String keyword; //tasks with 'keyword' are to be found

    public Parser() {

    }

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
        //OR: remove task from list of tasks
        else if (input.startsWith("delete")) {
            commandWord = "delete";
            parseDeleteCommand(input);
        }
        //OR: mark current task as 'done' & outputs the taskType,taskStatus,taskName(and taskDate):
        else if (input.startsWith("done")) {
            commandWord = "done";
            parseDoneCommand(input);
        }
        //OR: lists all the user's current tasks in the format of taskType,taskStatus,taskName(and taskDate):
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
        //OR: deal with invalid command
        else {
            commandWord = "random";
        };
    }

    private static void parseAddToDoCommand (String input) throws NoTaskNameException {
        separateTypeOfTaskAndTaskInputString(input);
        taskName = taskInputString;
    }

    private static void parseAddDeadlineCommand(String input) throws NoTaskNameException, NoTaskDateException, EmptyTaskDateException, TaskDateFormatException {
        separateTypeOfTaskAndTaskInputString(input);
        splitTaskNameAndDate(taskInputString);
    }

    private static void parseAddEventCommand(String input) throws NoTaskNameException, NoTaskDateException, EmptyTaskDateException, TaskDateFormatException {
        separateTypeOfTaskAndTaskInputString(input);
        splitTaskNameAndDate(taskInputString);

    }

    private static void parseDeleteCommand(String input) throws NumberFormatException {
        String[] commandAndTaskNumber = input.split(" ");
        if (commandAndTaskNumber.length < 2) {
            throw new NumberFormatException(); //throws NumberFormatException() when user does not input a number after word 'done'
        }
        index = Integer.parseInt(commandAndTaskNumber[1]) - 1; //obtain index from task number(which starts from 1)
    }

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
     * Removes taskType from user's entire input string
     * Stores remaining string (with taskName and taskDate combined) in static variable taskInputString
     *
     * throws exception if user string does not contain proper taskName (taskName, taskDate etc.)
     * @param input - entire input string of the user, made of taskType + taskName + taskDate
     */
    public static void separateTypeOfTaskAndTaskInputString(String input) throws NoTaskNameException {
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
     * Splits it into two parts, then stores into TaskName
     * and TaskDateString of the task
     *
     * Splits TaskDateString into 2 parts, complement word "by:" or "at:" & taskDate (further split by " ")
     *
     * TaskDateFormatException() - TaskDateString is written in an incorrect format
     * NoTaskDateException() - no date after '/at' or '/by'
     * EmptyTaskDateException() - empty date after '/at' or '/by'
     *
     * End Results: TaskName and TaskDate
     * @param taskInput - essentially taskInputString, which does not include taskType
     */
    public static void splitTaskNameAndDate(String taskInput) throws TaskDateFormatException, NoTaskDateException, EmptyTaskDateException {
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
