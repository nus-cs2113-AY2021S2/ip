package duke;

import java.util.Scanner;

public class Ui implements AutoCloseable {
    public static final String LONG_LINE = "------------------------------------------------------------";

    // Default indentation is 8 whitespaces
    public static final char DEFAULT_INDENT_CHARACTER = '\t';
    public static final int DEFAULT_INDENT_COUNT = 1;
    // Used for internal 2nd-level indentation (e.g. print task)
    public static final String INTERNAL_INDENT = "\t";

    public static final String LOCALE_CLASS_PREFIX = "duke.locale";
    public static final String DEFAULT_LOCALE = "English";
    public static final Class<?> DEFAULT_LOCALE_CLASS = duke.locale.English.class;

    protected String indent;
    protected Scanner scanner;
    protected Class<?> locale;

    public Ui() {
        this(DEFAULT_INDENT_CHARACTER, DEFAULT_INDENT_COUNT, DEFAULT_LOCALE);
    }

    public Ui(char indentCharacter, int indentCount, String locale) {
        indent = new String(new char[indentCount]).replace('\0', indentCharacter);
        scanner = new Scanner(System.in);

        // Load locale from class
        String className = LOCALE_CLASS_PREFIX + locale;
        try {
            this.locale = Class.forName(className);
        } catch (ClassNotFoundException e) {
            // Cannot load a locale class, fall back to English
            this.locale = DEFAULT_LOCALE_CLASS;
        }
    }

    protected String getLocaleText(String key) {
        try {
            return (String) locale.getDeclaredField(key).get(null);
        } catch (Exception e) {
            // If it fails, fall back to default locale
            try {
                return (String) DEFAULT_LOCALE_CLASS.getDeclaredField(key).get(null);
            } catch (Exception ex) {
                // Critical error - we cannot even get this key from the default locale
                // Print out the error and return an empty string
                printException(ex);
                return "";
            }
        }
    }

    @Override
    public void close() {
        scanner.close();
        scanner = null;
    }

    public String read() {
        if (scanner == null || !scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine();
    }

    public void print(String text, Object ... args) {
        // Pass to String.format to format the text using varargs provided
        String[] lines = String.format(text, args).split("\n");
        for (String line : lines) {
            System.out.println(indent + line);
        }
    }

    // Print a message for a successful insertion of task
    public void printNewTask(TaskList tasks) {
        int size = tasks.size();
        print(getLocaleText("NEW_TASK"), INTERNAL_INDENT + tasks.get(size - 1), size);
    }

    public void printTaskList(TaskList tasks) {
        printTaskList(tasks, "");
    }

    public void printTaskList(TaskList tasks, DateTime dateTime) {
        printTaskList(tasks, getLocaleText("TASK_LIST_AT_BY") + dateTime);
    }

    public void printTaskList(TaskList tasks, String additionalText) {
        if (tasks.size() == 0) {
            print(getLocaleText("TASK_LIST_EMPTY"), additionalText);
            return;
        }
        print(getLocaleText("TASK_LIST"), additionalText);
        for (int i = 0; i < tasks.size(); i += 1) {
            print("%d.%s%s", i + 1, INTERNAL_INDENT, tasks.get(i));
        }
    }

    public void printLine() {
        print(LONG_LINE);
    }

    public void printSaveException(String filepath, Exception e) {
        printException(e);
        print(getLocaleText("SAVE_EXCEPTION"), filepath);
        printLine();
    }

    public void printException(Exception e) {
        print(getLocaleText("EXCEPTION"), e);
    }

    public void printWelcome() {
        print(getLocaleText("GREETING"), new DateTime("31/01/2021 23:59"));
        printLine();
    }

    public void printGoodbye() {
        print(getLocaleText("FAREWELL"));
    }
}
