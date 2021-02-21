package duke.inputhandlers;

import duke.exception.FullListException;
import duke.exception.InvalidCommandException;
import duke.storage.FileManager;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.Duke.tasks;
import static duke.Duke.ui;
import static duke.constants.ProgramInts.*;
import static duke.constants.UiStrings.*;

/**
 * Runs the commands entered by the user.
 * Also handles when the list is full by only allowing "list" and "bye" command.
 * TODO: allow delete command
 */

public class CommandRunner {

    public void receiveUserInput() {
        Scanner in = new Scanner(System.in);
        boolean isExit = false;

        //Loop to receive response.
        while (!isExit) {
            String input = in.nextLine();
            int command = Parser.parseCommand(input);

            // If list is full, will only allow LIST and BYE command to pass
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
     * PRIVATE COMMAND RUNNERS
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

    private void runDone(String input) {
        String[] word = input.split(" ");
        int jobNumber = 0;

        try {
            jobNumber = Integer.parseInt(word[1]) - 1;

            // error handling - no jobs
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

    private void markJobAsDone(Task task) {
        task.setDone(true);
        System.out.print("Congrats! You've completed: \n   ");
        task.printTask();
        System.out.println();
    }

    private void runList() {
        int numbering = 1;

        // error handling - no jobs
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


    private void runTodo(String input) {
        String job;

        try {
            job = Parser.parseJob(input, "");
        } catch (InvalidCommandException e) {
            ui.printInvalidInputWarning(input);
            return;
        }

        Todo newTask = new Todo(job);
        tasks.add(newTask);
        ui.printTaskAdded(newTask);
    }

    private void runDeadline(String input) {
        String job;
        String by;

        try {
            job = Parser.parseJob(input, "/by");
            by = Parser.parseDate(input, "/by");
        } catch (InvalidCommandException e) {
            ui.printInvalidInputWarning(input);
            return;
        }

        Deadline newTask = new Deadline(job, by);
        tasks.add(newTask);
        ui.printTaskAdded(newTask);
    }

    private void runEvent(String input) {
        String job, at;

        try {
            job = Parser.parseJob(input, "/at");
            at = Parser.parseDate(input, "/at");
        } catch (InvalidCommandException e) {
            ui.printInvalidInputWarning(input);
            return;
        }

        Event newTask = new Event(job, at);
        tasks.add(newTask);
        ui.printTaskAdded(newTask);
    }

    private void runUnknownCommand(String input) {
        ui.printUnknownCommandWarning(input);
    }

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
            if (t.getJob().contains(keyword)) {
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
     * CHECK LIST CAPACITY
     */
    private boolean isAllowedWhenListFull(int command) {
        return (command == LIST_COMMAND || command == BYE_COMMAND);
    }


    private void checkListCapacity(int command) throws FullListException {
        if (tasks.getCount() == MAX_TASK && !Task.isFull) {
            Task.isFull = true;
        }

        if (Task.isFull && !isAllowedWhenListFull(command)) {
            throw new FullListException();
        }
    }
}
