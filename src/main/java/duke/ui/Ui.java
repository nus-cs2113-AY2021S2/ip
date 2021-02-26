package duke.ui;

import duke.task.TaskList;

public class Ui {
    public static void printSuccessfulLoad() {
        String loaded = "******************************************************\n"
                + "*       Archive Successfully Loaded, Commander!      *\n"
                + "******************************************************\n";

        System.out.println(loaded);
    }

    public static void printUnsuccessfulLoad() {
        String notLoaded = "******************************************************\n"
                + "*Archive not found commander. Starting new archive...*\n"
                + "******************************************************\n";

        System.out.println(notLoaded);
    }

    public static void printUnsuccessfulCreation() {
        String failedCreation = "******************************************************\n"
                + "*     Archive creation was corrupted! Aborting...    *\n"
                + "******************************************************\n";

        System.out.println(failedCreation);
    }

    public static void printSuccessfulCreation() {
        String successCreation = "******************************************************\n"
                + "*      Archive successfully created, Commander!      *\n"
                + "******************************************************\n";

        System.out.println(successCreation);
    }

    public static void initialiseWelcomeMessage() {
        String logo
                = "******************************************************\n"
                + "*                 Systems: [Online]                  *\n"
                + "*                Protocol: [Dominion]                *\n"
                + "*                    Race: [Terran]                  *\n"
                + "******************************************************\n";

        String greeting
                = "______________________________________________________\n"
                + "Systems Accessed...\n"
                + "Decrypting Overwrite...\n"
                + "Welcome Commander, can I be of assistance, commander?\n"
                + "______________________________________________________\n";

        System.out.println(logo);
        System.out.println(greeting);
    }

    public static void printGoodbyeMessage() {
        String goodbye
                = "______________________________________________________\n"
                + "Good Bye Commander.\n"
                + "______________________________________________________\n";

        System.out.println(goodbye);
    }

    public static void printErrorLogo() {
        System.out.println("*****\n* ! *\n*****");
    }

    public static void printTaskHeader() {
        String tasksTemplate
                = "******************************************************\n"
                + "*                                                    *\n"
                + "*          [Objectives]-[Missions]-[Tasks]           *\n"
                + "*                                                    *\n"
                + "******************************************************";
        System.out.println(tasksTemplate);
    }

    public static void echoInput(int index) {
        String echoTop = "______________________________________________________\n"
                + "[Orders received]:\n";
        String echoBottom = "______________________________________________________\n";

        System.out.println(echoTop);
        printTaskItem(index);
        System.out.println();
        System.out.println("There are now " + TaskList.returnTaskCount() + " mission objective(s), Commander.");
        System.out.println(echoBottom);
    }

    public static void printMarkDonePrompt(int index) {
        String doneTextTop = "______________________________________________________\n"
                + "[Objective Completed]:\n";
        String doneTextBottom = "______________________________________________________\n";

        System.out.println(doneTextTop);
        printTaskItem(index);
        System.out.println(doneTextBottom);
    }

    public static void printTaskItem(int index) {
        System.out.print("[" + TaskList.returnTaskType(index) + "]");
        printCompletionStatus(index);
        System.out.print(" " + TaskList.returnTaskItem(index));
        System.out.println();
    }

    public static void printDeletePrompt(int taskIndex) {
        String doneTextTop = "______________________________________________________\n"
                + "[Objective Successfully Removed]:\n";
        String doneTextBottom = "______________________________________________________\n";

        System.out.println(doneTextTop);
        printTaskItem(taskIndex);
        System.out.println();
        System.out.println("There are now " + (TaskList.returnTaskCount() - 1) + " mission objective(s), Commander.");
        System.out.println(doneTextBottom);
    }

    public static void printTaskIndex(int taskIndex) {
        System.out.print("[" + (taskIndex + 1) + "]");
    }

    public static void printCompletionStatus(int index) {
        System.out.print("[" + TaskList.returnStatusIcon(index) + "]");
    }

    public static void printSearchHeader() {
        String searchHeader = "******************************************************\n"
                + "*          Processing all objectives now...          *\n"
                + "******************************************************\n";

        System.out.println(searchHeader);
    }

    public static void printNullSearch() {
        printErrorLogo();
        System.out.println("No matching objectives found, Commander!");
        System.out.println();
    }
}
