package Duke;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke { //implement toString() next

    public static void main(String[] args) {
        welcomeMessage();

        ArrayList<Task> taskList = new ArrayList<>();
        int taskCount = 0;
        Scanner in = new Scanner(System.in);
        String commandInput = in.nextLine();

        while (!commandInput.equals("bye")) {
            //Process commands taken from user, exit if user typed "bye"
            if (commandInput.equals("list")) {
                printList(taskList, taskCount);
            } else if (commandInput.startsWith("done")) {
                try {
                    int taskNumber = Integer.parseInt(commandInput.substring(5, 6));
                    taskList.get(taskNumber - 1).setDone();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! No task number detected, please try again.");
                } catch (NullPointerException e) {
                    System.out.println("☹ OOPS!!! Invalid number, please try again.");
                }
            } else if (commandInput.startsWith("todo")) {
                try {
                    taskList.add(new Todo(commandInput.substring(5)));
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description is empty, please try again.");
                }
            } else if (commandInput.startsWith("event")) {
                try {
                    int timeIndex = commandInput.indexOf("/at");
                    taskList.add(new Event(commandInput.substring(6, timeIndex), commandInput.substring(timeIndex + 1)));
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description is invalid, please try again.");
                }
            } else if (commandInput.startsWith("deadline")) {
                try {
                    int timeIndex = commandInput.indexOf("/by");
                    taskList.add(new Deadline(commandInput.substring(9, timeIndex), commandInput.substring(timeIndex + 1)));
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description is invalid, please try again.");
                }
            } else if (commandInput.startsWith("delete")) {
                try {
                    int taskNumber = Integer.parseInt(commandInput.substring(7, 8));
                    setDelete(taskList.get(taskNumber - 1), taskCount - 1);
                    taskList.remove(taskNumber - 1);
                    taskCount--;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! No task number detected, please try again.");
                } catch (NullPointerException e) {
                    System.out.println("☹ OOPS!!! Invalid number, please try again.");
                }
            } else {
                System.out.println("Invalid command entered, please try again.");
            }
            commandInput = in.nextLine();
        }
        exitMessage();
    }

    private static void printList(ArrayList<Task> taskList, int taskCount) {
        System.out.println("************************************************************");
        if (taskCount == 0) {
            System.out.println("You do not have any tasks at the moment! :)");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; ++i) {
                System.out.print(i + 1 + ". [" + taskList.get(i).getAlphabet() + "]");
                System.out.println("[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).description + taskList.get(i).time);
            }
        }
        System.out.println("************************************************************");

    }

    private static void setDelete(Task taskDeleted, int taskCount) {
        System.out.println("************************************************************");
        System.out.println("Noted. I've removed this task:");
        System.out.print("  [" + taskDeleted.getAlphabet() + "]");
        System.out.println("[" + taskDeleted.getStatusIcon() + "] " + taskDeleted.description + taskDeleted.time);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("************************************************************");

    }

    private static void exitMessage() {
        String ENDMESSAGE = "############################################################\n"
                + "Bye. Hope to see you again soon!\n"
                + "############################################################\n";
        System.out.println(ENDMESSAGE);
    }

    private static void welcomeMessage() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + LOGO);
        String STARTMESSAGE = "____________________________________________________________\n"
                + "Hello! I'm Duke.Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(STARTMESSAGE);
    }

}
