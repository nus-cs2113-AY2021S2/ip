package ui;

public class Ui {


    public final String DIVIDER = "____________________________________________________________";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String INTRO_MESSAGE = " Hello! I'm Duke\n" + " What can I do for you?";

    /**
     * Organises Welcome message into correct format before calling showToUser to print
     */
    public void showIntroMessage(){
        showToUser(DIVIDER, INTRO_MESSAGE, DIVIDER);
    }

    /**
     * Organises Exit message into correct format before calling showToUser to print
     */
    public void showExitMessage() {
        showToUser(DIVIDER, EXIT_MESSAGE, DIVIDER);
    }

    /**
     * Prints any String given into the command line interface
     *
     * @param message message that the bot wants to send to the user
     */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

}
