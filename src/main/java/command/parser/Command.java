package command.parser;

/**
 * Represents commands that the user will input
 */
public abstract class Command {
    public static final int NUMBER_OF_COMMAND_ARGUMENTS = 2;
    public static final int COMMAND_KEYWORD_POSITION = 0;
    public static final int EMPTY = 0;
    public static final int WRONG_INDEX = -1;
    public static final int DESCRIPTION_INDEX_IN_COMMANDS = 1;
}
