package ip.duke;

import ip.duke.exception.*;
import ip.duke.task.Deadline;
import ip.duke.task.Event;
import ip.duke.task.Task;
import ip.duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private static final int ONE_INDEX = 1;
    private static final int START_POSITION = 0;
    private static final int SHORT_LINE1_POSITION = 4;
    private static final int SHORT_LINE2_POSITION = 7;
    private static final int DATE_LENGTH = 10;
    private static final int BY_SPACE_LENGTH = 4;
    private static final int BY_SPACE_SLASH_LENGTH = 5;
    private static final int AT_SPACE_LENGTH = 4;
    private static final int AT_SPACE_SLASH_LENGTH = 5;
    private static final int TIME_POSITION = 10;
    private static final String SPACE = " ";

    public static ArrayList<Task> list;

    /**
     * Constructs a new task list if there is no data stored in the given address.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructs a task list by reading from the file in the given address.
     *
     * @param list the task list that is read from the file in the given address
     */
    public TaskList(ArrayList<Task> list) {
        TaskList.list = list;
    }

    /**
     * Updates a Todo type task into the task list
     * and confirms the operation by printing a confirmation message
     *
     * @param description the description of this Todo task obtained by parsing the user's input command
     */
    public static void updateTodo(String description) {
        list.add(new Todo(description));
        Ui.printRecordMessage(list.get(list.size() - ONE_INDEX));
    }

    /**
     * Updates a Deadline type task into the task list
     * and confirms the operation by printing a confirmation message
     *
     * @throws TimeException an exception occurs if the due time of this Deadline task is missing
     * @throws DateException an exception occurs if the date format is not correct
     * @oaram commandContent the user input content of this Deadline task
     */
    public static void updateDeadline(String commandContent) throws TimeException, DateException {
        if (!commandContent.contains("/") || !commandContent.contains("by")) {
            throw new TimeException();
        }
        int byTimePosition = commandContent.indexOf("/") + BY_SPACE_LENGTH;
        String description = commandContent.substring(START_POSITION, byTimePosition - BY_SPACE_SLASH_LENGTH);
        String byTime = commandContent.substring(byTimePosition);
        if (byTime.length() < DATE_LENGTH ||
                byTime.charAt(SHORT_LINE1_POSITION) != ('-') ||
                byTime.charAt(SHORT_LINE2_POSITION) != ('-')
        ) {
            throw new DateException();
        }
        String theDay;
        String theTime;
        LocalDate date = LocalDate.parse(byTime.substring(START_POSITION, DATE_LENGTH));
        theDay = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        theTime = byTime.substring(TIME_POSITION);
        byTime = theDay + SPACE + theTime;
        list.add(new Deadline(description, byTime));
        Ui.printRecordMessage(list.get(list.size() - ONE_INDEX));
    }

    /**
     * Updates an Event type task into the task list
     * and confirms the operation by printing a confirmation message
     *
     * @param commandContent the user input content of this Event task
     * @throws TimeException an exception occurs if the start time of this Event task is missing
     * @throws DateException an exception occurs if the date format is not correct
     */
    public static void updateEvent(String commandContent) throws TimeException, DateException {
        if (!commandContent.contains("/") || !commandContent.contains("at")) {
            throw new TimeException();
        }
        int atTimePosition = commandContent.indexOf("/") + AT_SPACE_LENGTH;
        String description = commandContent.substring(START_POSITION, atTimePosition - AT_SPACE_SLASH_LENGTH);
        String atTime = commandContent.substring(atTimePosition);
        if (atTime.length() < DATE_LENGTH ||
                atTime.charAt(SHORT_LINE1_POSITION) != ('-')
                || atTime.charAt(SHORT_LINE2_POSITION) != ('-')
        ) {
            throw new DateException();
        }
        String theDay;
        String theTime;
        LocalDate date = LocalDate.parse(atTime.substring(START_POSITION, DATE_LENGTH));
        theDay = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        theTime = atTime.substring(TIME_POSITION);
        atTime = theDay + SPACE + theTime;
        list.add(new Event(description, atTime));
        Ui.printRecordMessage(list.get(list.size() - ONE_INDEX));
    }

    /**
     * Marks the indicated user's input task as done
     * and confirms the marking operation by printing a confirmation message.
     *
     * @param doneIndex the index of the task that to be marked as done
     * @throws IndexException an exception occurs if the user input index
     *                        of the task to be mark as done is out of the range of the task list
     * @throws ListException  an exception occurs if there is no task in the task list
     */
    public static void markDone(int doneIndex) throws IndexException, ListException {
        if (list.size() == 0) {
            throw new ListException();
        }
        if (doneIndex >= list.size() + 1 || doneIndex <= 0) {
            throw new IndexException();
        }
        Task doneTask = list.get(doneIndex - ONE_INDEX);
        doneTask.setDone(true);
        list.set(doneIndex - ONE_INDEX, doneTask);
        Ui.printDoneMessage(doneTask);
    }

    /**
     * Deletes the indicated user's input task from the task list
     * and confirms the marking operation by printing a confirmation message.
     *
     * @param deletedIndex the index of the task that to be deleted
     * @throws DukeException an exception occurs if the user input index
     *                       of the task to deleted is out of the range of the task list
     * @throws ListException an exception occurs if there is no task in the task list
     */
    public static void deleteTask(int deletedIndex) throws IndexException, ListException {
        if (list.size() == 0) {
            throw new ListException();
        }
        if (deletedIndex >= list.size() + 1 || deletedIndex <= 0) {
            throw new IndexException();
        }
        Task deletedTask = list.get(deletedIndex - ONE_INDEX);
        Ui.printDeletedMessage(deletedTask);
        list.remove(deletedIndex - ONE_INDEX);
    }

    public static int getSize() {
        return list.size();
    }

    public static ArrayList<Task> getList() {
        return list;
    }

    /**
     * Prints all tasks in the task list if the list is not empty
     * Prints a message to tell the user the list is empty if there is no task in the task list
     */
    public static void getCompleteList() {
        if (list.size() == 0) {
            Ui.printEmptyListMessage();
        } else {
            Ui.printList(list);
        }
    }


    /**
     * Obtains the tasks that contains the keyword that input by user and stores them in another task list.
     * Prints the new task list obtained before if the new task list is not empty
     * Prints a message if there is no matching task in the list so the new task list is empty
     *
     * @param keywordToFind the keyword that the user want all the matching tasks to contain
     * @throws ListException an exception occurs when there is no task in the task list
     */
    public static void getFoundTask(String keywordToFind) throws ListException {
        if (list.size() == 0) {
            throw new ListException();
        }
        ArrayList<Task> foundList = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(keywordToFind)) {
                foundList.add(task);
            }
        }
        if (foundList.size() != 0) {
            Ui.printFoundList(foundList);
        } else {
            Ui.printEmptyMessage();
        }
    }

    /**
     * Obtains the tasks that contains the date that input by user and stores them in another task list.
     * Prints the new list obtained before if the new task list is not empty
     * Prints a message if there is no matching task in the list so the new list is empty
     *
     * @param commandContent the description of the date type
     * @throws DateException an exception occurs if the user input date is not follow the correct format
     * @throws ListException an exception occurs if there is no task in the task list
     */
    public static void getDateTask(String commandContent) throws DateException, ListException {
        if (list.size() == 0) {
            throw new ListException();
        }
        if (commandContent.length() < DATE_LENGTH ||
                commandContent.charAt(SHORT_LINE1_POSITION) != ('-') ||
                commandContent.charAt(SHORT_LINE2_POSITION) != ('-')
        ) {
            throw new DateException();
        }
        String dateToFind = commandContent.substring(START_POSITION, DATE_LENGTH);
        LocalDate date = LocalDate.parse(dateToFind);
        dateToFind = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        ArrayList<Task> dateList = new ArrayList<>();
        for (Task task : list) {
            if (task.toString().contains(dateToFind)) {
                dateList.add(task);
            }
        }
        if (dateList.size() != 0) {
            Ui.printDateList(dateList);
        } else {
            Ui.printNoMessage();
        }
    }
}



