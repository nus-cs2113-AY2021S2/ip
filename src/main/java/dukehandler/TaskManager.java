package dukehandler;

import exceptions.ExceptionAlreadyTaskMarkedDone;
import exceptions.ExceptionIllegalTaskMarkedDone;
import exceptions.ExceptionInvalidCommand;
import exceptions.ExceptionsEmptyCommandDescription;

import taskclasses.Deadline;
import taskclasses.Event;
import taskclasses.Task;
import taskclasses.ToDo;

public class TaskManager {
    static Task[] tasks = new Task[100];
    static int index = 0;

    public TaskManager() {
    }

    public static void addNewTask(String taskType, String fullCommand) {
        String[] part = fullCommand.split(taskType);
        try {

            checkNewTaskToAdd(taskType, part[1].trim());
            switch (taskType) {
            case "todo":
                tasks[index] = new ToDo(part[1].trim());
                break;
            case "deadline":
                String[] time = part[1].split("/by");
                tasks[index] = new Deadline(time[0].trim(), time[1].trim());
                break;
            case "event":
                time = part[1].split("/at");
                tasks[index] = new Event(time[0].trim(), time[1].trim());
                break;
            }
            printAddedTask();

        } catch (ExceptionInvalidCommand eic) {
            MessagePrinter.printInvalidCommandMessage(taskType);
        } catch (ArrayIndexOutOfBoundsException | ExceptionsEmptyCommandDescription oob) {
            MessagePrinter.printEmptyCommandMessage(taskType);
        }
    }

    public static void checkNewTaskToAdd(String taskType, String description)
            throws ExceptionInvalidCommand, ExceptionsEmptyCommandDescription {
        if (taskType.equals("deadline") && !description.contains("/by")
                || taskType.equals("event") && !description.contains("/at")) {

            throw new ExceptionInvalidCommand();

        } else if (taskType.equals("todo") && description.trim().isEmpty()
                || taskType.equals("deadline") && (description.split("/by")[0]).trim().isEmpty()
                || taskType.equals("event") && (description.split("/at")[0].trim()).isEmpty()) {

            throw new ExceptionsEmptyCommandDescription();

        } else if (taskType.equals("deadline") && description.split("/by")[1].trim().isEmpty()
                || taskType.equals("event") && description.split("/at")[1].trim().isEmpty()) {

            throw new ExceptionsEmptyCommandDescription();
        }
    }

    public static void printAddedTask() {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks[index].toString());
        index++;
        System.out.println(" Now you have " + index + " task"
                + (index == 1 ? " " : "s ") + "in the list.");
    }

    public static void markTaskAsDone(String doneIndexString) {
        int doneIndexInt;
        try {
            doneIndexInt = Integer.parseInt(doneIndexString);
            checkTaskToMarkDone(doneIndexInt);
        } catch (NumberFormatException nfe) {
            MessagePrinter.printDoneTaskErrorMessage("text", 0);
            return;
        } catch (ExceptionIllegalTaskMarkedDone itmd) {
            MessagePrinter.printDoneTaskErrorMessage("number", index);
            return;
        } catch (ExceptionAlreadyTaskMarkedDone atmd) {
            System.out.println(" Hey, you've already marked that as done!");
            return;
        }

        tasks[doneIndexInt - 1].markAsDone();
        System.out.println(" Nice! I've marked this task as done:\n "
                + tasks[doneIndexInt - 1].toString());
    }

    public static void checkTaskToMarkDone(int doneIndexInt)
            throws ExceptionIllegalTaskMarkedDone, ExceptionAlreadyTaskMarkedDone {
        if (doneIndexInt < 1 || doneIndexInt > index) {
            throw new ExceptionIllegalTaskMarkedDone();
        } else if ((tasks[doneIndexInt - 1].getStatusIcon()).equals("X")) {
            throw new ExceptionAlreadyTaskMarkedDone();
        }
    }

    public static void printAllTasks() {
        boolean isAllDone = true;
        if (index == 0) {
            MessagePrinter.printEmptyListMessage();
            return;
        }
        System.out.println(" Here are the tasks in your list:");
        for (int i = 1; i < index + 1; ++i) {
            if ((tasks[i - 1].getStatusIcon()).equals(" ")) {
                isAllDone = false;
            }
            System.out.println(" " + i + "." + tasks[i - 1].toString());
        }
        if (isAllDone) {
            System.out.println(" Woah, all completed! Good job!");
        }
    }
}

