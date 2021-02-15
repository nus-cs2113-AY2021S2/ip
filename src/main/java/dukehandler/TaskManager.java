package dukehandler;

import exceptions.ExceptionAlreadyTaskMarkedDone;
import exceptions.ExceptionEmptyList;
import exceptions.ExceptionIllegalTaskMarkedDone;
import exceptions.ExceptionIllegalTaskRemoved;
import exceptions.ExceptionInvalidCommand;
import exceptions.ExceptionsEmptyCommandDescription;

import taskclasses.Deadline;
import taskclasses.Event;
import taskclasses.Task;
import taskclasses.ToDo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskManager {
    static ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
    }
    public static void loadTasksFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] part = s.nextLine().split(" ~~ ");
            switch(part[0]) {
            case "T":
                tasks.add(new ToDo(part[2]));
                break;
            case "D":
                tasks.add(new Deadline(part[2], part[3]));
                break;
            case "E":
                tasks.add(new Event(part[2], part[3]));
                break;
            }
            if (part[1].equals("X")) {
                tasks.get(tasks.size()-1).markAsDone();
            }
        }
    }

    public static void saveTasksToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.getTaskType() + " ~~ "
                    + task.getStatusIcon() + " ~~ "
                    + task.getTaskName());
            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                fw.write(" ~~ " + task.getTime());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public static void printAllTasks() {
        boolean isAllDone = true;
        if (tasks.isEmpty()) {
            MessagePrinter.printEmptyListMessage();
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
            checkTaskToMarkDone(doneIndexInteger);
        } catch (ExceptionEmptyList eel) {
            MessagePrinter.printEmptyListMessage();
            return;
        } catch (ExceptionIllegalTaskMarkedDone | NumberFormatException nfe) {
            MessagePrinter.printDoneOrDeleteTaskErrorMessage("done", tasks.size());
            return;
        } catch (ExceptionAlreadyTaskMarkedDone atmd) {
            System.out.println(" Hey, you've already marked that as done!");
            return;
        }

        tasks.get(doneIndexInteger - 1).markAsDone();
        System.out.println(" Nice! I've marked this task as done:\n "
                + tasks.get(doneIndexInteger - 1).toString());
    }

    public static void checkTaskToMarkDone(int doneIndexInt)
            throws ExceptionIllegalTaskMarkedDone,
            ExceptionAlreadyTaskMarkedDone,
            ExceptionEmptyList {
        if (tasks.isEmpty()) {
            throw new ExceptionEmptyList();
        } else if (doneIndexInt < 1 || doneIndexInt > tasks.size()) {
            throw new ExceptionIllegalTaskMarkedDone();
        } else if ((tasks.get(doneIndexInt - 1).getStatusIcon()).equals("X")) {
            throw new ExceptionAlreadyTaskMarkedDone();
        }
    }

    public static void addNewTask(String taskType, String fullCommand) {
        String[] part = fullCommand.split(taskType);
        try {
            checkNewTaskToAdd(taskType, part[1].trim());
            Task newTask;
            String description = part[1].trim();
            String[] descriptionAndTime;
            String taskName;
            String timeOrDate;

            switch (taskType) {
            case "todo":
                newTask = new ToDo(description);
                tasks.add(newTask);
                break;
            case "deadline":
                descriptionAndTime = description.split("/by");
                taskName = descriptionAndTime[0].trim();
                timeOrDate = descriptionAndTime[1].trim();
                newTask = new Deadline(taskName, timeOrDate);
                tasks.add(newTask);
                break;
            case "event":
                descriptionAndTime = description.split("/at");
                taskName = descriptionAndTime[0].trim();
                timeOrDate = descriptionAndTime[1].trim();
                newTask = new Event(taskName, timeOrDate);
                tasks.add(newTask);
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
        System.out.println("   " + tasks.get(tasks.size() - 1).toString());
        System.out.println(" Now you have " + tasks.size() + " task"
                + (tasks.size() == 1 ? " " : "s ") + "in the list.");
    }

    public static void removeTask(String removeIndexString) {
        int removeIndexInt;
        try {
            removeIndexInt = Integer.parseInt(removeIndexString);
            checkTaskToRemove(removeIndexInt);
        } catch (ExceptionIllegalTaskRemoved | NumberFormatException nfe) {
            MessagePrinter.printDoneOrDeleteTaskErrorMessage("delete", tasks.size());
            return;
        } catch (ExceptionEmptyList eel) {
            MessagePrinter.printEmptyListMessage();
            return;
        }
        printRemovedTask(removeIndexInt);
        tasks.remove(tasks.get(removeIndexInt - 1));
    }

    public static void printRemovedTask(int removeIndexInt) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + tasks.get(removeIndexInt - 1).toString());
        System.out.println(" Now you have " + (tasks.size()-1) + " task"
                + (tasks.size()-1 == 1 ? " " : "s ") + "in the list.");
    }

    public static void checkTaskToRemove(int removeIndexInt)
            throws ExceptionIllegalTaskRemoved, ExceptionEmptyList {
        if (tasks.isEmpty()) {
            throw new ExceptionEmptyList();
        } else if (removeIndexInt < 1 || removeIndexInt > tasks.size()) {
            throw new ExceptionIllegalTaskRemoved();
        }
    }

}
