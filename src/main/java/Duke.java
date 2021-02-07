import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static String getFirstWord(String text) {
        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.

            return text.substring(0, index).trim(); // Extract first word.

        } else {

            return text; // Text is the first word itself.
        }
    }

    private static void showExitMessage() {
        System.out.println("\tBye fellow coder! Hope to see you again soon!");
    }

    private static void listTasks(List<Task> tasks) {
        int i = 0;
        if (tasks.size() == 0) {
            System.out.println("\tWow! You have time on your hands! Go do something you enjoy :)");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            while (i < tasks.size()) {
                int num = i + 1;
                System.out.println("\t" + num + ". " + tasks.get(i).toString());
                i++;
            }
        }
    }

    private static void markAsDone(List<Task> tasks, int index) {
        tasks.get(index).markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + tasks.get(index).toString());
    }

    private static void printAddedTask(List<Task> tasks, int taskCounter) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + tasks.get(taskCounter).toString());
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
        List<Task> tasks = new ArrayList<Task>();

        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");

        while(isOn) {
            String line;
            line = getUserInput().trim();
            String[] part = line.split("(?<=\\D)(?=\\d)");
            int commandIndex = line.indexOf(' ');
            int timeIndex = line.indexOf('/');
            String command = getFirstWord(line);

            if (line.equals("bye")) {
                showExitMessage();
                isOn = false;
            } else if (line.equals("list")) {
                listTasks(tasks);
            } else if (part[0].equals("done")) {
                int index = Integer.parseInt(part[1]);
                markAsDone(tasks, index);
            } else if (command.equals("todo")) {
                try {
                    String task = line.substring(commandIndex);
                    tasks.add(new Todo(task));

                    printAddedTask(tasks, taskCounter);
                    taskCounter++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\tAre you sure you have nothing to do? :)");
                }
            } else if (command.equals("deadline")) {
                try {
                    String task = line.substring(commandIndex, timeIndex);
                    String time = line.substring(timeIndex + 4);
                    tasks.add(new Deadline(task, time));

                    printAddedTask(tasks, taskCounter);
                    taskCounter++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\tAre you sure you have no deadlines to meet? :)");
                }
            } else if (command.equals("event")) {
                try {
                    String task = line.substring(commandIndex, timeIndex);
                    String time = line.substring(timeIndex + 4);
                    tasks.add(new Event(task, time));

                    printAddedTask(tasks, taskCounter);
                    taskCounter++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\tAre you sure you have nothing going on? :)");
                }
            } else {
                System.out.println("\tI am afraid a computer program is not able to understand what you're saying!");
            }
        }
    }
}
