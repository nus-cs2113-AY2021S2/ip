package ui;

public class Ui {

    public static final String DIVIDER_LINE = "____________________________________________________________";
    public static final String INVALID_NUMBER = "OOPS!!! Invalid task number =/\n" + DIVIDER_LINE;
    public static final String UNKNOWN_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means =/\n" + DIVIDER_LINE;
    public static final String INVALID_TODO = "OOPS!!! Invalid todo description\n" + DIVIDER_LINE;
    public static final String INVALID_DEADLINE = "OOPS!!! Invalid deadline description\n" + DIVIDER_LINE;
    public static final String INVALID_EVENT = "OOPS!!! Invalid event description\n" + DIVIDER_LINE;
    public static final String LIST_EMPTY = "The list is currently empty!\n" + DIVIDER_LINE;
    public static final String LIST_FULL = "The list is currently full!\n" + DIVIDER_LINE;
    public static final String FILE_SAVED = "File has been saved!";
    public static final String SAVE_ERROR = "File is not able to be saved!\n" + DIVIDER_LINE;
    public static final String FILE_NOT_FOUND = "No save file found!\n" + DIVIDER_LINE;
    public static final String FILE_LOADED = "Save file has been loaded!\n" + DIVIDER_LINE;
    /**
     * Prints hello message
     */
    public static void hello() {
        System.out.println(DIVIDER_LINE);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke!!\n" + logo);
        System.out.println("What can I do for you?\n" + "List of commands: todo, deadline, event, done, bye");
        System.out.println(DIVIDER_LINE);
    }
    /**
     * Prints bye message
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER_LINE);
    }
}
