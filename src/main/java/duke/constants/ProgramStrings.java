package duke.constants;

/**
 * Container for UI messages and string constants used throughout the program.
 */

public class ProgramStrings {
    /** Messages for program start and exit. */
    public static final String ALLO_MESSAGE = "Hello I'm Duke!\n" + "What would you like to do today?\n";
    public static final String BYE_MESSAGE = "Bye! Hit me up if you feel like being productive again ;)\n";
    
    /** Messages for {@code help} command. */
    public static final String LIST_COMMAND_FORMAT = "LIST - \n" + "FORMAT: list";
    public static final String DONE_COMMAND_FORMAT = "DONE - \n" + "FORMAT: done [(int) number]";
    public static final String TODO_COMMAND_FORMAT = "TODO - \n" + "FORMAT: todo [(str) description]";
    public static final String DEADLINE_COMMAND_FORMAT = "DEADLINE - \n" + "FORMAT: deadline [(str) description] /by [(str) time/*date]";
    public static final String EVENT_COMMAND_FORMAT = "EVENT - \n" + "FORMAT: event [(str) description] /at [(str) time/*date]";
    public static final String DELETE_COMMAND_FORMAT = "DELETE - \n" + "FORMAT: delete [(int) index]";
    public static final String FIND_COMMAND_FORMAT = "FIND - \n" + "FORMAT: find [(string) search keywords]";
    public static final String DATE_FORMAT = "Date FORMAT: dd-mm-yyyy";

    /** Messages for announcing user error. */
    public static final String ENTER_HELP_FOR_LIST_OF_COMMANDS = "Enter \"help\" for a list of available commands and format\n";
    public static final String JOB_NUMBER_TOO_SMALL = "Enter a valid job number. Use the list command to view your current tasks.";
    public static final String JOB_NUMBER_TOO_BIG = "You don't have that many jobs! Use the list command to view your current tasks.";
    public static final String LIST_FULL_WARNING = "List is full!" +'\n'+"Use the \"list\" command to view your tasks." + '\n';
    public static final String NO_MATCH_WARNING = "No tasks match your search query:";
    public static final String INVALID_DATE_FORMAT_WARNING = "Invalid date/ wrong date format. " + DATE_FORMAT;
    
    /** Messages to prompt user. */
    public static final String PROMPT_ENTER_BYE_TO_EXIT = "To exit, enter \"bye\"";
    public static final String PROMPT_TASK_DONE = "Congrats! You've completed: \n   ";
    
    /** String constants for Parser. */
    public static final String INPUT_DATE_FORMAT = "d-M-yyyy";
    public static final String OUTPUT_DATE_FORMAT = "d MMMM, yyyy";
    public static final String EVENT_DELIM = "/at";
    public static final String DEADLINE_DELIM = "/by";
    
    /** String constants intended for simple UI design. */
    public static final String STRAIGHT_LINE = "____________________________________________________________";
    public static final String SHORT_LINE = "-------------";
    public static final String INDENT = "   ";
}
