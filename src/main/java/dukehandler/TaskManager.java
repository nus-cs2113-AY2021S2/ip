package dukehandler;

import exceptions.TaskAlreadyMarkedException;
import exceptions.EmptyListException;
import exceptions.IllegalTaskMarkedDoneException;
import exceptions.IllegalTaskRemovedException;
import exceptions.InvalidCommandException;
import exceptions.EmptyCommandDescriptionException;

import taskclasses.Deadline;
import taskclasses.Event;
import taskclasses.Task;
import taskclasses.ToDo;
import ui.ErrorMessagePrinter;
import ui.SuccessMessagePrinter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskManager {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
    }

    public static void printAllTasks() {
        boolean isAllDone = true;
        if (tasks.isEmpty()) {
            ErrorMessagePrinter.printEmptyListMessage();
            return;
        }
        System.out.println(" Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; ++i) {
            if ((tasks.get(i - 1).getStatusIcon()).equals(" ")) {
                isAllDone = false;
            }
            System.out.println(" " + i + "." + tasks.get(i - 1).toString());
        }
        if (isAllDone) {
            System.out.println(" Woah, all completed! Good job!");
        }
    }

    public static void markTaskAsDone(String doneIndexString) {
        int doneIndexInteger;
        try {
            doneIndexInteger = Integer.parseInt(doneIndexString);
            Checker.checkTaskToMarkDone(doneIndexInteger);
        } catch (EmptyListException eel) {
            ErrorMessagePrinter.printEmptyListMessage();
            return;
        } catch (IllegalTaskMarkedDoneException | NumberFormatException nfe) {
            ErrorMessagePrinter.printDoneOrDeleteTaskErrorMessage("done", tasks.size());
            return;
        } catch (TaskAlreadyMarkedException atmd) {
            ErrorMessagePrinter.printTaskAlreadyMarkedDone();
            return;
        }
        tasks.get(doneIndexInteger - 1).markAsDone();
        SuccessMessagePrinter.printTaskMarkedDone(doneIndexInteger - 1);
    }

    public static void addNewTask(String taskType, String fullCommand) {
        String[] part = fullCommand.split(taskType);
        try {
            Checker.checkNewTaskToAdd(taskType, part[1].trim());
            String description = part[1].trim();
            String[] descriptionAndTime;

            switch (taskType) {
            case "todo":
                tasks.add(new ToDo(description));
                break;
            case "deadline":
                descriptionAndTime = description.split("/by");
                tasks.add(new Deadline(descriptionAndTime[0].trim(), descriptionAndTime[1].trim()));
                break;
            case "event":
                descriptionAndTime = description.split("/at");
                tasks.add(new Event(descriptionAndTime[0].trim(), descriptionAndTime[1].trim()));
                break;
            }
            SuccessMessagePrinter.printAddedTask();
        } catch (InvalidCommandException eic) {
            ErrorMessagePrinter.printInvalidCommandMessage(taskType);
        } catch (ArrayIndexOutOfBoundsException | EmptyCommandDescriptionException oob) {
            ErrorMessagePrinter.printEmptyCommandMessage(taskType);
        }
    }

    public static void removeTask(String removeIndexString) {
        int removeIndexInt;
        try {
            removeIndexInt = Integer.parseInt(removeIndexString);
            Checker.checkTaskToRemove(removeIndexInt);
        } catch (IllegalTaskRemovedException | NumberFormatException nfe) {
            ErrorMessagePrinter.printDoneOrDeleteTaskErrorMessage("delete", tasks.size());
            return;
        } catch (EmptyListException eel) {
            ErrorMessagePrinter.printEmptyListMessage();
            return;
        }
        SuccessMessagePrinter.printRemovedTask(removeIndexInt);
        tasks.remove(tasks.get(removeIndexInt - 1));
    }

    public static void printTasksWithKeywords(String keyword) {
        try {
            Checker.checkTasksToFind(keyword.trim());
        } catch (InvalidCommandException i) {
            ErrorMessagePrinter.printInvalidCommandMessage("'find'");
            return;
        } catch (ArrayIndexOutOfBoundsException a) {
            ErrorMessagePrinter.printEmptyCommandMessage("find command");
        }
        System.out.println(" Here are the matching tasks in your list:");
        int[] i = {1};
        tasks.stream()
                .filter((t) -> t.getTaskName().trim().contains(keyword.trim()))
                .forEach((t) -> System.out.println(" " + (i[0]++) + ". " + t));
    }

}
