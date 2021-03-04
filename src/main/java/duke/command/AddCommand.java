package duke.command;

import duke.TaskList;
import duke.Ui;

import duke.error.EmptyDateException;
import duke.error.EmptyNameFieldException;
import duke.error.WrongFormatException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.ArrayList;

public class AddCommand extends Command {
    private static final int ERR_NO_DATE = -5;
    private static final int ERR_NO_NAME = -4;
    private static final int ERR_WRONG_FORMAT_MESSAGE = -2;
    private static final int ERR_WRONG_DATE_FORMAT = -1;
    private static final int ADD_TODO = 4;
    private static final int ADD_DEADLINE = 5;
    private static final int ADD_EVENT = 6;

    private final TaskList taskList;
    private final String input;
    private final Ui ui;

    public AddCommand(String input, TaskList taskList, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void execute(Ui ui) {
        try {
            ArrayList<Task> Tasks = taskList.getTasks();
            addItem(this.input, Tasks);
        } catch (WrongFormatException e) {
            this.ui.printError(ERR_WRONG_FORMAT_MESSAGE);
        } catch (EmptyNameFieldException e) {
            this.ui.printError(ERR_NO_NAME);
        } catch (EmptyDateException | ArrayIndexOutOfBoundsException e) {
            this.ui.printError(ERR_NO_DATE);
        } catch (DateTimeException e) {
            this.ui.printError(ERR_WRONG_DATE_FORMAT);
        }
    }

    /**
     * Parses type of item to add and calls appropriate method.
     *
     * @param line raw input given by user.
     */
    public void addItem(String line, ArrayList<Task> Tasks) throws WrongFormatException, EmptyNameFieldException,
            EmptyDateException {
        String command = line.split(" ")[0];
        int itemType = getItemType(command);
        // No fallthrough required
        switch (itemType) {
        case ADD_TODO:
            addTodo(line, Tasks);
            break;
        case ADD_DEADLINE:
            addDeadline(line, Tasks);
            break;
        case ADD_EVENT:
            addEvent(line, Tasks);
        }
    }

    /**
     * Parses the type of task to add to list. Returns a constant integer representing type of task.
     *
     * @param line input from user.
     * @return Type of task to add if valid input, error otherwise.
     * @throws WrongFormatException If type of task is not recognised.
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

    /**
     * Adds a task of type todo into the list after checking validity of the input.
     *
     * @param line user input.
     * @param tasks ArrayList containing all tasks.
     * @throws EmptyNameFieldException if task name is not given.
     */
    public void addTodo(String line, ArrayList<Task> tasks) throws EmptyNameFieldException {
        validateTodoFormat(line);
        int current = Task.totalNumberOfTasks;
        String nameOfTask = line.substring(5);
        tasks.add(new Todo(nameOfTask.trim()));
        Task.totalNumberOfTasks += 1;
        this.ui.printAddedToList(current, tasks);
    }

    /**
     * Validates user input for task type Todo.
     *
     * @param line user input.
     * @throws EmptyNameFieldException if task name is not given or all whitespace.
     */
    public void validateTodoFormat(String line) throws EmptyNameFieldException {
        // Checks if name field is blank or all whitespace
        if (line.length() < 6 || line.substring(5).trim().length() == 0) {
            throw new EmptyNameFieldException();
        }
    }

    /**
     * Adds a task of type deadline into the list after checking validity of input.
     *
     * @param line user input.
     * @param tasks ArrayList of tasks.
     * @throws EmptyNameFieldException if task name is not given or all whitespace.
     * @throws WrongFormatException if task name does not contain substring "/by"
     * @throws EmptyDateException if date is not given or full of whitespace.
     * @throws DateTimeException if date given cannot be parsed as LocalDate.
     * @throws ArrayIndexOutOfBoundsException if there is nothing after the "/by".
     */
    public void addDeadline(String line, ArrayList<Task> tasks) throws EmptyNameFieldException,
            WrongFormatException, EmptyDateException, DateTimeException, ArrayIndexOutOfBoundsException {
        String[] components = extractDeadlineComponents(line);
        int current = Task.totalNumberOfTasks;
        // components[0] contains name, components[1] contains date
        tasks.add(new Deadline(components[0], components[1]));
        Task.totalNumberOfTasks += 1;
        this.ui.printAddedToList(current, tasks);
    }

    /**
     * Extracts the name and date of the deadline after checking validity of input.
     *
     * @param line user input.
     * @return components, a string array with 2 elements: name and date.
     * @throws EmptyNameFieldException if task name is not given or is all whitespace.
     * @throws WrongFormatException if task name does not contain substring "/by"
     * @throws EmptyDateException if date is not given or is all whitespace.
     * @throws DateTimeException if date given cannot be parsed as LocalDate.
     * @throws ArrayIndexOutOfBoundsException if there is nothing after the "/by".
     */
    public String[] extractDeadlineComponents(String line) throws EmptyNameFieldException,
            WrongFormatException, EmptyDateException, DateTimeException, ArrayIndexOutOfBoundsException {
        validateDeadlineComponents(line);
        String nameAndDate = line.substring(8);
        String[] components = nameAndDate.split(" /by ");
        String name = components[0];
        String date = components[1];
        // Remove any leading or trailing whitespace from name and date.
        components[0] = name.trim();
        components[1] = date.trim();

        return components;
    }

    /** Does the following validation checks on input of type Deadline:
     *  1. Check for the substring "/by"
     *  2. Checks that name and date of task are not empty or all whitespace
     *  3. Checks that format of date is correct
     *
     * @param line user input.
     * @throws EmptyNameFieldException if name is not provided or all whitespace.
     * @throws WrongFormatException if input does not contain substring "/by".
     * @throws EmptyDateException if date is not given or all whitespace.
     * @throws DateTimeException if date is wrong format.
     * @throws ArrayIndexOutOfBoundsException if there are no characters after the substring "/by".
     */
    public void validateDeadlineComponents(String line) throws EmptyNameFieldException,
            WrongFormatException, EmptyDateException, DateTimeException, ArrayIndexOutOfBoundsException {
        if (!line.contains("/by")) {
            throw new WrongFormatException();
        }
        String nameAndDate = line.substring(8);
        String[] components = nameAndDate.split(" /by ");
        String name = components[0];
        String date = components[1];
        if (name.trim().length() == 0) {
            throw new EmptyNameFieldException();
        }
        if (date.trim().length() == 0) {
            throw new EmptyDateException();
        }
        // Checks if date is of correct format
        LocalDate.parse(date.trim());
    }

    /**
     * Adds a task of type event into the list after checking validity of input.
     *
     * @param line user input.
     * @param tasks ArrayList of tasks.
     * @throws EmptyNameFieldException if task name is not given or all whitespace.
     * @throws WrongFormatException if input does not contain substring "/at".
     * @throws EmptyDateException if date is not given or all whitespace.
     * @throws DateTimeException if date cannot be parsed as LocalDate.
     * @throws ArrayIndexOutOfBoundsException if there is nothing after the "/by".
     */
    public void addEvent(String line, ArrayList<Task> tasks) throws EmptyNameFieldException,
            WrongFormatException, EmptyDateException, DateTimeException, ArrayIndexOutOfBoundsException {
        String[] components = extractEventComponents(line);
        int current = Task.totalNumberOfTasks;
        // components[0] contains name, components[1] contains date
        tasks.add(new Event(components[0], components[1]));
        Task.totalNumberOfTasks += 1;
        this.ui.printAddedToList(current, tasks);
    }

    /**
     * Extracts name and date of task after checking validity of input.
     *
     * @param line user input.
     * @return components, a String array with 2 elements: name and date.
     * @throws WrongFormatException if task does not contain substring "/at".
     * @throws EmptyNameFieldException if task name is not given or all whitespace.
     * @throws EmptyDateException if date is not given or all whitespace.
     * @throws DateTimeException if date cannot be parsed as LocalDate.
     * @throws ArrayIndexOutOfBoundsException if there is nothing after the "/at".
     */
    public String[] extractEventComponents(String line) throws WrongFormatException,
            EmptyNameFieldException, EmptyDateException, DateTimeException, ArrayIndexOutOfBoundsException {
        validateEventComponents(line);
        String nameAndDate = line.substring(5);
        String[] components = nameAndDate.split(" /at ");
        String name = components[0];
        String date = components[1];
        // Remove any leading or trailing whitespace from name and date.
        components[0] = name.trim();
        components[1] = date.trim();

        return components;
    }

    /** Does the following validation checks on input of type Event:
     *  1. Check for the substring "/at"
     *  2. Checks that name and date of task are not empty or all whitespace
     *  3. Checks that format of date is correct
     *
     * @param line user input.
     * @throws EmptyNameFieldException if name is not provided or all whitespace.
     * @throws WrongFormatException if input does not contain substring "/at".
     * @throws EmptyDateException if date is not given or all whitespace.
     * @throws DateTimeException if date is wrong format.
     * @throws ArrayIndexOutOfBoundsException if there are no characters after the substring "/at".
     */
    public void validateEventComponents(String line) throws WrongFormatException,
            EmptyNameFieldException, EmptyDateException, DateTimeException, ArrayIndexOutOfBoundsException {
        if (!line.contains("/at")) {
            throw new WrongFormatException();
        }
        String nameAndDate = line.substring(5);
        String[] components = nameAndDate.split(" /at ");
        String name = components[0];
        String date = components[1];
        if (name.trim().length() == 0) {
            throw new EmptyNameFieldException();
        }
        if (date.trim().length() == 0) {
            throw new EmptyDateException();
        }
        // Checks if date is of correct format
        LocalDate.parse(date.trim());
    }
}
