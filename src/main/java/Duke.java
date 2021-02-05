import java.util.List;
import java.util.Scanner;
import java.util.*;

public class Duke {

    private static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void showExitMessage() {
        System.out.println("\tBye fellow coder! Hope to see you again soon!");
    }

    private static void listTasks(List<Task> list) {
        System.out.println("\tHere are the tasks in your list:");
        int i = 0;
        while (i < list.size()) {
            int num = i+1;
            System.out.println("\t" + num + ". " + list.get(i).toString());
            i++;
        }
    }

    private static void markAsDone(List<Task> list, int index) {
        list.get(index).markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + list.get(index).toString());
    }

    private static void printAddedTask(List<Task> list, int taskCounter) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + list.get(taskCounter).toString());
        int counter = taskCounter + 1;
        System.out.println("Now you have " + counter + " tasks in the list.\n");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        boolean isOn = true;
        int taskCounter = 0;
        List<Task> list = new ArrayList<Task>();

        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");

        while(isOn) {
            String line;
            line = getUserInput();
            String[] part = line.split("(?<=\\D)(?=\\d)");
            int commandIndex = line.indexOf(' ');
            int timeIndex = line.indexOf('/');
            String command = null;
            String test = null;
            if (commandIndex != -1) {
                command = line.substring(0, commandIndex);
            }

            if (line.equals("bye")) {
                showExitMessage();
                isOn = false;
            } else if (line.equals("list")) {
                listTasks(list);
            } else if (part[0].equals("done ")) {
                int index = Integer.parseInt(part[1]) - 1;
                markAsDone(list, index);
            } else if (command.equals("todo")) {
                String task = line.substring(commandIndex);
                list.add(new Todo(task));

                printAddedTask(list, taskCounter);
                taskCounter++;
            } else if (command.equals("deadline")) {
                String task = line.substring(commandIndex, timeIndex);
                String time = line.substring(timeIndex+4);
                list.add(new Deadline(task, time));

                printAddedTask(list, taskCounter);
                taskCounter++;
            } else if (command.equals("event")) {
                String task = line.substring(commandIndex, timeIndex);
                String time = line.substring(timeIndex+4);
                list.add(new Event(task, time));

                printAddedTask(list, taskCounter);
                taskCounter++;
            }
        }
    }
}
