package duke.ui;

import duke.task.Task;
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

    public static void echoInput(Task task, int index) {
        String taskString;
        String echoTop = "______________________________________________________\n"
                + "[Orders received]:\n";
        String echoBottom = "______________________________________________________\n";

        System.out.println(echoTop);
        TaskList.printTaskType(index);
        TaskList.printCompletionStatus(index);
        TaskList.printTaskItem(index);
        System.out.println();
        TaskList.printTaskCount();
        System.out.println(echoBottom);
    }

    public static void printMarkDonePrompt(int index) {
        String doneTextTop = "______________________________________________________\n"
                + "[Objective Completed]:\n";
        String doneTextBottom = "______________________________________________________\n";

        System.out.println(doneTextTop);
        TaskList.printTaskType(index);
        TaskList.printCompletionStatus(index);
        TaskList.printTaskItem(index);
        System.out.println();
        System.out.println(doneTextBottom);
    }
}
