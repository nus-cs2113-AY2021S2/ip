package duke;

import exception.EmptyCommandException;
import exception.UnknownCommandException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class DukeController {

    public static final int EVENT_STRING_LENGTH = 5;
    public static final int DEADLINE_STRING_LENGTH = 8;
    public static final int TODO_STRING_LENGTH = 4;
    public static final int DELETE_STRING_LENGTH = 6;
    public static final String STRING_COMMAND_BYE = "bye";
    public static final String STRING_COMMAND_LIST = "list";
    public static final String STRING_COMMAND_DONE = "done";
    public static final String STRING_COMMAND_DELETE = "delete";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int taskCount = 0;

    public static void run(Scanner scanner) {
        tasks = DukeReader.getTaskListFromFile();
        taskCount = tasks.size();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(STRING_COMMAND_BYE)) {
                return;
            } else if (input.equals(STRING_COMMAND_LIST)) {
                UI.printTaskList(tasks, taskCount);
            } else if (input.contains(STRING_COMMAND_DONE)) {
                markTaskDone(input);
            } else if (input.contains(STRING_COMMAND_DELETE)) {
                deleteTask(input);
            } else {
                addNewTask(input);
            }
        }
    }

    public static void markTaskDone(String input) {
        String[] parts = input.split(" ");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        Task taskToMarkDone = tasks.get(taskIndex);
        taskToMarkDone.markDone();
        UI.taskMarkedAsDoneMessage(taskToMarkDone);
    }

    public static void addNewTask(String input) {
        try {
            Task taskToAdd = processTaskToAdd(input);
            if (taskToAdd != null) {
                addTaskSuccessful(taskToAdd);
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addTaskSuccessful(Task taskToAdd) {
        tasks.add(taskToAdd);
        taskCount++;
        UI.addTaskSuccessfulMessage(taskToAdd, taskCount);
    }

    public static Task processTaskToAdd(String input) throws UnknownCommandException{
        try {
            if (input.contains("todo")) {
                return processToDo(input);
            } else if (input.contains("deadline")) {
                return processDeadline(input);
            } else if (input.contains("event")) {
                return processEvent(input);
            } else {
                throw new UnknownCommandException();
            }
        } catch (EmptyCommandException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    private static Event processEvent(String input) throws EmptyCommandException{
        String substr = input.substring(EVENT_STRING_LENGTH);
        if (substr.isBlank()) {
            throw new EmptyCommandException("Event");
        }
        String[] parts = substr.split("/at");
        String description = parts[0].trim();
        String at = parts[1].trim();
        return new Event(description, at);
    }

    private static Deadline processDeadline(String input) throws EmptyCommandException {
        String substr = input.substring(DEADLINE_STRING_LENGTH);
        if (substr.isBlank()) {
            throw new EmptyCommandException("Deadline");
        }
        String[] parts = substr.split("/by");
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new Deadline(description, by);
    }

    private static ToDo processToDo(String input) throws EmptyCommandException {
        String substr = input.substring(TODO_STRING_LENGTH);
        if (substr.isBlank()) {
            throw new EmptyCommandException("ToDo");
        }
        return new ToDo(substr.trim());
    }

    private static void deleteTask (String input) {
        String substr = input.substring(DELETE_STRING_LENGTH).trim();
        int taskIndex = Integer.parseInt(substr) - 1;
        try {
            Task taskToDelete = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            taskCount--;
            UI.deleteTaskSuccessfulMessage(taskToDelete, taskCount);
        } catch (IndexOutOfBoundsException e) {
            UI.taskIndexOutOfBoundsMessage();
        }

    }
}
