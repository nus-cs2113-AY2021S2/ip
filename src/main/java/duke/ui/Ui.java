package duke.ui;

import duke.task.TaskList;

public class Ui {
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
        System.out.print("[" + TaskList.returnTaskType(index) + "]");
        printCompletionStatus(index);
        System.out.println(" " + TaskList.returnTaskItem(index));
        System.out.println();
        System.out.println("There are now " + TaskList.returnTaskCount() + " mission objective(s), Commander.");
        System.out.println(echoBottom);
    }

    public static void printMarkDonePrompt(int index) {
        String doneTextTop = "______________________________________________________\n"
                + "[Objective Completed]:\n";
        String doneTextBottom = "______________________________________________________\n";

        System.out.println(doneTextTop);
        System.out.print("[" + TaskList.returnTaskType(index) + "]");
        printCompletionStatus(index);
        System.out.println(" " + TaskList.returnTaskItem(index));
        System.out.println(doneTextBottom);
    }

    public static void printDeletePrompt(int taskIndex) {
        String doneTextTop = "______________________________________________________\n"
                + "[Objective Successfully Removed]:\n";
        String doneTextBottom = "______________________________________________________\n";

        System.out.println(doneTextTop);
        System.out.print("[" + TaskList.returnTaskType(taskIndex) + "]");
        printCompletionStatus(taskIndex);
        System.out.println(" " + TaskList.returnTaskItem(taskIndex));
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
}
