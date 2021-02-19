package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static duke.Messages.*;
import static duke.Storage.save;

/**
 * Contains the task list and operations to add/delete tasks in the list.
 */
public class TaskList {
    public static ArrayList<Task> taskList;

    /**
     * Construct the <code>TaskList</code> from data in the storage file.
     *
     * @param tasksLoad Task data loaded from the storage file.
     */
    public TaskList(List<String> tasksLoad) {
        taskList = new ArrayList<>();
        for (String task : tasksLoad) {
            String[] words = task.split("\\|");
            String taskType = words[0];
            String taskStatus = words[1];
            String taskDescription = words[2];
            switch (taskType) {
            case "T":
                taskList.add(new Todo(taskDescription));
                break;
            case "D":
                String taskBy = words[3];
                taskList.add(new Deadline(taskDescription, taskBy));
                break;
            case "E":
                String taskAt = words[3];
                taskList.add(new Event(taskDescription, taskAt));
                break;
            }
            if (taskStatus.equals("true")) {
                taskList.get(taskList.size() - 1).markAsDone();
            }
        }
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Find a task stored in Duke.
     *
     * @param commandDetail Command input from the user.
     */
    private static void findTask(String commandDetail) {
        System.out.println(MESSAGE_MATCH_TASK);
        int index = 0;
        while (index < taskList.size()) {
            if (taskList.get(index).toString().contains(commandDetail)) {
                System.out.println((index + 1) + "." + taskList.get(index).toString());
            }
            index++;
        }
    }

    /**
     * Delete a task stored in Duke.
     *
     * @param commandDetail Command input from the user.
     */
    private static void deleteTask(String commandDetail) {
        int deleteIndex = Integer.parseInt(commandDetail) - 1;
        Ui.showToUser(MESSAGE_DELETE_TASK, taskList.get(deleteIndex).toString(),
                String.format(MESSAGE_TASK_NUMBER, taskList.size() - 1));
        taskList.remove(deleteIndex);
    }

    /**
     * Add an event to Duke.
     *
     * @param description Event description input from user.
     * @param time        Event time input from the user.
     */
    private static void addEvent(String description, String time) {
        taskList.add(new Event(description, time));
        Ui.showToUser(MESSAGE_ADD_TASK, taskList.get(taskList.size() - 1).toString(),
                String.format(MESSAGE_TASK_NUMBER, taskList.size()));
    }

    /**
     * Add a deadline to Duke.
     *
     * @param description Deadline description input from user.
     * @param time        Deadline time input from the user.
     */
    private static void addDeadline(String description, String time) {
        taskList.add(new Deadline(description, time));
        Ui.showToUser(MESSAGE_ADD_TASK, taskList.get(taskList.size() - 1).toString(),
                String.format(MESSAGE_TASK_NUMBER, taskList.size()));
    }

    /**
     * Add a todo task to Duke.
     *
     * @param commandDetail Command input from the user.
     */
    private static void addTodo(String commandDetail) {
        taskList.add(new Todo(commandDetail));
        Ui.showToUser(MESSAGE_ADD_TASK, taskList.get(taskList.size() - 1).toString(),
                String.format(MESSAGE_TASK_NUMBER, taskList.size()));
    }

    /**
     * Mark the status of a task in Duke as done.
     *
     * @param commandDetail Command input from the user.
     */
    private static void markDone(String commandDetail) {
        int doneIndex = Integer.parseInt(commandDetail);
        Task doneTask = taskList.get(doneIndex - 1);
        doneTask.markAsDone();
        Ui.showToUser(MESSAGE_MARK_DONE, doneTask.toString());
    }

    /**
     * List all the tasks store in Duke.
     */
    private static void printList() {
        System.out.println(MESSAGE_LIST_TASKS);
        int index = 0;
        while (index < taskList.size()) {
            System.out.println((index + 1) + "." + taskList.get(index).toString());
            index++;
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @param commandStr Input command from the user.
     * @param isExit     Boolean variable shows whether the program is terminated.
     * @return Boolean variable shows whether the program is terminated.
     */
    public boolean process(List<String> commandStr, boolean isExit) {
        String commandType = commandStr.get(0);
        try {
            switch (commandType) {
            case "list":
                printList();
                break;
            case "bye":
                isExit = true;
                Ui.printBye();
                save();
                break;
            case "done": {
                String commandDetail = commandStr.get(1);
                markDone(commandDetail);
                break;
            }
            case "todo": {
                String commandDetail = commandStr.get(1);
                addTodo(commandDetail);
                break;
            }
            case "deadline":
                String descriptionD = commandStr.get(1);
                String byTime = commandStr.get(2);
                addDeadline(descriptionD, byTime);
                break;
            case "event":
                String descriptionE = commandStr.get(1);
                String atTime = commandStr.get(2);
                addEvent(descriptionE, atTime);
                break;
            case "delete": {
                String commandDetail = commandStr.get(1);
                deleteTask(commandDetail);
                break;
            }
            case "find": {
                String commandDetail = commandStr.get(1);
                findTask(commandDetail);
                break;
            }
            default:
                throw new DukeException();
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showToUser(String.format(MESSAGE_INCOMPLETE_COMMAND, commandType));
        } catch (DukeException e) {
            Ui.showToUser(MESSAGE_INVALID_COMMAND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isExit;
    }
}
