package duke;

import duke.exception.DescriptionFieldEmptyException;
import duke.exception.MultipleTimeFieldsException;
import duke.exception.TimeFieldEmptyException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        printStartingMessage();

        Task[] tasks = new Task[100];
        Task task;
        ToDo toDo;
        String[] splitCommand;
        String[] description;
        String command;
        int numberOfTasks = 0;

        while (!(command = userInput.nextLine().trim()).equals("bye")) {

            printHorizontalLine();

            if (command.equals("list")) {
                printListMessage(tasks, numberOfTasks);
                printHorizontalLine();
                continue;
            }

            String[] tokens = command.split(" ");

            switch (tokens[0]) {
            case "done":
                int taskNumber = Integer.parseInt(tokens[1]);
                task = tasks[taskNumber - 1];
                task.markAsDone();
                printDoneMessage(task);
                break;
            case "deadline":
                try {
                    splitCommand = command.split(" ", 2);
                    description = splitCommand[1].split("/by", 2);
                    checkForValidDeadlineInput(description);
                    Deadline deadline = new Deadline(description[0].trim(), description[1].trim());
                    tasks[numberOfTasks] = deadline;
                    printDeadlineMessage(deadline, ++numberOfTasks);
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
                    splitCommand = command.split(" ", 2);
                    description = splitCommand[1].split("/at", 2);
                    checkForValidEventInput(description);
                    Event event = new Event(description[0].trim(), description[1].trim());
                    tasks[numberOfTasks] = event;
                    printEventMessage(event, ++numberOfTasks);
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
                    toDo = new ToDo(tokens[1]);
                    tasks[numberOfTasks] = toDo;
                    printToDoMessage(toDo, ++numberOfTasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printMissingFieldsMessage();
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

    public static void printListMessage(Task[] tasks, int numberOfTasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i].toString());
        }
    }

    public static void printDoneMessage(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task.toString());
    }

    public static void printDeadlineMessage(Deadline deadline, int numberOfTasks) {
        System.out.println(" Alright, I've added this task:\n   " + deadline.toString() + "\n"
                + " Now you have " + numberOfTasks + " tasks in your list.");
    }

    public static void printEventMessage(Event event, int numberOfTasks) {
        System.out.println(" Alright, I've added this task:\n   " + event.toString() + "\n"
                + " Now you have " + numberOfTasks + " tasks in your list.");
    }

    public static void printToDoMessage(ToDo toDo, int numberOfTasks) {
        System.out.println(" Alright, I've added this task:\n   " + toDo.toString() + "\n"
                + " Now you have " + numberOfTasks + " tasks in your list.");
    }

    public static void printByeMessage() {
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

}
