package Duke.UI;

public class Ui {
    private static final String VERSION = "Duke - Version 1.0";
    private static final String DIVIDER = "===================================================";
    private static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke\n" + "What can I do for you?\n";
    private static final String HELP = "You can type:\n"+
    "| List             | list                    |list                              |\n"+
    "| Mark As Done     | done [index]            |done 1                            |\n"+
    "| Delete Task      |delete [index]           |delete 1                          |\n"+
    "| Done Task        |done [index]             |done 1                            |\n"+
    "| Find With Keyword|find [keyword]           |find football                  |\n"+
    "| Add Deadline Task|deadline [task]/by [time]|deadline book update/by 2021-01-03|\n"+
    "| Add Event Task   |event [task]/at [time]   |event FOOD delivery/at 2021-01-01 |\n"+
    "| Add Todo Task    |todo [task]              |todo upgrade game                 |\n"+
    "| Exit and save    |bye                      |bye                               |";

    /***
     *  Show welcome message
     ***/
    public static void showWelcomeMessage() {
        showToUser(DIVIDER, DIVIDER, VERSION, LOGO, GREETING, DIVIDER);
    }

    /***
     *  Show users information one line by one line
     ***/
    public static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    /***
     *  Repeat users command after user input
     ***/
    public static void echoUserCommand(String userCommand) {
        showToUser("[Command entered:" + userCommand + "]", DIVIDER);
    }

    public static void showHelpMenu(){
        Ui.showToUser(HELP);
    }


}
