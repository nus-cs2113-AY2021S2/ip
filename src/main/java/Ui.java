import java.util.Scanner;

/**
 * Take in user input
 */
public class Ui {
    protected static int num_of_goals = 0;
    public static Scanner in = new Scanner(System.in);

    public static final int DONE_START = 5;
    public static final int TODO_START = 5;
    public static final int EVENT_START = 6;
    public static final int DEADLINE_START = 9;
    public static final int FIND_START =5;
    public TaskList tasks = new TaskList();

    /**
     * Constructor of the class
     */
    public Ui() {
    }

    /**
     * Take in user input and convert into string
     * @param in Scanner
     * @return string user input
     */
    protected static String getString(Scanner in) {
        String user_input;
        user_input = in.nextLine();
        return user_input;

    }

    /**
     * Increment of total number of tasks stored
     */
    protected static void plusNumOfGoals() {
        num_of_goals++;
    }
}



