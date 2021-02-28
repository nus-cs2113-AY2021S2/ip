package duke.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Implements the user interface functionalities.
 *
 * @author Leonardo Irvin Pratama
 */
public class Ui {
    final String LOGO = "\n" +
            " .----------------.  .----------------. \n" +
            "| .--------------. || .--------------. |\n" +
            "| |    ______    | || |        __    | |\n" +
            "| |  .' ____ '.  | || |    _  / /    | |\n" +
            "| |  | (____) |  | || |   (_)/ /     | |\n" +
            "| |  '_.____. |  | || |     / / _    | |\n" +
            "| |  | \\____| |  | || |    / / (_)   | |\n" +
            "| |   \\______,'  | || |   /_/        | |\n" +
            "| |              | || |              | |\n" +
            "| '--------------' || '--------------' |\n" +
            " '----------------'  '----------------' ";
    final String DECO_LINE = "____________________________________________________________";
    final String HELLO_MESSAGE = " Hello! I'm 9%.";
    final String ASK_MESSAGE = " What can I do for you? \n(todo/deadline/event/list/find/done/remove/bye)";
    final String BYE_MESSAGE = " Bye. Hope to see you again soon!";
    private ArrayList<String> accumulatedResponses;

    /**
     * Creates a new Ui object.
     */
    public Ui() {
        this.accumulatedResponses = new ArrayList<>();
    }

    /**
     * Returns welcome message.
     *
     * @return Welcome message.
     */
    public void getGreetings() {
        showToUser(LOGO + "\n" + DECO_LINE + "\n" + HELLO_MESSAGE + "\n" + ASK_MESSAGE + "\n" + DECO_LINE);
    }

    /**
     * Returns line separator.
     *
     * @return Line separator.
     */
    public void showLine() {
        showToUser(DECO_LINE);
    }

    /**
     * Returns goodbye message.
     *
     * @return Goodbye message.
     */
    public void showGoodbyeUser() {
        showToUser(BYE_MESSAGE);
    }

    /**
     * Returns loading error message.
     *
     * @return Loading error message.
     */
    public void showLoadingError() {
        showToUser(" Failed to load saved data!");
    }

    /**
     * Prints messages line by line.
     *
     * @param messages Messages to be printed.
     */
    public void printNicely(String... messages) {
        Arrays.stream(messages).forEach(message -> System.out.println(message));
    }

    /**
     * Reads current input from user.
     *
     * @return String indicating current input.
     */
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Accumulates response from various functionalities.
     *
     * @param responses Responses to be accumulated.
     */
    public void accumulateResponses(String... responses) {
        Arrays.stream(responses).forEachOrdered(response -> accumulatedResponses.add(response));
    }

    /**
     * Returns the accumulated responses and reset.
     *
     * @return Accumulated responses.
     */
    public void getResponses() {
        StringBuilder sb = new StringBuilder();
        accumulatedResponses.stream().limit(accumulatedResponses.size() - 1)
                .forEachOrdered(response -> sb.append(response));
        sb.append(accumulatedResponses.get(accumulatedResponses.size() - 1));
        accumulatedResponses = new ArrayList<>();
        showToUser(sb.toString());
    }

    /**
     * Shows message to user.
     *
     * @param message Message to show.
     */
    public void showToUser(String message) {
        for (String line : message.split(System.lineSeparator())) {
            printNicely(line);
        }
    }
}
