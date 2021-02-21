import java.util.Scanner;

public class Ui {
    protected static int num_of_goals = 0;
    public static Scanner in = new Scanner(System.in);

    public static final int DONE_START = 5;
    public static final int TODO_START = 5;
    public static final int EVENT_START = 6;
    public static final int DEADLINE_START = 9;
    public static final int FIND_START =5;

    public TaskList tasks = new TaskList();

    public Ui() {
    }
    protected static String getString(Scanner in) {
        String user_input;
        user_input = in.nextLine();
        return user_input;

    }


    protected static void plusNumOfGoals() {
        num_of_goals++;
    }
}



