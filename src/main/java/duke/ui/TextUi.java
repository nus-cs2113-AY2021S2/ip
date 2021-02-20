package duke.ui;

import duke.tasks.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static duke.Duke.taskList;

public class TextUi {
    private final Scanner in;
    private final PrintStream out;
    
    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printTaskAdded(Task task) {
        System.out.println("Added to list: ");
        task.printTask();
        printNumTasksLeft();
        System.out.println();
    }

    public void printTaskDeleted(int index) {
        System.out.println("Task " + (index + 1) + " has been deleted:");
        System.out.print("   ");
        taskList.get(index).printTask();
        System.out.println("Tasks remaining: " + (taskList.size() - 1) + "\n");
    }

    private static void printNumTasksLeft() {
        String output = Integer.toString(taskList.size());
        output += (taskList.size() == 1) ? " task" : " tasks";
        output += " in the list";

        System.out.println(output);
    }

    public void printInvalidInputWarning(String input) {
        System.out.println("Wrong format: " + input + "! Enter \"help\" for a list of available commands and format\n");
    }

    public void printNoTaskWarning() {
        System.out.println("You don't have any tasks ! Enter a task");
        System.out.println("Enter \"help\" for a list of available commands and format\n");
    }

    public void printInvalidIndexWarning(int jobNumber) {
        String smaller = "Enter a valid job number. Use the list command to view your current tasks.";
        String larger = "You don't have that many jobs! Use the list command to view your current tasks.";

        System.out.println(jobNumber <= 0 ? smaller : larger);
        System.out.println("Enter \"help\" for a list of available commands and format\n");
    }

    private static void printListFullWarning() {
        System.out.println("List is full!");
        System.out.println("Use the \"list\" command to view your tasks.");
        System.out.println("Enter \"bye\" to exit... \n");
    }

    public void printDataErrorWarning(int line) {
        System.out.println("Error forming task. Check formatting at line " + line + " in data file!");
        System.out.println();
    }

    public void printHelp() {
        String commandList = "LIST - \n" +
                "FORMAT: list";

        String commandDone = "DONE - \n" +
                "FORMAT: done [(int) number]";

        String commandTodo = "TODO - \n" +
                "FORMAT: todo [(str) description]";

        String commandDeadline = "DEADLINE - \n" +
                "FORMAT: deadline [(str) description] /by [(str) deadline]";

        String commandEvent = "EVENT - \n" +
                "FORMAT: event [(str) description] /at [(str) time]";

        String commandDelete = "DELETE - \n" +
                "FORMAT: delete [(int) index]";


        System.out.println("COMMAND LIST:");
        System.out.println("-------------");
        System.out.println(commandTodo + '\n');
        System.out.println(commandDeadline + '\n');
        System.out.println(commandEvent + '\n');
        System.out.println(commandList + '\n');
        System.out.println(commandDone + '\n');
        System.out.println(commandDelete + '\n');
        System.out.println("To exit, enter \"bye\"\n");

    }

    public void printHello() {
        // Start - Greets user
        String line = "____________________________________________________________\n";
        String hello_message = "Hello I'm Diuk! \nWhat would you like to do today?\n";

        System.out.print(line);
        System.out.print(hello_message);
        System.out.print(line);
    }

    public void printBye() {
        String line = "____________________________________________________________\n";
        String bye_message = "Bye! Hit me up if you feel like being productive again ;)\n";

        System.out.print(line);
        System.out.print(bye_message);
        System.out.print(line);
    }
    
}
