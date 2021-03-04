package ui;

import command.CommandResult;
import task.Task;

import java.util.Iterator;
import java.util.Scanner;

/**
 * A class for getting user input and displaying messages to user
 */
public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_SEPARATOR = "    ____________________________________________________________\n";
    private static final String PRE_SPACE = "    ";

    private Scanner sc;

    public Ui(){
        sc = new Scanner(System.in);
    }

    public void showWelcomeMessage(){
        System.out.println(PRE_SPACE + "Hello from\n" + LOGO);
        System.out.print(LINE_SEPARATOR +
                 PRE_SPACE + "Hello! I'm Duke\n" + PRE_SPACE +
                "What can I do for you?\n" +
                LINE_SEPARATOR);
    }

    public String getUserInput(){
        return sc.nextLine();
    }

    /**
     * Display the feedback for a command
     * @param result
     */
    public void printFeedback(CommandResult result){
        System.out.print(LINE_SEPARATOR);
        System.out.println(PRE_SPACE + result.getFeedback());
        if(result.getRelevantTasks() == null){
            System.out.print(LINE_SEPARATOR);
            return;
        }
        Iterator<Task> taskIterator = result.getRelevantTasks().getIterator();
        int count = 1;
        while(taskIterator.hasNext()){
            Task currentTask = taskIterator.next();
            if(currentTask != null){
                System.out.println(PRE_SPACE + count + "." + currentTask.getTaskInfo());
            }
            count++;
        }
        System.out.print(LINE_SEPARATOR);
    }

    /**
     * Display error message when the file can't be accessed
     */
    public void showFileErrorMessage(){
        System.out.print(PRE_SPACE + "The file can't be loaded, please check" +
                "and rebuild Duke\n" + LINE_SEPARATOR);
    }

    /**
     * Display error message when the file can't be written to
     */
    public void showStoreIssueMessage(){
        System.out.print(PRE_SPACE + "The tasks can't be correctly stored back to " +
                "the file, please check the setting and try again.\n" + LINE_SEPARATOR);
    }

    public void showGoodbyeMessage(){
        System.out.print(LINE_SEPARATOR +
                "    Bye. Hope to see you again soon!" +
                "\n" + LINE_SEPARATOR);
    }
}
