package Duke;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke { //implement toString() next

    public static void main(String[] args) throws IOException {
        welcomeMessage();

        Task[] taskList = new Task[100];
        int taskCount = 0;

        File dataFile = new File("data/tasks.txt");
        if (!dataFile.createNewFile()) {
            taskCount = downloadTasks(dataFile, taskList, taskCount);
        }

        Scanner in = new Scanner(System.in);
        String commandInput = in.nextLine();

        while (!commandInput.equals("bye")) {
            //Process commands taken from user, exit if user typed "bye"
            if (commandInput.equals("list")) {
                printList(taskList, taskCount);
            } else if (commandInput.startsWith("done")) {
                try {
                    int taskNumber = Integer.parseInt(commandInput.substring(5, 6));
                    taskList[taskNumber - 1].setDone();
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! No task number detected, please try again.");
                } catch (NullPointerException e) {
                    System.out.println("☹ OOPS!!! Invalid number, please try again.");
                }
            } else if (commandInput.startsWith("todo")) {
                try {
                    taskList[taskCount] = new Todo(commandInput.substring(5));
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description is empty, please try again.");
                }
            } else if (commandInput.startsWith("event")) {
                try {
                    int timeIndex = commandInput.indexOf("/at");
                    taskList[taskCount] = new Event(commandInput.substring(6, timeIndex), commandInput.substring(timeIndex + 1));
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description is invalid, please try again.");
                }
            } else if (commandInput.startsWith("deadline")) {
                try {
                    int timeIndex = commandInput.indexOf("/by");
                    taskList[taskCount] = new Deadline(commandInput.substring(9, timeIndex), commandInput.substring(timeIndex + 1));
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description is invalid, please try again.");
                }
            } else {
                System.out.println("Invalid command entered, please try again.");
            }
            commandInput = in.nextLine();
        }

        UploadTasks(taskList, taskCount);

        exitMessage();
    }

    private static void UploadTasks(Task[] taskList, int taskCount) throws java.io.IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (int i = 0; i < taskCount; ++i) {
            int taskStatus = taskList[i].isDone ? 1 : 0;
            if (taskList[i].getClass().getName().equals("Duke.Todo")) {
                fw.write("T | " + taskStatus + " | " + taskList[i].description + System.lineSeparator());
            } else if (taskList[i].getClass().getName().equals("Duke.Deadline")) {
                fw.write("D | " + taskStatus + " | " + taskList[i].description + taskList[i].time + System.lineSeparator());
            } else if (taskList[i].getClass().getName().equals("Duke.Event")) {
                fw.write("E | " + taskStatus + " | " + taskList[i].description + taskList[i].time + System.lineSeparator());
            }
        }
        fw.close();
    }

    private static int downloadTasks(File file, Task[] taskList, int taskCount) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String commandInput = s.nextLine();
            if (commandInput.startsWith("T")) {
                String description = commandInput.substring(8);
                taskList[taskCount] = new Todo(description);
            } else if (commandInput.startsWith("D")) {
                int timeIndex = commandInput.indexOf("(by");
                String description = commandInput.substring(8, timeIndex);
                taskList[taskCount] = new Deadline(description, commandInput.substring(timeIndex + 1));
            } else if (commandInput.startsWith("E")) {
                int timeIndex = commandInput.indexOf("(at");
                String description = commandInput.substring(8, timeIndex);
                taskList[taskCount] = new Deadline(description, commandInput.substring(timeIndex + 1));
            }
            if (commandInput.charAt(4) == '1') {
                taskList[taskCount].setDone();
            }
            taskCount++;
        }
        return taskCount;
    }

    private static void printList(Task[] taskList, int taskCount) {
        System.out.println("************************************************************");
        if (taskCount > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; ++i) {
                Class classType = taskList[i].getClass();
                System.out.print(i + 1 + ". [" + classType.getName().charAt(0) + "]");
                System.out.println("[" + taskList[i].getStatusIcon() + "] " + taskList[i].description + taskList[i].time);
            }
        } else {
            System.out.println("You have not entered any tasks at the moment! :)");
        }
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
