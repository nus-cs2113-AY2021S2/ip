package duke.inputhandlers;

import duke.exception.FullListException;
import duke.exception.InvalidCommandException;
import duke.storage.FileManager;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static duke.Duke.tasks;
import static duke.Duke.ui;
import static duke.constants.ProgramInts.*;
import static duke.constants.UiStrings.*;

/**
 * This class receives user input, sends it to the parser, then runs the commands chosen by the user.
 * Also handles situation where user input is invalid or list is full.
 */

public class CommandRunner {

    /**
     * Loops indefinitely, receiving user input until "bye" command is received.
     * Checks list capacity at each iteration. 
     * When list is full, user cannot add new tasks until tasks are deleted.
     * User can still list tasks, enter a find query, mark tasks as done or print the help manual.
     */
    public void receiveUserInput() {
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        
        while (!isExit) {
            String input = in.nextLine();
            int command = Parser.parseCommand(input);
            
            try {
                checkListCapacity(command);
            } catch (FullListException e) {
                ui.printListFullWarning();
                continue;
            }

            isExit = selectCommandToRun(command, input);
        }
    }

    /**
     * Selects the method to run based on the command entered.
     * 
     * @param command the integer representation of the command parsed from user input
     * @param input full user input string
     * @return false only when exit command is given. Calls the respective method for other commands
     */
    private boolean selectCommandToRun(int command, String input) {
        switch (command) {
        case BYE_COMMAND:
            return true;
        case LIST_COMMAND:
            runList();
            break;
        case HELP_COMMAND:
            ui.printHelp();
            break;
        case DONE_COMMAND:
            runDone(input);
            FileManager.saveData();
            break;
        case TODO_COMMAND:
            runTodo(input);
            FileManager.saveData();
            break;
        case DEADLINE_COMMAND:
            runDeadline(input);
            FileManager.saveData();
            break;
        case EVENTS_COMMAND:
            runEvent(input);
            FileManager.saveData();
            break;
        case DELETE_COMMAND:
            runDeleteCommand(input);
            FileManager.saveData();
            break;
        case FIND_COMMAND:
            runFindCommand(input);
            break;
        default:
            runUnknownCommand(input);
        }

        return false;
    }

    /**
     * Marks the task specified by user as 'done'
     * 
     * @param input full user input string
     */
    private void runDone(String input) {
        String[] word = input.split(" ");
        int jobNumber = 0;

        try {
            jobNumber = Integer.parseInt(word[1]) - 1;

            // returns when there is no jobs in the list
            if (tasks.getCount() == 0) {
                ui.printNoTaskWarning();
                return;
            }
            markJobAsDone(tasks.get(jobNumber));

        } catch (NumberFormatException e) {
            ui.printInvalidInputWarning(input);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printInvalidIndexWarning(jobNumber);
        }

    }

    /**
     * Sets the done property of given task to true.
     * Prints prompt on screen displaying the task that was marked.
     * 
     * @param task the task to be marked
     */
    private void markJobAsDone(Task task) {
        task.setDone(true);
        System.out.print(PROMPT_TASK_DONE);
        task.printTask();
        System.out.println();
    }


    /**
     * Prints every task in the list.
     * Displays warning message if task list is empty, does not print empty list.
     */
    private void runList() {
        int numbering = 1;

        // prints warning message when task list is empty
        if (tasks.getCount() == 0) {
            ui.printNoTaskWarning();
            return;
        }

        System.out.println(SHORT_LINE);
        System.out.println("TASK LIST:");
        System.out.println(SHORT_LINE);

        for (Task task : tasks.getTasks()) {
            ui.printTaskAsList(numbering, task);
            numbering++;
        }
        System.out.println();
    }

    /**
     * Adds a task of type Todo into the task list.
     * Task description is parsed from user input.
     * 
     * @param input full user input string
     */
    private void runTodo(String input) {
        String job;

        try {
            job = Parser.parseDescription(input);
        } catch (InvalidCommandException e) {
            ui.printInvalidInputWarning(input);
            return;
        }

        Todo newTask = new Todo(job);
        tasks.add(newTask);
        ui.printTaskAdded(newTask);
    }

    /**
     * Adds a task of type Deadline into the task list.
     * Task description and deadline is parsed from user input.
     * 
     * @param input full user input string
     */
    private void runDeadline(String input) {
        String job;
        String by;

        try {
            job = Parser.parseDescription(input, "/by");
            by = Parser.parseDate(input, "/by");
        } catch (InvalidCommandException e) {
            ui.printInvalidInputWarning(input);
            return;
        } catch (DateTimeParseException e) {
            ui.printInvalidDateFormatWarning();
            return;
        }

        Deadline newTask = new Deadline(job, by);
        tasks.add(newTask);
        ui.printTaskAdded(newTask);
    }

    /**
     * Adds a task of type Event into the task list.
     * Task description and event time is parsed from user input.
     * 
     * @param input full user input string
     */
    private void runEvent(String input) {
        String job, at;

        try {
            job = Parser.parseDescription(input, "/at");
            at = Parser.parseDate(input, "/at");
        } catch (InvalidCommandException e) {
            ui.printInvalidInputWarning(input);
            return;
        } catch (DateTimeParseException e) {
            ui.printInvalidDateFormatWarning();
            return;
        }

        Event newTask = new Event(job, at);
        tasks.add(newTask);
        ui.printTaskAdded(newTask);
    }

    /**
     * Handles unrecognized input by printing error message.
     * 
     * @param input full user input string
     */
    private void runUnknownCommand(String input) {
        ui.printUnknownCommandWarning(input);
    }

    /**
     * Deletes a task specified by the user.
     * Task index is parsed from user input.
     * 
     * @param input full user input string
     */
    private void runDeleteCommand(String input) {

        String[] words = input.split(" ");
        int index = 0;

        try {
            index = Integer.parseInt(words[1]) - 1;
            if (tasks.contains(tasks.get(index))) {
                ui.printTaskDeleted(index);
                tasks.remove(index);
            }
        } catch (NumberFormatException e) {
            ui.printInvalidInputWarning(input);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidIndexWarning(index);
        }
    }

    /**
     * Prints tasks which contain the keyword specified by user.
     * Search keyword is parsed from user input.
     * Prints warning message if no matches are found.
     * 
     * @param input full user input string
     */
    private void runFindCommand(String input) {
        ArrayList<Task> matches = new ArrayList<>();
        int numbering = 1;
        String keyword = null;

        // parse keyword from user input
        try {
            keyword = Parser.parseKeyword(input);
        } catch (InvalidCommandException e) {
            ui.printInvalidInputWarning(input);
            return;
        }

        // collect tasks which match keyword
        for (Task t : tasks.getTasks()) {
            if (t.getDescription().contains(keyword)) {
                matches.add(t);
            }
        }

        // check if no matches
        if (matches.size() == 0) {
            ui.printNoMatchWarning(keyword);
            return;
        }

        // else print the matching tasks
        ui.printSearchResultsHeader(keyword);
        for (Task t : matches) {
            ui.printTaskAsList(numbering, t);
            numbering++;
        }
        System.out.println();
    }

    /**
     * Checks list capacity before a command is ran.
     * 
     * @param command command selected to run
     * @throws FullListException if list is full and selected command is not allowed when list is full.
     */
    private void checkListCapacity(int command) throws FullListException {
        Task.isFull = (tasks.getCount() == MAX_TASK);
        if (Task.isFull && !isAllowedWhenListFull(command)) {
            throw new FullListException();
        }
    }

    /**
     * Checks if given command is allowed to run when list is full.
     * User is not allowed to add more tasks when list is full.
     * i.e. "todo", "deadline" and "event" commands are not allowed.
     * User is still allowed to view list, mark tasks as done, delete tasks, and find tasks with keywords.
     * 
     * @param command command to be checked
     * @return true if command is allowed when list is full
     */
    private boolean isAllowedWhenListFull(int command) {
        boolean isAllowed;
        isAllowed = !(command == TODO_COMMAND || command == DEADLINE_COMMAND || command == EVENTS_COMMAND);
        return isAllowed;
    }
}
