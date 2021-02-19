package common;

public class Constants {

    //Values
    public final int MAX_SIZE = 100;
    public final int INVALID_INDEX = -1;
    public final int MIN_SPLIT_SUCCESS_COUNT = 2;


    //Miscellaneous Strings
    public final String LINE = "───────────────────────────────────────────────────────────────────────────";
    public final String LOGO = "                o                     o\n" +
            "                oooo               oooo\n" +
            "                ooooooo         ooooooo\n" +
            "                   ooooooo   ooooooo\n" +
            "                      ooooooooooo\n" +
            "                         ooooo\n" +
            "  ooooooooooooooooooooooooooooooooooooooooooooooooooo\n" +
            "  ooooooooooooooooooooooooooooooooooooooooooooooooooo\n" +
            "\n" +
            "     ooo    oooooooooo           oooooooooo\n" +
            "   ooo   oooooooooooooooo     oooooooooooooooo\n" +
            "        ooooo        ooooo   ooooo        ooooo\n" +
            "       oooo            ooooooooo            oooo\n" +
            "        ooooo        ooooo   ooooo        ooooo\n" +
            "         oooooooooooooooo     oooooooooooooooo\n" +
            "            oooooooooo           oooooooooo\n";
    public final String KEYWORD_AT = " /at ";
    public final String KEYWORD_BY = " /by ";
    public final String COMMAND_TODO = "todo ";
    public final String COMMAND_DEADLINE = "deadline ";
    public final String COMMAND_EVENT = "event ";


    //Messages
    public final String MESSAGE_WELCOME = LOGO + "\n" + LINE + "\n"
            + "8K: Hi there! 8K here.\n"
            + "    How can I help you?\n"
            + "    (Enter \"help\" to view all commands.)\n" + LINE;
    public final String MESSAGE_BYE = LINE + "\n"
            + "8K: Bye bye! Have a nice day.\n" + LINE;
    public final String MESSAGE_HELP = LINE + "\n"
            + "8K: bye - Exit programme.\n"
            + "    help - Show list of commands.\n"
            + "    list - Show list of saved values.\n"
            + "    todo <name> - Creates new todo task.\n"
            + "    event <name> /at <info> - Creates new event.\n"
            + "    deadline <name> /by <date> - Creates new deadline.\n"
            + "    delete <indices> - Deletes selected tasks.\n"
            + "    done n - Mark nth item as done.\n"
            + "    undo n - Mark nth item as not done.\n" + LINE;
    public final String MESSAGE_MARK_DONE =  LINE + "\nMarked as done:";
    public final String MESSAGE_MARK_UNDONE = LINE + "\nMarked as undone:";
    public final String MESSAGE_DELETED = LINE + "\nDeleted:";
    public final String MESSAGE_FILE_LOADED = LINE + "\n"
            + "File loaded successfully.\n" + LINE;


    //Errors
    public final String MESSAGE_UNRECOGNIZED_COMMAND = LINE + "\n"
            + "8K: Sorry. I do not understand.\n" + LINE;
    public final String MESSAGE_LIST_FULL = LINE + "\n"
            + "8K: List is full." + "\n" + LINE;
    public final String MESSAGE_EMPTY_LIST = "<< List is empty >>\n" + LINE;
    public final String MESSAGE_MISSING_AT_KEYWORD = LINE + "\n"
            + "8K: Please follow the format \"event <name> /at <info>\" to create event.\n" + LINE;
    public final String MESSAGE_MISSING_BY_KEYWORD = LINE + "\n"
            + "8K: Please follow the format \"deadline <name> /by <date>\" to create deadline.\n" + LINE;
    public final String MESSAGE_SAVE_ERROR = LINE + "\n"
            + "8K: Failed to save.\n" + LINE;
    public final String MESSAGE_LOAD_ERROR = LINE + "\n"
            + "8K: Failed to load.\n" + LINE;
    public final String MESSAGE_FILE_NOT_FOUND = LINE + "\n"
            + "File not found. New file will be created.\n" + LINE;
}
