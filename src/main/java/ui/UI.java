package ui;

import task.list.Task;

import java.util.ArrayList;

/**
 * Represents UI
 */
public class UI extends Dialogues {


    /**
     * prints corrupted files
     */
    public static void printCorruptedFile() {
        System.out.println(CORRUPTED_FILE);
    }

    /**
     * prints appropriate phrase when user tries to mark a task, which was already done, as done again
     *
     * @param tasks takes in list of tasks
     */
    public static void printTaskAlreadyCompletedPhrase(ArrayList<Task> tasks) {
        String phrase;
        if (getAreAllTasksDone(tasks)) {
            phrase = THIS_JOB_WAS_ALREADY_COMPLETED + System.lineSeparator()
                    + YOU_COMPLETED_ALL_YOUR_TASKS_IN_THIS_LIST + System.lineSeparator();

        } else {
            phrase = WHAT_ARE_YOU_DOING_THIS_JOB_WAS_ALREADY_COMPLETED + System.lineSeparator()
                    + TASK_LEFT_FIRST_PART + getNumberOfTaskRemaining(tasks)
                    + TASKS_LEFT_SECOND_PART;
        }
        System.out.println(phrase);
    }

    /**
     * returns the number of tasks which have not been done
     *
     * @param tasks takes in list of tasks
     * @return incomplete tasks counter
     */
    public static int getNumberOfTaskRemaining(ArrayList<Task> tasks) {
        int counter = 0;
        for (Task t : tasks) {
            if (!t.getIsTaskDone()) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * verifies if all tasks have not been done
     *
     * @param tasks takes in list of tasks
     * @return whether all tasks are not done
     */
    public static boolean getAreAllTasksNotDone(ArrayList<Task> tasks) {
        boolean areAllTasksNotDone = true;
        for (Task t : tasks) {
            if (t.getIsTaskDone()) {
                areAllTasksNotDone = false;
                break;
            }
        }
        return areAllTasksNotDone;
    }

    /**
     * verifies if all tasks have been done
     *
     * @param tasks takes in list of tasks
     * @return whether all tasks are done
     */
    public static boolean getAreAllTasksDone(ArrayList<Task> tasks) {
        boolean areAllTasksDone = true;
        for (Task t : tasks) {
            if (!t.getIsTaskDone()) {
                areAllTasksDone = false;
                break;
            }
        }
        return areAllTasksDone;
    }

    /**
     * prints message to greet the user
     */
    public static void greet() {
        String logo = getLogo();
        System.out.println(FIRST_LINE + System.lineSeparator() + logo);
        String greetings = DOTTED_LINE
                + System.lineSeparator()
                + GREETING + System.lineSeparator()
                + ASSIGN_ME_MY_TASKS_TO_COMPLETE + System.lineSeparator()
                + DOTTED_LINE
                + System.lineSeparator();
        System.out.println(greetings);
    }

    /**
     * gets arthur's logo
     *
     * @return logo
     */
    public static String getLogo() {
        return LOGO;

    }

    /**
     * prints help message
     */
    public static void printHelp() {
        System.out.println(HELP);
        printDottedLines();
    }

    /**
     * Prompts the user to input date for new task
     */
    public static void printInsertCorrectDateTask() {
        System.out.println(WRONG_DATE_FORMAT);
    }

    /**
     * Prompts the user to input date for search command
     */
    public static void printInsertCorrectDateSearch() {
        System.out.println(WRONG_DATE_FORMAT_SEARCH);
    }

    /**
     * Prints search command has been completed
     */
    public static void printSearchComplete() {
        System.out.println(SEARCH_HAS_BEEN_COMPLETED);
    }

    /**
     * Prints no match found
     */
    public static void printNoMatchFound() {
        System.out.println(NO_MATCH_FOUND);
    }

    /**
     * prints list's title
     */
    public static void printListName() {
        System.out.println(LIST_OF_TASKS_CREWMATE);
    }

    /**
     * prints a continuous dotted line
     */
    public static void printDottedLines() {
        System.out.println(DOTTED_LINE);
    }

    /**
     * prints command does not exist
     */
    public static void printCommandDoesNotExist() {
        System.out.println(NO_SUCH_COMMAND);
    }

    /**
     * prints cannot perform a done task again
     */
    public static void printWrongTaskDoneName() {
        System.out.println(THIS_TASK_HAS_ALREADY_BEEN_COMPLETED);
    }

    /**
     * prints task does not exist
     */
    public static void printInvalidTaskPhrase() {
        System.out.println(NO_SUCH_TASK);
    }

    /**
     * prints no tasks have been done
     */
    public static void printNoTasksDone() {
        System.out.println(YOU_HAVEN_T_DONE_ANY_WORK_IN_THIS_LIST);
    }

    /**
     * prints all tasks have been completed
     */
    public static void printCompletedTasks() {
        System.out.println(COMPLETED_ALL_YOUR_TASKS_IN_THIS_LIST);
    }

    /**
     * prints the list is empty
     */
    public static void printEmptyList() {
        System.out.println(THIS_LIST_IS_EMPTY);
        printDottedLines();
    }

    /**
     * prints number of tasks which have not been done
     *
     * @param counter takes in number of tasks remaining
     */
    public static void printSomeTasksRemaining(int counter) {
        System.out.println(YOU_STILL_HAVE + counter + TASK_S_LEFT_IN_YOUR_LISTS);
    }

    /**
     * prints a good ending
     */
    public static void printGoodEnding() {
        System.out.print(GOOD_ENDING + System.lineSeparator());
        printDottedLines();

    }

    /**
     * prints a bad ending
     */
    public static void printBadEnding() {
        System.out.print(BAD_ENDING + System.lineSeparator());
        printDottedLines();

    }

    /**
     * prints a normal ending
     */
    public static void printTraitor() {
        System.out.print(TRAITOR + System.lineSeparator());
        printDottedLines();
    }

    /**
     * notifies user when find is completed
     */
    public static void printFindComplete() {
        System.out.println(YOUR_SEARCH_HAS_BEEN_COMPLETED_CREWMATE);
    }

    /**
     * prints loading arthur.txt when Arthur is started
     */
    public static void printLoading() {
        System.out.println(LOADING_FILE);
    }

    /**
     * prints a message to indicate that arthur.txt is empty
     */
    public static void printEmptyFile() {
        System.out.println(FILE_IS_EMPTY);
    }

    /**
     * prints a message to indicate that arthur.txt does not exist
     */
    public static void printNoSavedFile() {
        System.out.println(LOADING_WINDOW);
    }

    /**
     * prints a message when a task is deleted
     */
    public static void printDeletingTask() {
        System.out.println(DELETING_THIS_TASK);
    }
}