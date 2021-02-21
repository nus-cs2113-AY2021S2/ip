package Duke;

import Duke.Tasks.Task;

import java.util.ArrayList;

public class Output {

    public static String printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo;
    }

    public static String printLine() {
        String line = "____________________________________________________________";
        return line;
    }

    public static String printGreet() {
        String greetMessage = "Hello! I'm Duke\n"
                + printLogo()
                + "\nWhat can I do for you?";
        return greetMessage;
    }


    public static String printDeleted(String taskName, int size) {
        String deleteMessage = String.format("Noted. I've removed this task:\n  %1$s \nNow you have %2$d tasks in the list.",
                taskName,
                size);
        return deleteMessage;
    }

    public static String printAdded(String taskName, int size) {
        String addMessage = String.format("Got it. I've added this task:\n  %1$s \nNow you have %2$d tasks in the list.",
                taskName,
                size);
        return addMessage;
    }

    public static String printUpdated(String taskName) {
        String updateMessage = String.format("Nice! I've marked this task as done:\n  %s",
                taskName);
        return updateMessage;
    }

    public static String printTask(Task task) {
        return task.toString();
    }

    public static String printTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "You do not have any tasks currently.";
        }
        String listTasksMessage = "";
        for (int i = 0; i < taskList.size(); i++) {
            listTasksMessage += String.format("%d. ", i + 1);
            listTasksMessage += printTask(taskList.get(i));
            listTasksMessage += "\n";
        }
        return listTasksMessage;
    }


    public static String printExit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        return exitMessage;
    }

    public static String printSave() {
        String saveMessage = "Sure! Your data has been saved.";
        return saveMessage;
    }

}