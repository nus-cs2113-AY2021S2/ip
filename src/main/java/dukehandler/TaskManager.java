package dukehandler;

import exceptions.TaskAlreadyMarkedException;
import exceptions.EmptyListException;
import exceptions.IllegalTaskMarkedDoneException;
import exceptions.IllegalTaskRemovedException;
import exceptions.InvalidCommandException;
import exceptions.EmptyCommandDescriptionException;

import taskclasses.Deadline;
import taskclasses.Event;
import taskclasses.ToDo;
import ui.ErrorMessagePrinter;
import ui.SuccessMessagePrinter;

public class TaskManager {
    private final TaskList taskList;
    private final Checker checker;
    private final SuccessMessagePrinter successMessagePrinter;

    public TaskManager(TaskList taskList) {
        this.taskList = taskList;
        this.checker = new Checker(taskList);
        this.successMessagePrinter = new SuccessMessagePrinter(taskList);
    }

    public void printAllTasks() {
        boolean isAllDone = true;
        if (taskList.tasks.isEmpty()) {
            ErrorMessagePrinter.printEmptyListMessage();
            return;
        }
        System.out.println(" Here are the tasks in your list:");
        for (int i = 1; i < taskList.tasks.size() + 1; ++i) {
            if ((taskList.tasks.get(i - 1).getStatusIcon()).equals(" ")) {
                isAllDone = false;
            }
            System.out.println(" " + i + "." + taskList.tasks.get(i - 1).toString());
        }
        if (isAllDone) {
            System.out.println(" Woah, all completed! Good job!");
        }
    }

    public void markTaskAsDone(String doneIndexString) {
        int doneIndexInteger;
        try {
            doneIndexInteger = Integer.parseInt(doneIndexString);
            checker.checkTaskToMarkDone(doneIndexInteger);
        } catch (EmptyListException eel) {
            ErrorMessagePrinter.printEmptyListMessage();
            return;
        } catch (IllegalTaskMarkedDoneException | NumberFormatException nfe) {
            ErrorMessagePrinter.printDoneOrDeleteTaskErrorMessage("done",
                    taskList.tasks.size());
            return;
        } catch (TaskAlreadyMarkedException atmd) {
            ErrorMessagePrinter.printTaskAlreadyMarkedDone();
            return;
        }
        taskList.tasks.get(doneIndexInteger - 1).markAsDone();
        successMessagePrinter.printTaskMarkedDone(doneIndexInteger - 1);
    }

    public void addNewTask(String taskType, String fullCommand) {
        String[] part = fullCommand.split(taskType);
        try {
            checker.checkNewTaskToAdd(taskType, part[1].trim());
            String description = part[1].trim();
            String[] descriptionAndTime;

            switch (taskType) {
            case "todo":
                taskList.tasks.add(new ToDo(description));
                break;
            case "deadline":
                descriptionAndTime = description.split("/by");
                taskList.tasks.add(new Deadline(descriptionAndTime[0].trim(),
                        descriptionAndTime[1].trim()));
                break;
            case "event":
                descriptionAndTime = description.split("/at");
                taskList.tasks.add(new Event(descriptionAndTime[0].trim(),
                        descriptionAndTime[1].trim()));
                break;
            }
            successMessagePrinter.printAddedTask();
        } catch (InvalidCommandException eic) {
            ErrorMessagePrinter.printInvalidCommandMessage(taskType);
        } catch (ArrayIndexOutOfBoundsException | EmptyCommandDescriptionException oob) {
            ErrorMessagePrinter.printEmptyCommandMessage(taskType);
        }
    }

    public void removeTask(String removeIndexString) {
        int removeIndexInt;
        try {
            removeIndexInt = Integer.parseInt(removeIndexString);
            checker.checkTaskToRemove(removeIndexInt);
        } catch (IllegalTaskRemovedException | NumberFormatException nfe) {
            ErrorMessagePrinter.printDoneOrDeleteTaskErrorMessage("delete",
                    taskList.tasks.size());
            return;
        } catch (EmptyListException eel) {
            ErrorMessagePrinter.printEmptyListMessage();
            return;
        }
        successMessagePrinter.printRemovedTask(removeIndexInt);
        taskList.tasks.remove(taskList.tasks.get(removeIndexInt - 1));
    }

}
