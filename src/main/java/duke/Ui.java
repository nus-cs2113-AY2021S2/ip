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


    public void showHello() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm duke.Duke, your truly daily helper");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

    }

    public void showBye() {
        showExecuteResult("Bye. Hope to see you again soon!");
    }

    public void showExecuteResult(String result) {
        System.out.println("____________________________________________________________");
        System.out.println(result);
        System.out.println("____________________________________________________________");
    }

    public void showAddResult(Task t, int num) {
        showExecuteResult("Got it. I've added this task:\n" + t + "\nNow you have " + num + " tasks in the list.");
    }
    public void showMessageForInvalidCommandInput() {
        showExecuteResult("OOPS!!! I'm sorry, but I don't know what that means :-(!");
    }

    public String getUserInput() {
        String inputLine = in.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty()) {
            inputLine = in.nextLine();
        }
        return inputLine;
    }



}
