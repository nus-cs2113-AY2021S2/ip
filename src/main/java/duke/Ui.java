package duke;

import duke.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showHello() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("Hello! I'm duke.Duke, your truly daily helper");
        System.out.println("What can I do for you?");
        showLine();

    }

    public void showHelp() {
        showLine();
        System.out.println("User Guide: ");
        System.out.println("1. todo ...");
        System.out.println("2. event ... /at ...");
        System.out.println("3. deadline ... /by ...");
        System.out.println("4. done [index]");
        System.out.println("5. delete [index]");
        System.out.println("6. list");
        System.out.println("7. bye");
        System.out.println("8. help");
        showLine();

    }

    public void showExecuteResult(String result) {
        showLine();
        System.out.println(result);
        showLine();
    }

    public void showBye() {
        showExecuteResult("Bye. Hope to see you again soon!");
    }

    public void showAddResult(Task t, int num) {
        showExecuteResult("Got it. I've added this task:\n" + t + "\nNow you have " + num + " tasks in the list.");
    }

    public void showDoneResult(Task t) {
        showExecuteResult("Nice! I've marked this task as done:\n" + t);
    }

    public void showDeleteResult(Task t, int num) {
        showExecuteResult("Noted. I've removed this task: \n" + t + "\nNow you have " + num + " tasks in the list.");
    }
    public void showMessageForInvalidCommandInput() {
        showExecuteResult("OOPS!!! I'm sorry, but I don't know what that means :-(!");
    }

    public void showInvalidIDMessage(CommandType type) {
        showExecuteResult("OOPS!!! The task ID that you " + type + " is invalid.");
    }

    public void showEmptyFieldMessage(CommandType type) {
        showExecuteResult("OOPS!!! The description of a " + type + " cannot be empty.");
    }

    public void showNoAtMessage() {
        showExecuteResult("OOPS!!! No /at founded in the command");
    }

    public void showNoByMessage() {
        showExecuteResult("OOPS!!! No /by founded in the command");
    }

    public void showWrongDateMessage() {
        showExecuteResult("OOPS!!! the date you enter is in wrong format! (correct: yyyy-mm-dd)");
    }
    public void showWriteToFileError() {
        showExecuteResult("Something wrong when writing to txt..");
    }


    public String getUserInput() {
        String inputLine = in.nextLine();
        while (inputLine.trim().isEmpty()) {
            inputLine = in.nextLine();
        }
        return inputLine;
    }





}
