package duke;

import exceptions.DukeException;
import tasks.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    static final int TODO_LENGTH = 4;
    static final int EVENT_LENGTH = 5;
    static final int DEADLINE_LENGTH = 8;
    static final int DONE_LENGTH = 4;
    static final int DELETE_LENGTH = 6;
    static final int FIND_LENGTH = 4;

    enum UserCommands {
        LIST,
        DONE,
        DELETE,
        FIND
    }

    public static void processUserRequest(String task) {
        if (task.equalsIgnoreCase(UserCommands.LIST.toString().toLowerCase())) {
            Ui.printTaskList();
        }
        else if (task.contains(UserCommands.DONE.toString().toLowerCase())) {
            TaskList.markTaskDone(task);
        }
        else if (task.contains(UserCommands.DELETE.toString().toLowerCase())) {
            TaskList.deleteTask(task);
        }
        else if (task.contains(UserCommands.FIND.toString().toLowerCase())) {
            Ui.printMatchingTasks(task);
        }
        else {
            TaskList.addTask(task);
        }
    }

    public static Deadline processAddDeadline(String userInput) throws DukeException {
        if (userInput.length() == DEADLINE_LENGTH ) {
            throw new DukeException(TaskType.DEADLINE);
        }
        String description, by;
        userInput = userInput.substring(DEADLINE_LENGTH+1).trim();
        if (userInput.contains("/")){
            int idx = userInput.indexOf('/');
            description = userInput.substring(0, idx);
            by = userInput.substring(idx+3).trim();
            LocalDate date = processDatesTimes(by);
            if (date != null) {
                return new Deadline(description, date);
            }
        }
        else {
            description = userInput;
            by = "";
        }
        return new Deadline(description, by);
    }

    public static Event processAddEvent(String userInput) throws DukeException {
        if (userInput.length() == EVENT_LENGTH) {
            throw new DukeException(TaskType.EVENT);
        }
        String description, at;
        userInput = userInput.substring(EVENT_LENGTH+1).trim();
        if (userInput.contains("/")){
            int idx = userInput.indexOf('/');
            description = userInput.substring(0, idx);
            at = userInput.substring(idx+3).trim();
            LocalDate date = processDatesTimes(at);
            if (date != null) {
                return new Event(description, date);
            }
        }
        else {
            description = userInput;
            at = "";
        }

        return new Event(description, at);
    }

    public static Todo processAddTodo(String userInput) throws DukeException {
        if (userInput.length() == TODO_LENGTH) {
            throw new DukeException(TaskType.TODO);
        }
        String description = userInput.substring(TODO_LENGTH+1);
        return new Todo(description);
    }

    public static LocalDate processDatesTimes(String dateString) {
        LocalDate date = null;
        Pattern patt = Pattern.compile("2[0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]");
        Matcher matcher = patt.matcher(dateString);
        boolean ifmatch = matcher.find();
        if (ifmatch) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(dateString.substring(matcher.start(), matcher.start()+10), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Unable to parse date ...");
            }
        }
        return date;
    }

    public static int processTaskDone(String userInput) {
        if (userInput.length() == DONE_LENGTH) {
            throw new IndexOutOfBoundsException();
        }
        int idx = -1;
        try {
            idx = Integer.parseInt(String.valueOf(userInput.charAt(DONE_LENGTH+1)));
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please enter an integer after 'done'.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please choose a valid task index.");
        }
        return idx;
    }

    public static int processDeleteTask(String userInput) {
        if (userInput.length() == DELETE_LENGTH) {
            throw new IndexOutOfBoundsException();
        }
        int idx = -1;
        try {
            idx = Integer.parseInt(String.valueOf(userInput.charAt(DELETE_LENGTH+1)));
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please enter an integer after 'delete'.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please choose a valid task index.");
        }
        return idx;
    }

    public static String processFindTask(String userInput) {
        // when the user did not enter search description
        if (userInput.length() == FIND_LENGTH) {
            return "";
        }
        return userInput.substring(FIND_LENGTH+1).trim();
    }
}
