package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    /**
     * Prints all tasks in the list
     *
     * @param list ArrayList containing all tasks
     */
    public static void listCommand(ArrayList<Task> list) {
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
     * Adds a new To Do to the list of tasks after checking
     * if there is an input
     *
     * @param input String containing the description of the To Do
     * @param list Arraylist containing all tasks
     */
    public static void toDoCommand(String input, ArrayList<Task> list) {
        if(input == null || input.length() == 0) {
            Ui.printInvalidArgumentMessage(Constants.NO_DESCRIPTION_MESSAGE);
        } else {
            list.add(new ToDo(input));
            Ui.printToDoAdded(input);
        }
    }

    /**
     * Adds a new Deadline to the list of tasks
     * after checking if there is a description and a deadline
     *
     * @param input String containing the description amd dueDate of the Deadline
     * @param list ArrayList containing all tasks
     */
    public static void deadlineCommand(String input, ArrayList<Task> list) {
        if (input == null) {
            Ui.printInvalidArgumentMessage(Constants.NO_DESCRIPTION_MESSAGE);
        } else if(input.toLowerCase().contains("/by")) {
            addDeadline(input, list);
        } else {
            Ui.printInvalidArgumentMessage(Constants.NO_DEADLINE_MESSAGE);
        }
    }

    /**
     * Adds a new Event to the list of tasks
     * after checking if there is a description and a time/date
     *
     * @param input String containing the description and date of the Event
     * @param list ArrayList containing all tasks
     */
    public static void eventCommand(String input, ArrayList<Task> list) {
        if (input == null) {
            Ui.printInvalidArgumentMessage(Constants.NO_DESCRIPTION_MESSAGE);
        } else if(input.toLowerCase().contains("/at")) {
            addEvent(input, list);
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
    public static void doneCommand(ArrayList<Task> list, String argument) {
        try {
            int taskNo = Integer.parseInt(argument);
            checkTask(list, taskNo);
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
    public static void undoCommand(ArrayList<Task> list, String input) {
        try {
            int taskNo = Integer.parseInt(input);
            uncheckTask(list, taskNo);
        } catch (Exception e) {
            Ui.printGenericErrorMessage();
        }
    }

    /**
     * Deletes a task
     *
     * @param list ArrayList containing all tasks
     * @param input Task number of task to be deleted
     */
    public static void deleteCommand(ArrayList<Task> list, String input) {
        try {
            int taskNo = Integer.parseInt(input);
            removeTask(list, taskNo);
        } catch (Exception e) {
            Ui.printGenericErrorMessage();
        }
    }

    /**
     * Searches through tasks to find tasks with descriptions matching keyword(s) provided
     * then prints a list of all matching tasks
     *
     * @param list ArrayList containing all tasks
     * @param keyword Keyword(s) used for searching
     */
    public static void findCommand(ArrayList<Task> list, String keyword) {
        List<Task> filteredList = list.stream().filter(str -> str.contains(keyword)).collect(Collectors.toList());

        ArrayList<String> taskList = new ArrayList<>();
        taskList.add(String.format(Constants.TASKS_CONTAINING_KEYWORD_MESSAGE, filteredList.size(), keyword));
        StringBuilder task = new StringBuilder();
        for (int i = 0; i < filteredList.size(); i++) {
            if (i < 9) {
                task.append(" ");
            }
            task.append(i+1).append(filteredList.get(i).toString());
            taskList.add(task.toString());
            task.setLength(0);
        }
        Ui.dukePrinter(taskList);
    }

    /**
     * Passes the input to a parser then uses the parsed data to
     * create a new deadline and add it to the task list
     *
     * @param input user input
     * @param list ArrayList containing all tasks
     */
    private static void addDeadline(String input, ArrayList<Task> list) {
        try {
            String[] parsedInput = Parser.descDateParser(input, "/by");
            String desc = parsedInput[0];
            String dueDate = parsedInput[1];
            if (dueDate != null) {
                list.add(new Deadline(desc, dueDate));
                Ui.printDeadlineAdded(desc, dueDate);
            } else {
                Ui.printInvalidArgumentMessage(Constants.NO_DEADLINE_MESSAGE);
            }
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printInvalidArgumentMessage(Constants.NO_DESCRIPTION_MESSAGE);
        } catch (Exception e) {
            Ui.printGenericErrorMessage();
        }
    }

    /**
     * Passes the input to a parser then uses the parsed data to
     * create a new event and add it to the list
     *
     * @param input user input
     * @param list ArrayList containing all tasks
     */
    private static void addEvent(String input, ArrayList<Task> list) {
        try {
            String[] parsedInput = Parser.descDateParser(input, "/at");
            String desc = parsedInput[0];
            String date = parsedInput[1];
            if (date != null) {
                list.add(new Event(desc, date));
                Ui.printEventAdded(desc, date);
            } else {
                Ui.printInvalidArgumentMessage(Constants.NO_TIME_MESSAGE);
            }
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printInvalidArgumentMessage(Constants.NO_DESCRIPTION_MESSAGE);
        } catch (Exception e) {
            Ui.printGenericErrorMessage();
        }
    }

    /**
     * Checks if the task requested by the user exists
     * (Checks if the task number is valid)
     *
     * @param listSize number of tasks in the list
     * @param taskNo index of task requested by the user
     * @return true if the task number is valid, false if the task number is invalid
     */
    private static boolean checkValidIndex(int listSize, int taskNo) {
        if (!(taskNo <= listSize && taskNo > 0)) {
            Ui.printInvalidArgumentMessage(String.format(Constants.TASK_DOES_NOT_EXIST_MESSAGE));
            return false;
        }
        return true;
    }

    /**
     * Checks the task off as done
     *
     * @param list ArrayList containing all tasks
     * @param taskNo task number of the task to be checked off
     */
    private static void checkTask(ArrayList<Task> list, int taskNo) {
        if (checkValidIndex(list.size(), taskNo)) {
            if (list.get(taskNo - 1).getStatus()) {
                Ui.printTaskAlreadyCheckedMessage(taskNo, list.get(taskNo - 1).getDesc());
            } else {
                list.get(taskNo - 1).check();
                Ui.printTaskChecked(taskNo, list.get(taskNo - 1).getStatusSymbol(),
                        list.get(taskNo - 1).getDesc(), Task.getTasksRemaining());
            }
        }
    }

    /**
     * Unchecks an already checked task
     *
     * @param list ArrayList containing all tasks
     * @param taskNo task number of the task to be unchecked
     */
    private static void uncheckTask(ArrayList<Task> list, int taskNo) {
        if (checkValidIndex(list.size(), taskNo)) {
            if (!list.get(taskNo - 1).getStatus()) {
                Ui.printTaskNotCheckedMessage(taskNo, list.get(taskNo - 1).getDesc());
            } else {
                list.get(taskNo - 1).uncheck();
                Ui.printTaskUnchecked(taskNo, list.get(taskNo - 1).getStatusSymbol(),
                        list.get(taskNo - 1).getDesc());
            }
        }
    }

    /**
     * Removes the task from the list
     * @param list ArrayList containing all tasks
     * @param taskNo task number of the task to be removed
     */
    private static void removeTask(ArrayList<Task> list, int taskNo) {
        if (checkValidIndex(list.size(), taskNo)) {
            Ui.printTaskDeleted(taskNo, list.get(taskNo - 1).toString());
            list.get(taskNo - 1).remove();
            list.remove(taskNo - 1);
        }
    }
}
