package ui;

import task.list.TaskList;

import java.util.ArrayList;

/**
 * Represents UI
 */
public class UI {


    // prints appropriate phrase when user tries to mark a task, which was already done, as done again
    public static void printTaskAlreadyCompletedPhrase(ArrayList<TaskList> tasks) {
        String phrase;
        if (getAreAllTasksDone(tasks)) {
            phrase = "This job was already completed!!!!" + System.lineSeparator()
                    + "Good job Crewmate! You completed all your tasks in this list! (─‿─)" + System.lineSeparator();

        } else {
            phrase = "What are you doing??? This job was already completed!! (;¬_¬)" + System.lineSeparator()
                    + "You still have " + getNumberOfTaskRemaining(tasks)
                    + " tasks left in this list Crewmate! Hurry up!! ＼(｀0´)／";
        }
        System.out.println(phrase);
    }

    // returns the number of tasks which have not been done
    public static int getNumberOfTaskRemaining(ArrayList<TaskList> tasks) {
        int counter = 0;
        for (TaskList t : tasks) {
            if (!t.getIsTaskDone()) {
                counter++;
            }
        }
        return counter;
    }

    // verifies if all tasks have not been done
    public static boolean getAreAllTasksNotDone(ArrayList<TaskList> tasks) {
        boolean areAllTasksNotDone = true;
        for (TaskList t : tasks) {
            if (t.getIsTaskDone()) {
                areAllTasksNotDone = false;
                break;
            }
        }
        return areAllTasksNotDone;
    }

    // verifies if all tasks have been done
    public static boolean getAreAllTasksDone(ArrayList<TaskList> tasks) {
        boolean areAllTasksDone = true;
        for (TaskList t : tasks) {
            if (!t.getIsTaskDone()) {
                areAllTasksDone = false;
                break;
            }
        }
        return areAllTasksDone;
    }

    // prints message to greet the user
    public static void greet() {
        String logo = getLogo();
        System.out.println("Ssshhhhhh!!!!!" + System.lineSeparator() + logo);
        String greetings = "____________________________________________________________________________________"
                + System.lineSeparator()
                + "Hello Crewmate! I'm Arthur, ( ͡°͜ ʖ ͡°)" + System.lineSeparator()
                + "Please assign me my tasks to complete!" + System.lineSeparator()
                + "____________________________________________________________________________________"
                + System.lineSeparator();
        System.out.println(greetings);
    }

    // gets Duke's logo
    private static String getLogo() {
        return "             ⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠀ ⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠀ ⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀  ⢀⣀⣀⠈⢻⣿⣿⡄" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀ ⣸⣿⡏⠀⠀⠀ ⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀ ⣿⣿⠁⠀⠀ ⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀ ⠈⠙⢿⣷⡄" + System.lineSeparator()
                + " ⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀ ⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⣿⣷" + System.lineSeparator()
                + " ⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀ ⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿" + System.lineSeparator()
                + " ⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀ ⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃" + System.lineSeparator()
                + " ⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀ ⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇" + System.lineSeparator()
                + " ⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⢸⣿⣧" + System.lineSeparator()
                + " ⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⣿" + System.lineSeparator()
                + " ⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⣿" + System.lineSeparator()
                + " ⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⡇" + System.lineSeparator()
                + " ⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⣿⣿⠃" + System.lineSeparator()
                + " ⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀  ⢸⣿⡇" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀  ⣸⣿⠇" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁" + System.lineSeparator();

    }

    // prints help message
    public static void printHelp() {
        String help = "               help                :prints list of all commands" + System.lineSeparator()
                + "               list                :prints all lists ordered by category" + System.lineSeparator()
                + "               exit                :shuts down Arthur" + System.lineSeparator()
                + "            todo <task>            :add task to To Do list" + System.lineSeparator()
                + "  event <task> // yyyy/MM/dd HHmm  :add task to Event List with time" + System.lineSeparator()
                + " deadline <task // yyyy/MM/dd HHmm :add task to Deadline List" + System.lineSeparator()
                + "            done  <index>          :mark task <index> as done in List" + System.lineSeparator()
                + "            delete  <index>        :delete task <index> in List" + System.lineSeparator()
                + "            find  <task>           :mark task <index> as done in List" + System.lineSeparator()
                + "         search yyyy/MM/dd         :add task to Deadline List" + System.lineSeparator();

        System.out.println(help);
        printDottedLines();
    }

    // Prompts the user to input date for new task
    public static void printInsertCorrectDateTask() {
        System.out.println("Please re-enter date in this format yyyy/MM/dd HHmm");
    }

    // Prompts the user to input date for search command
    public static void printInsertCorrectDateSearch() {
        System.out.println("Please re-enter date in this format yyyy/MM/dd:");
    }

    // Prints search command has been completed
    public static void printSearchComplete(){
        System.out.println("Search has been completed!! (￣^￣)ゞ");
    }

    // Prints no match found
    public static void printNoMatchFound(){
        System.out.println("No match found!! （／．＼）");
    }

    // prints list's title
    public static void printListName() {
        System.out.println("ATTENTION, Here's your list of tasks Crewmate!!!");
    }

    // prints a continuous dotted line
    public static void printDottedLines() {
        System.out.println("____________________________________________________________________________________");
    }

    // prints command does not exist
    public static void printCommandDoesNotExist() {
        System.out.println("There's no such command!!! You look SUS!!!  (ー_ーゞ");
    }

    // prints cannot perform a done task again
    public static void printWrongTaskDoneName() {
        System.out.println("This task has already been completed crewmate!! Watchu doin??? (¬_¬)");
    }

    // prints task does not exist
    public static void printInvalidTaskPhrase() {
        System.out.println("There's no such task?! Focus Crewmate!!  (╬⓪益⓪)");
    }

    // prints no tasks have been done
    public static void printNoTasksDone() {
        System.out.println("Are you really a Crewmate??? You haven't done any work in this list! （○｀Ｏ´○）");
    }

    // prints all tasks have been completed
    public static void printCompletedTasks() {
        System.out.println("Good job Crewmate! You completed all your tasks in this list! (─‿─)");
    }

    // prints the list is empty
    public static void printEmptyList() {
        System.out.println("This list is empty!!! YEEEEEEET!!!");
        printDottedLines();
    }

    // prints number of tasks which have not been done
    public static void printSomeTasksRemaining(int counter) {
        System.out.println("You still have "
                + counter + " task(s) left in your Lists ! Hurry up!! ＼(｀0´)／");
    }

    // prints a good ending
    public static void printGoodEnding() {
        System.out.print("Thanks for your help Crewmate!!" + System.lineSeparator()
                + "We wouldn't have done this without your help!!" + System.lineSeparator()
                + "Goodbye!!!! (￣▽￣)ノ" + System.lineSeparator());
        printDottedLines();

    }

    // prints a bad ending
    public static void printBadEnding() {
        System.out.print("You have been kicked out! Bye Impostor!!!  (๑>ᴗ<๑)" + System.lineSeparator());
        printDottedLines();

    }

    // prints a normal ending
    public static void printTraitor() {
        System.out.print("You are abandoning us!!! I trusted you!!!  (　ﾟдﾟ)" + System.lineSeparator());
        printDottedLines();
    }

    // notifies user when find is completed
    public static void printFindComplete(){
        System.out.println("Your search has been completed Crewmate! ( ´ ▽ ` )ﾉ");
    }

    // prints loading duke.txt when Duke is started
    public static void printLoading() {
        System.out.println("Loading your previous data . . .");
    }

    // prints a message to indicate that duke.txt is empty
    public static void printEmptyFile() {
        System.out.println("Your saved file is empty!");
    }

    // prints a message to indicate that duke.txt does not exist
    public static void printNoSavedFile() {
        System.out.println("Loading New Duke Window . . .");
    }

    // prints a message when a task is deleted
    public static void printDeletingTask() {
        System.out.println(" (o‿∩) Hol' up! I'm deleting this task:");
    }
}