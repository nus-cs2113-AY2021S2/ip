package duke.constants;

/**
 * Container for constant strings to be printed out in UI
 */

public class UiStrings {
    // Messages for announcing error
    public static final String ENTER_HELP_FOR_LIST_OF_COMMANDS = "Enter \"help\" for a list of available commands and format\n";
    public static final String JOB_NUMBER_TOO_SMALL = "Enter a valid job number. Use the list command to view your current tasks.";
    public static final String JOB_NUMBER_TOO_BIG = "You don't have that many jobs! Use the list command to view your current tasks.";
    public static final String LIST_FULL_WARNING = "List is full!" +'\n'+"Use the \"list\" command to view your tasks." + '\n';

    // Messages for ... todo
    public static final String PROMPT_ENTER_BYE_TO_EXIT = "To exit, enter \"bye\"";
    
    
    // Command formats for help message
    public static final String LIST_COMMAND_FORMAT = "LIST - \n" + "FORMAT: list";
    public static final String DONE_COMMAND_FORMAT = "DONE - \n" + "FORMAT: done [(int) number]";
    public static final String TODO_COMMAND_FORMAT = "TODO - \n" + "FORMAT: todo [(str) description]";
    public static final String DEADLINE_COMMAND_FORMAT = "DEADLINE - \n" + "FORMAT: deadline [(str) description] /by [(str) deadline]";
    public static final String EVENT_COMMAND_FORMAT = "EVENT - \n" + "FORMAT: event [(str) description] /at [(str) time]";
    public static final String DELETE_COMMAND_FORMAT = "DELETE - \n" + "FORMAT: delete [(int) index]";
    
    // Output formatting
    public static final String STRAIGHT_LINE = "____________________________________________________________";
    public static final String SHORT_LINE = "-------------";
    public static final String INDENT = "   ";
    
    // Messages for program start and exit
    public static final String ALLO_MESSAGE = "Hello I'm Diuk!\n" + "What would you like to do today?\n";
    public static final String BYE_MESSAGE = "Bye! Hit me up if you feel like being productive again ;)\n";
    
    
}
