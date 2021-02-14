package duke;

import duke.exception.DescriptionFieldEmptyException;
import duke.exception.MultipleTimeFieldsException;
import duke.exception.TimeFieldEmptyException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        printStartingMessage();

        ArrayList<Task> tasks = new ArrayList<>();
        Task task;
        String[] description;
        String inputLine;
        int COMMAND_TASK_SEPARATOR = 2;

        while (!(inputLine = userInput.nextLine().trim()).equals("bye")) {

            printHorizontalLine();

            String[] command = inputLine.split(" ", COMMAND_TASK_SEPARATOR);

            switch (command[0].toLowerCase()) {
            case "list":
                printListMessage(tasks);
                break;
            case "done":
                try {
                    int doneTaskNumber = Integer.parseInt(command[1]);
                    task = tasks.get(doneTaskNumber - 1);
                    task.markAsDone();
                    printDoneMessage(task);
                } catch (IndexOutOfBoundsException e) {
                    printNonExistentTaskMessage();
                } catch (NumberFormatException e) {
                    printNotANumberMessage();
                }
                break;
            case "deadline":
                try {
                    description = command[1].split("/by", COMMAND_TASK_SEPARATOR);
                    checkForValidDeadlineInput(description);
                    Deadline deadline = new Deadline(description[0].trim(), description[1].trim());
                    tasks.add(deadline);
                    printAddedMessage(tasks, deadline);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printMissingFieldsMessage();
                } catch (DescriptionFieldEmptyException e) {
                    printDescriptionFieldEmptyMessage();
                } catch (TimeFieldEmptyException e) {
                    printTimeFieldEmptyMessage();
                } catch (MultipleTimeFieldsException e) {
                    printTooManyTimeFieldsMessage();
                }
                break;
            case "event":
                try {
                    description = command[1].split("/at", COMMAND_TASK_SEPARATOR);
                    checkForValidEventInput(description);
                    Event event = new Event(description[0].trim(), description[1].trim());
                    tasks.add(event);
                    printAddedMessage(tasks, event);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printMissingFieldsMessage();
                } catch (DescriptionFieldEmptyException e) {
                    printDescriptionFieldEmptyMessage();
                } catch (TimeFieldEmptyException e) {
                    printTimeFieldEmptyMessage();
                } catch (MultipleTimeFieldsException e) {
                    printTooManyTimeFieldsMessage();
                }
                break;
            case "todo":
                try {
                    ToDo toDo = new ToDo(command[1]);
                    tasks.add(toDo);
                    printAddedMessage(tasks, toDo);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printMissingFieldsMessage();
                }
                break;
            case "delete":
                try {
                    int deleteTaskNumber = Integer.parseInt(command[1]);
                    Task deletedTask = tasks.get(deleteTaskNumber - 1);
                    tasks.remove(deletedTask);
                    printDeletedMessage(tasks, deletedTask);
                } catch (IndexOutOfBoundsException e) {
                    printNonExistentTaskMessage();
                } catch (NumberFormatException e) {
                    printNotANumberMessage();
                }
                break;
            default:
                printCommandDoesNotExistMessage();
                break;
            }

            printHorizontalLine();
        }

        printByeMessage();

    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printStartingMessage() {
        System.out.println("Hello from");
        System.out.println(" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n");
        printHorizontalLine();
        System.out.println(" Hello! I'm duke.Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

    public static void printListMessage(ArrayList<Task> tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.println(" " + (tasks.indexOf(task) + 1) + "." + task.toString());
        }
    }

    public static void printDoneMessage(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task.toString());
    }

    public static void printAddedMessage(ArrayList<Task> tasks, Task task) {
        System.out.println(" Alright, I've added this task:\n   " + task.toString() + "\n"
                + " Now you have " + tasks.size() + " tasks in your list.");
    }

    public static void printDeletedMessage(ArrayList<Task> tasks, Task task) {
        System.out.println(" Alright, I've deleted this task:\n   " + task.toString() + "\n"
                + " Now you have " + tasks.size() + " tasks in your list.");
    }

    public static void printByeMessage() {
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void checkForValidDeadlineInput(String[] input) throws DescriptionFieldEmptyException,
            TimeFieldEmptyException,
            MultipleTimeFieldsException {
        if (input[0].trim().equals("")) {
            throw new DescriptionFieldEmptyException();
        } else if (input[1].contains("/by")) {
            throw new MultipleTimeFieldsException();
        } else if (input[1].trim().equals("")) {
            throw new TimeFieldEmptyException();
        }
    }

    public static void checkForValidEventInput(String[] input) throws DescriptionFieldEmptyException,
            TimeFieldEmptyException,
            MultipleTimeFieldsException {
        if (input[0].trim().equals("")) {
            throw new DescriptionFieldEmptyException();
        } else if (input[1].contains("/at")) {
            throw new MultipleTimeFieldsException();
        } else if (input[1].trim().equals("")) {
            throw new TimeFieldEmptyException();
        }
    }

    public static void printDescriptionFieldEmptyMessage() {
        System.out.println(" ERROR: the description field of a task cannot be empty :(");
    }

    public static void printTimeFieldEmptyMessage() {
        System.out.println(" ERROR: the time field of a task cannot be empty :(");
    }

    public static void printMissingFieldsMessage() {
        System.out.println(" ERROR: make sure that you've input a description and time field!");
    }

    public static void printTooManyTimeFieldsMessage() {
        System.out.println(" ERROR: too many timings, try again with just one!");
    }

    public static void printCommandDoesNotExistMessage() {
        System.out.println(" ERROR: there is no such command, try again!");
    }

    public static void printNonExistentTaskMessage() {
        System.out.println(" ERROR: this task number doesn't exist!");
    }

    public static void printNotANumberMessage() {
        System.out.println(" ERROR: this is not a task number!");
    }

}
