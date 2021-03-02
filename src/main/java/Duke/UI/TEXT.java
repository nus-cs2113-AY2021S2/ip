package Duke.UI;

import Duke.Duke;

public class TEXT extends Duke{

    public static final String LINE = "____________________________________________________________\n";
    public static final String LOGO =
            "\n██████   █████   ██████   ██████  ██ ███████ \n" +
                    "██   ██ ██   ██ ██       ██       ██ ██      \n" +
                    "██████  ███████ ██   ███ ██   ███ ██ █████   \n" +
                    "██   ██ ██   ██ ██    ██ ██    ██ ██ ██      \n" +
                    "██████  ██   ██  ██████   ██████  ██ ███████ \n" +
                    "                                            \n";
    public static final String GREETING = " Hello! I'm Duke\n" + " Type 'help' to see what i could do for you\n";
    public static final String ENDING = " Bye! Hope to see you again soon!\n";
    public static final String HELP_MENU = " Here's what I could do for you ^_^\n"
            + " help: print help menu\n"
            + " list: see saved tasks in the list\n"
            + " done: mark a task in the list as done\n"
            + " delete: delete a task from the list\n"
            + " date: search saved tasks on a specific day\n"
            + " find: search saved tasks by keywords\n"
            + " bye: exit Duke\n"
            + " how to add tasks into the list:\n"
            + " \ttodo-> format: todo [task]\n"
            + " \tevent-> format: event [task] / [time]\n"
            + " \tdeadline-> format: deadline [task] / [time]\n"
            + " \t[tip: enter date as YYYY-MM-DD to help duke understand better!]\n";
    public static final String TASK_ADDED = " Task added! ^_^";
    public static final String TASK_DELETED = " Yay! This task is deleted!";
    public static final String TASK_DONE = " Yay! This task is done!";
    public static final String TASK_EMPTY = " No task description :(\n Ahh what's the task here?";
    public static final String TASK_WITHOUT_TIME = " No time input :(";
    public static final String NO_INDEX = " No index input :(";
    public static final String ILLEGAL_INPUT = " Duke doesn't know what to do with the command D:";
    public static final String TASK_ALREADY_DONE = " The task is already done ^_^";
    public static final String YOU_DO_NOT_HAVE = " You don't have Task ";
    public static final String IN_YOUR_LIST = " in your list ^_^ ";
    public static final String DUKE_CANNOT_FIND = " Duke could find any tasks related to ";
    public static final String LIST_EMPTY = " List is empty :o";
    public static final String A_NEW_FILE = " A new file [";
    public static final String HAS_BEEN_CREATED = "] has been created! ^_^\n";
    public static final String IT_COULD_BE_FOUND = " It could be found at: ";
    public static final String READ_FILE = " Reading saved Task Lists from [";
    public static final String FILE_SAVED = " File saved!";
    public static final String KEYWORD_EMPTY = " What's the keyword here?";
    public static final String DATE_EMPTY = " What's the date here?";
    public static final String FILE_NAME = "/Duke.txt";
    public static final String USER_DIRECTORY = "user.dir";
    public static final String TASK_TYPE_DEADLINE = "[D]";
    public static final String TASK_TYPE_TODO = "[T]";
    public static final String TASK_TYPE_EVENT = "[E]";
    public static final String TRUE = "true";
    public static final String DIVIDER = "|";
    public static final String TIME_DIVIDER = "/";
    public static final String DONE_ICON = "X";
    public static final String SPACE = " ";
    public static final String LEFT_SQUARE_BRACKET = " [";
    public static final String RIGHT_SQUARE_BRACKET = "] ";
    public static final String DATE_FORMAT = "MMM d yyyy";
    public static final String DEADLINE_ICON = "[D]";
    public static final String EVENT_ICON = "[E]";
    public static final String TODO_ICON = "[T]";
    public static final String SEMICOLON = ": ";
}
