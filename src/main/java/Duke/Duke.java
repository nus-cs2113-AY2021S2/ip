package Duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {

    public static void main(String[] args) throws IOException {
        welcomeMessage();

        ArrayList<Task> taskList = new ArrayList<>();
        int taskCount = 0;

        File dataDir = new File("data");
        dataDir.mkdir();
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
                    taskList.get(taskNumber - 1).setDone();
                    taskList.get(taskNumber - 1).printDoneTask();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! No task number detected, please try again.");
                } catch (NullPointerException e) {
                    System.out.println("☹ OOPS!!! Invalid number, please try again.");
                }
            } else if (commandInput.startsWith("todo")) {
                try {
                    taskList.add(new Todo(commandInput.substring(5)));
                    taskList.get(taskCount).printDescription();
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description is empty, please try again.");
                }
            } else if (commandInput.startsWith("event")) {
                try {
                    int timeIndex = commandInput.indexOf("/at");
                    taskList.add(new Event(commandInput.substring(6, timeIndex - 1), commandInput.substring(timeIndex + 4)));
                    taskList.get(taskCount).printDescription();
                    taskCount++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description is invalid, please try again.");
                }
            } else if (commandInput.startsWith("deadline")) {
                try {
                    int timeIndex = commandInput.indexOf("/by");
                    taskList.add(new Deadline(commandInput.substring(9, timeIndex - 1), commandInput.substring(timeIndex + 4)));
                    taskList.get(taskCount).printDescription();
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

        uploadTasks(taskList, taskCount);

        exitMessage();
    }

    private static void uploadTasks(ArrayList<Task> taskList, int taskCount) throws java.io.IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (int i = 0; i < taskCount; ++i) {
            int taskStatus = taskList.get(i).isDone ? 1 : 0;
            if (taskList.get(i).getAlphabet().equals("T")) {
                fw.write("T | " + taskStatus + " | " + taskList.get(i).description + System.lineSeparator());
            } else {
                fw.write(taskList.get(i).getAlphabet() + " | " + taskStatus + " | " +
                        taskList.get(i).description + " | " + taskList.get(i).time + System.lineSeparator());
            }
        }
        fw.close();
    }

    private static int downloadTasks(File file, ArrayList<Task> taskList, int taskCount) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String commandInput = s.nextLine();
            if (commandInput.startsWith("T")) {
                String description = commandInput.substring(8);
                taskList.add(new Todo(description));
            } else if (commandInput.startsWith("D")) {
                int timeIndex = commandInput.lastIndexOf("| ");
                String description = commandInput.substring(8, timeIndex - 1);
                taskList.add(new Deadline(description, commandInput.substring(timeIndex + 2)));
            } else if (commandInput.startsWith("E")) {
                int timeIndex = commandInput.lastIndexOf("| ");
                String description = commandInput.substring(8, timeIndex - 1);
                taskList.add(new Event(description, commandInput.substring(timeIndex + 2)));
            }
            if (commandInput.charAt(4) == '1') {
                taskList.get(taskCount).setDone();
            }
            taskCount++;
        }
        return taskCount;
    }

    private static void printList(ArrayList<Task> taskList, int taskCount) {
        System.out.println("************************************************************");
        if (taskCount == 0) {
            System.out.println("You do not have any tasks at the moment! :)");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; ++i) {
                System.out.print(i + 1 + ". [" + taskList.get(i).getAlphabet() + "]");
                System.out.println("[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).toString());
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
