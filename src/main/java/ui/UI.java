package ui;

import task.list.TaskList;

import java.util.ArrayList;

public class UI {

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

    public static int getNumberOfTaskRemaining(ArrayList<TaskList> tasks) {
        int counter = 0;
        for (TaskList t : tasks) {
            if (!t.getIsTaskDone()) {
                counter++;
            }
        }
        return counter;
    }

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


    public static void printHelp() {
        String help = "           help           :prints list of all commands" + System.lineSeparator()
                + "           list           :prints all lists ordered by category" + System.lineSeparator()
                + "           exit           :shuts down Arthur" + System.lineSeparator()
                + "       todo <task>        :add task to To Do list" + System.lineSeparator()
                + "   event <task> /<time>   :add task to Event List with time" + System.lineSeparator()
                + "   deadline <task /<by>   :add task to Deadline List" + System.lineSeparator()
                + "       done  <index>      :mark task <index> as done in List" + System.lineSeparator();

        System.out.println(help);
        printDottedLines();

    }


    public static void printListName() {
        System.out.println("ATTENTION, Here's your list of tasks Crewmate!!!");
    }

    public static void printDottedLines() {
        System.out.println("____________________________________________________________________________________");
    }


    public static void printCommandDoesNotExist() {
        System.out.println("There's no such command!!! You look SUS!!!  (ー_ーゞ");
    }

    public static void printWrongTaskDoneName() {
        System.out.println("This task has already been completed crewmate!! Watchu doin??? (¬_¬)");
    }

    public static void printInvalidTaskPhrase() {
        System.out.println("There's no such task?! Focus Crewmate!!  (╬⓪益⓪)");
    }


    public static void printNoTasksDone() {
        System.out.println("Are you really a Crewmate??? You haven't done any work in this list! （○｀Ｏ´○）");
    }

    public static void printCompletedTasks() {
        System.out.println("Good job Crewmate! You completed all your tasks in this list! (─‿─)");
    }

    public static void printEmptyList() {
        System.out.println("This list is empty!!! YEEEEEEET!!!");
        printDottedLines();
    }


    public static void printSomeTasksRemaining(int counter) {
        System.out.println("You still have "
                + counter + " task(s) left in your Lists ! Hurry up!! ＼(｀0´)／");
    }

    public static void printGoodEnding() {
        System.out.print("Thanks for your help Crewmate!!" + System.lineSeparator()
                + "We wouldn't have done this without your help!!" + System.lineSeparator()
                + "Goodbye!!!! (￣▽￣)ノ" + System.lineSeparator());
        printDottedLines();

    }

    public static void printBadEnding() {
        System.out.print("You have been kicked out! Bye Impostor!!!  (๑>ᴗ<๑)" + System.lineSeparator());
        printDottedLines();

    }

    public static void printTraitor() {
        System.out.print("You are abandoning us!!! I trusted you!!!  (　ﾟдﾟ)" + System.lineSeparator());
        printDottedLines();
    }

    public static void printLoading() {
        System.out.println("Loading your previous data . . .");
    }

    public static void printEmptyFile() {
        System.out.println("Your saved file is empty!");
    }

    public static void printNoSavedFile() {
        System.out.println("Loading New Duke Window . . .");
    }

    public static void printDeletingTask() {
        System.out.println(" (o‿∩) Hol' up! I'm deleting this task:");
    }
}
