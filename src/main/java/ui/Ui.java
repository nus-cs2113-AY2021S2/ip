package ui;

public class Ui {


    public final String DIVIDER = "____________________________________________________________";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String INTRO_MESSAGE = " Hello! I'm Duke\n" + " What can I do for you?";

    public void showIntroMessage(){
        showToUser(DIVIDER, INTRO_MESSAGE, DIVIDER);
    }

    public void showExitMessage() {
        showToUser(DIVIDER, EXIT_MESSAGE, DIVIDER);
    }

    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

}
