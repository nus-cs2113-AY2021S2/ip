package duke;

import commands.Command;
import commands.CommandResult;

public class Ui {
    private final String dividers = "____________________________________________________________\n" +
            "%s" + "\n" +
            "____________________________________________________________\n";

    public Ui(){
    }

    public void showResult(CommandResult output){
        String toPrint = String.format(dividers,output.feedbackToUser);
        System.out.println(toPrint);
    }

    public void showResult(String output){
        String toPrint = String.format(dividers,output);
        System.out.println(toPrint);
    }
    public void startupUi(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello from\n" + logo;
        String welcome = " Hello! I'm Duke\n" +
                        " What can I do for you?";
        showResult(greeting);
        showResult(welcome);

    }
}
