package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {

    /**
     * Prints all tasks in the list
     *
     * @param list ArrayList containing all tasks
     */
    public static void printList(ArrayList<Task> list) {
        if(list.size() == 0) {
            Ui.printEmptyListMessage();
        } else {
            ArrayList<String> taskList = new ArrayList<>();
            StringBuilder task = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i < 9) {
                    task.append(" ");
                }
                task.append(i+1).append(list.get(i).toString());
                taskList.add(task.toString());
                task.setLength(0);
            }
            taskList.add(String.format(Constants.UNDONE_TASKS_REMAINING_MESSAGE, Task.getTasksRemaining()));
            Ui.dukePrinter(taskList);
        }
    }

    /**
     * Adds a new To Do to the list of tasks
     *
     * @param input String containing the description of the To Do
     * @param list Arraylist containing all tasks
     */
    public static void addToDo(String input, ArrayList<Task> list) {
        if(input == null) {
            Ui.printInvalidArgumentMessage(Constants.NO_DESCRIPTION_MESSAGE);
        } else {
            list.add(new ToDo(input));
            Ui.printToDoAdded(input);
        }
    }

    /**
     * Adds a new Deadline to the list of tasks
     *
     * @param input String containing the description amd dueDate of the Deadline
     * @param list ArrayList containing all tasks
     */
    public static void addDeadline(String input, ArrayList<Task> list) {
        if (input == null) {
            Ui.printInvalidArgumentMessage(Constants.NO_DESCRIPTION_MESSAGE);
        } else if(input.toLowerCase().contains("/by")) {
            try {
                String desc = input.substring(0, input.toLowerCase().indexOf("/by") - 1);
                String dueDate = input.substring(input.toLowerCase().indexOf("/by") + 4);
                list.add(new Deadline(desc, dueDate));
                Ui.printDeadlineAdded(desc, dueDate);
            } catch (Exception e) {
                Ui.printGenericErrorMessage();
            }
        } else {
            Ui.printInvalidArgumentMessage(Constants.NO_DEADLINE_MESSAGE);
        }
    }

    /**
     * Adds a new Event to the list of tasks
     *
     * @param input String containing the description and date of the Event
     * @param list ArrayList containing all tasks
     */
    public static void addEvent(String input, ArrayList<Task> list) {
        if (input == null) {
            Ui.printInvalidArgumentMessage(Constants.NO_DESCRIPTION_MESSAGE);
        } else if(input.toLowerCase().contains("/at")) {
            try {
                String description = input.substring(0, input.toLowerCase().indexOf("/at")-1);
                String time = input.substring(input.toLowerCase().indexOf("/at")+4);
                list.add(new Event(description, time));
                Ui.printEventAdded(description, time);

            } catch (Exception e) {
                Ui.printGenericErrorMessage();
            }
        } else {
            Ui.printInvalidArgumentMessage(Constants.NO_TIME_MESSAGE);
        }
    }

    /**
     * Checks a task off as done
     *
     * @param list ArrayList containing all tasks
     * @param argument Task number of task to be checked off
     */
    public static void markAsDone(ArrayList<Task> list, String argument) {
        try {
            int taskNo = Integer.parseInt(argument);
            if (taskNo <= list.size() + 1 && taskNo > 0) { //no. is valid
                if (list.get(taskNo - 1).getStatus()) {
                    Ui.printTaskAlreadyCheckedMessage(taskNo, list.get(taskNo - 1).getDesc());
                } else {
                    list.get(taskNo - 1).check();
                    Ui.printTaskChecked(taskNo, list.get(taskNo - 1).getStatusSymbol(),
                            list.get(taskNo - 1).getDesc(), Task.getTasksRemaining());
                }
            } else {
                Ui.printInvalidArgumentMessage(String.format(Constants.TASK_DOES_NOT_EXIST_MESSAGE, taskNo));
            }
        } catch (Exception e) {
            Ui.printGenericErrorMessage();
        }
    }

    /**
     * Unchecks a previously checked task
     *
     * @param list ArrayList containing all tasks
     * @param input Task number of task to be unchecked
     */
    public static void undoMarkAsDone(ArrayList<Task> list, String input) {
        try {
            int taskNo = Integer.parseInt(input);
            if (taskNo <= list.size() + 1 && taskNo > 0) { //no. is valid
                if (!list.get(taskNo - 1).getStatus()) {
                    Ui.printTaskNotCheckedMessage(taskNo, list.get(taskNo - 1).getDesc());
                } else {
                    list.get(taskNo - 1).uncheck();
                    Ui.printTaskUnchecked(taskNo, list.get(taskNo - 1).getStatusSymbol(),
                            list.get(taskNo - 1).getDesc());
                }
            } else {
                Ui.printInvalidArgumentMessage(String.format(Constants.TASK_DOES_NOT_EXIST_MESSAGE, taskNo));
            }
        } catch (Exception e) {
            Ui.printGenericErrorMessage();
        }
    }

    /**
     * Deletes a task
     * @param list ArrayList containing all tasks
     * @param input Task number of task to be deleted
     */
    public static void delete(ArrayList<Task> list, String input) {
        try {
            int taskNo = Integer.parseInt(input);
            if (taskNo <= list.size() + 1 && taskNo > 0) {
                Ui.printTaskDeleted(taskNo, list.get(taskNo - 1).toString());
                list.get(taskNo - 1).remove();
                list.remove(taskNo - 1);
            } else {
                Ui.printInvalidArgumentMessage(String.format(Constants.TASK_DOES_NOT_EXIST_MESSAGE, taskNo));
            }
        } catch (Exception e) {
            Ui.printGenericErrorMessage();
        }
    }

}
