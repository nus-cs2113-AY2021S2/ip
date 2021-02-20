package duke;

import duke.error.EmptyNameFieldException;
import duke.error.IllegalAccessException;
import duke.error.WrongFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private static final int ERR_NO_NAME = -4;
    private static final int ERR_WRONG_FORMAT_MESSAGE = -2;
    private static final int INVALID_INDEX = -1;
    private static final int ADD_TODO = 4;
    private static final int ADD_DEADLINE = 5;
    private static final int ADD_EVENT = 6;

    private static final String LIST_ITEMS_MESSAGE = "Eez are the tings you added to the list";
    private static final String DELETED_MESSAGE = "This ting has been deleted. I could've done that task, you stupid.";
    private static final String SET_TO_DONE_MESSAGE = " set to done. You're well smart innit?";
    private static final String ADDED_TO_LIST_MESSAGE = "Wicked. This ting is now on da list.";

    private ArrayList<Task> Tasks;
    private Ui ui;

    public TaskList(Ui ui) {
        this.Tasks = new ArrayList<>();
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return Tasks;
    }
    /**
     * Parses type of item to add and calls appropriate method.
     *
     * @param line raw input given by user.
     */
    public void addItem(String line) {
        try {
            String prefix = line.split(" ")[0];
            int itemType = getItemType(prefix);
            // No fallthrough required
            switch (itemType) {
            case ADD_TODO:
                addTodo(line);
                break;
            case ADD_DEADLINE:
                addDeadline(line);
                break;
            case ADD_EVENT:
                addEvent(line);
            }
            ui.printBorderLine();
        } catch (WrongFormatException e) {
            ui.printError(ERR_WRONG_FORMAT_MESSAGE);
        } catch (EmptyNameFieldException e) {
            ui.printError(ERR_NO_NAME);
        }
    }
    /**
     * Parses the type of task to add to list.
     *
     * @param line input from user.
     * @return Type of task to add if valid input, error otherwise.
     * @throws WrongFormatException If type of task is invalid.
     */
    public static int getItemType(String line) throws WrongFormatException {
        if (line.equalsIgnoreCase("todo")) {
            return ADD_TODO;
        }
        if (line.equalsIgnoreCase("deadline")) {
            return ADD_DEADLINE;
        }
        if (line.equalsIgnoreCase("event")) {
            return ADD_EVENT;
        }
        throw new WrongFormatException();
    }
    public void addTodo(String line) throws EmptyNameFieldException {
        if (line.length() < 6) {
            throw new EmptyNameFieldException();
        }
        int current = Task.totalNumberOfTasks;
        String nameOfTask = line.substring(5);
        Tasks.add(new Todo(nameOfTask));
        Task.totalNumberOfTasks += 1;
        printAddedToList(current);
    }
    public void addDeadline(String line) throws EmptyNameFieldException, WrongFormatException {
        if (line.length() < 10) {
            throw new EmptyNameFieldException();
        }
        if (line.contains("/by")) {
            int current = Task.totalNumberOfTasks;
            // Details of task starts at index 9 of input
            String nameAndDeadline = line.substring(9);
            String[] split = nameAndDeadline.split(" /by ");
            String name = split[0];
            String deadline = split[1];
            Tasks.add(new Deadline(name, deadline));
            Task.totalNumberOfTasks += 1;
            printAddedToList(current);
        } else {
            throw new WrongFormatException();
        }
    }
    public void addEvent(String nameOfTask) throws EmptyNameFieldException, WrongFormatException {
        if (nameOfTask.length() < 7) {
            throw new EmptyNameFieldException();
        }
        if (nameOfTask.contains("/at")) {
            int current = Task.totalNumberOfTasks;
            // Details of task starts at index 6 of input
            String nameAndTime = nameOfTask.substring(6);
            String[] split = nameAndTime.split(" /at ");
            String name = split[0];
            String time = split[1];
            Tasks.add(new Event(name, time));
            Task.totalNumberOfTasks += 1;
            printAddedToList(current);
        } else {
            throw new WrongFormatException();
        }
    }
    /** Lists all items that were added to the list. */
    public void listItems() {
        System.out.println(LIST_ITEMS_MESSAGE);
        for(int i = 0; i < Task.totalNumberOfTasks; i++) {
            printListItem(i+1, Tasks.get(i).getType(), Tasks.get(i).getStatusIcon(),
                    Tasks.get(i).getName(), Tasks.get(i).getDate());
        }
        ui.printBorderLine();
    }
    /**
     * Deletes item from task list if input format is correct.
     *
     * @param line user input.
     * @throws EmptyNameFieldException if index to delete is not given.
     * @throws IllegalAccessException if index is out of bounds.
     */
    public void deleteItem(String line) throws EmptyNameFieldException, IllegalAccessException {
        if (line.length() < 8) {
            throw new EmptyNameFieldException();
        }
        int index =checkValidDelete(line);
        if (index == INVALID_INDEX) {
            throw new IllegalAccessException();
        } else {
            Task.totalNumberOfTasks -= 1;
            printDeletedTask(index-1);
            Tasks.remove(index-1);
        }
        ui.printBorderLine();
    }
    /**
     * Checks for presence of number on index 7 of input.
     * Then check if the number is within bounds.
     *
     * @param line input from user.
     * @return index of item to mark as done if valid, -1 otherwise.
     */
    public int checkValidDelete(String line) {
        if (line.substring(7).matches("[0-9]+")) {
            int listNum = Integer.parseInt(line.substring(7));
            // Check for illegal access to out of bounds index
            if (listNum > Task.totalNumberOfTasks || listNum == 0) {
                return INVALID_INDEX;
            }
            return listNum;
        }
        return INVALID_INDEX;
    }
    /**
     * Marks a task as done.
     * Checks for out of bounds access and presence of numerical value.
     *
     * @param line user input.
     * @throws IllegalAccessException if index given is out of bounds.
     * @throws EmptyNameFieldException if index is not given.
     */
    public void markAsDone(String line) throws IllegalAccessException, EmptyNameFieldException {
        if (line.length() < 6) {
            throw new EmptyNameFieldException();
        }
        int listNum = checkValidDone(line);
        if (listNum == INVALID_INDEX) {
            throw new IllegalAccessException();
        } else {
            listNum -= 1;
            Tasks.get(listNum).setDone();
            printMarkedDone(Tasks.get(listNum).getName());
        }
        ui.printBorderLine();
    }
    /**
     * Checks for presence of number on index 5 of input.
     * Then check if the number is within bounds.
     *
     * @param line input from user.
     * @return index of item to mark as done if valid, -1 otherwise.
     */
    public static int checkValidDone(String line) {
        if (line.substring(5).matches("[0-9]+")) {
            int listNum = Integer.parseInt(line.substring(5));
            // Check for illegal access to out of bounds index
            if (listNum > Task.totalNumberOfTasks || listNum == 0) {
                return INVALID_INDEX;
            }
            return listNum;
        }
        return INVALID_INDEX;
    }

    //**************************************** Methods for printing ***************************************************/
    /** Method used to print each item for "list" command */
    public void printListItem(int index, String type, String status, String name, String date) {
        System.out.println(index + ". " + type + "[" + status + "] " + name + " " + date);
    }
    public void printMarkedDone(String name) {
        System.out.println(name + SET_TO_DONE_MESSAGE);
    }
    /**
     * Prints message for deletion of task
     *
     * @param index index of item to delete
     */
    public void printDeletedTask(int index) {
        System.out.println(DELETED_MESSAGE);
        printDetailsOfTask(index);
        ui.printTotalTasks();
    }
    public void printDetailsOfTask(int index) {
        Task task = Tasks.get(index);
        System.out.println(task.getType() + "[" + task.getStatusIcon() + "] " + task.getName() + " " + task.getDate());
    }
    public void printAddedToList(int current) {
        System.out.println(ADDED_TO_LIST_MESSAGE);
        printDetailsOfTask(current);
        ui.printTotalTasks();
    }
}
