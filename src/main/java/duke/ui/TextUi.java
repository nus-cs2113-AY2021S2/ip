package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class TextUi {
    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** A tab prefix added to the beginning of lines printed by Duke TaskManager */
    private static final String LINE_PREFIX = "\t";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVLINE = "__________________________________________________________________\n";

    private static final String MESSAGE_USING_STORAGE_FILE = "Using storage file : %1$s";
    private static final String MESSAGE_WELCOME = "\tHello! I'm Ayanga, your personal task manager.\n" +
            "\tWhat can I note down for you?\n" +
            "\tTo add a todo item, simply write \"todo <DESCRIPTION>\".\n" +
            "\tTo add a deadline, write \"deadline <DESCRIPTION> /by <TIME>\".\n" +
            "\tTo add an event, write \"event <DESCRIPTION> /at <VENUE>\".\n" +
            "\tSay \"list\" and I will display your tasks.\n" +
            "\tWrite \"done <NUMBER OF TASK>\" or \"delete <NUMBER OF TASK>\"to mark complete or delete a task.\n" +
            "\tWave \"bye\" to me if you don't need me for now.\n";
    private static final String MESSAGE_GOODBYE = "\tBye. Hope you have done your work next time I see you!\n" +
            "\tAh, and also remember to take care of yourself and sleep early :)\n";

//    /** Format of indexed list item */
//    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Reads the text entered by the user.
     * Ignores empty and pure whitespace.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        return fullInputLine;
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void showWelcomeMessage() {
        showToUser(
                DIVLINE,
                MESSAGE_WELCOME,
                DIVLINE);
    }

    public void showGoodbyeMessage() {
        showToUser(
                DIVLINE,
                MESSAGE_GOODBYE, 
                DIVLINE);
    }

    public void showError(String message) {
        showToUser(message);
    }

    public void showLine() {
        showToUser(DIVLINE);
    }

    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(LINE_PREFIX + m.replace("\n", LS));
        }
    }

    public void showLoadingError() {
        showToUser("File loading error.");
    }

    /**
     * Shows the result of a command execution to the user. Includes additional formatting to demarcate different
     * command execution segments.
     */
//    public void showResultToUser(CommandResult result) {
//        final Optional<List<? extends ReadOnlyPerson>> resultPersons = result.getRelevantPersons();
//        if (resultPersons.isPresent()) {
//            showPersonListView(resultPersons.get());
//        }
//        showToUser(result.feedbackToUser, DIVLINE);
//    }

    /**
     * Shows a list of persons to the user, formatted as an indexed list.
     * Private contact details are hidden.
     */
//    private void showPersonListView(List<? extends ReadOnlyPerson> persons) {
//        final List<String> formattedPersons = new ArrayList<>();
//        for (ReadOnlyPerson person : persons) {
//            formattedPersons.add(person.getAsTextHidePrivate());
//        }
//        showToUserAsIndexedList(formattedPersons);
//    }

    /** Shows a list of strings to the user, formatted as an indexed list. */
//    private void showToUserAsIndexedList(List<String> list) {
//        showToUser(getIndexedListForViewing(list));
//    }

    /** Formats a list of strings as a viewable indexed list. */
//    private static String getIndexedListForViewing(List<String> listItems) {
//        final StringBuilder formatted = new StringBuilder();
//        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
//        for (String listItem : listItems) {
//            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
//            displayIndex++;
//        }
//        return formatted.toString();
//    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
//    private static String getIndexedListItem(int visibleIndex, String listItem) {
//        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
//    }
}
