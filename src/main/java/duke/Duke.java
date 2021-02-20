package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int taskCount = 0;

    public static final String root = System.getProperty("user.dir");
    public static final Path filePath = Paths.get(root, "data", "duke.txt");
    public static final Path dirPath = Paths.get(root, "data");


    public static void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("---------------------------------------------------------");
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------------");
    }

    public static void bidGoodbye() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Lists the tasks in order
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++){
            System.out.print(i + ".");
            System.out.println(tasks.get(i-1).toString());
        }
    }

    /**
     * Confirms task has been added
     *
     * @param task Task object that was added
     */
    public static void printAddTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    public static void addTodo(String line) {
        try {
            int descriptionStart = 5;
            String description = line.substring(descriptionStart);
            tasks.add(new Todo(description));
            taskCount++;
            printAddTaskMessage(tasks.get(tasks.size() - 1));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void addDeadline(String line) {
        try {
            int descriptionStart = 9;
            int descriptionEnd = line.indexOf("/by") - 1;
            String description = line.substring(descriptionStart, descriptionEnd);
            int byStart = line.indexOf("/by") + 4;
            String by = line.substring(byStart);
            tasks.add(new Deadline(description, by));
            taskCount++;
            printAddTaskMessage(tasks.get(tasks.size() - 1));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public static void addEvent(String line) {
        try {
            int descriptionStart = 6;
            int descriptionEnd = line.indexOf("/at") - 1;
            String description = line.substring(descriptionStart, descriptionEnd);
            int atStart = line.indexOf("/at") + 4;
            String at = line.substring(atStart);
            tasks.add(new Event(description, at));
            taskCount++;
            printAddTaskMessage(tasks.get(tasks.size() - 1));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of an event cannot be empty.");
        }
    }

    public static void markDone(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(5));
            tasks.get(itemNum - 1).setAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(itemNum - 1).toString());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done is out of range");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done is invalid");
        }
    }

    public static void deleteTask(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(7));
            Task toBeDeleted = tasks.get(itemNum - 1);
            tasks.remove(itemNum - 1);
            taskCount--;
            System.out.println("Noted. I've removed this task:");
            System.out.println(toBeDeleted.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be deleted cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be deleted is out of range");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! The index of the task to be deleted is invalid");
        }

    }


    public static void request() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("---------------------------------------------------------");
            if (line.equals("list")) {
                printList();
            } else if (line.startsWith("todo")) {
                addTodo(line);
                saveFile();
            } else if (line.startsWith("deadline")) {
                addDeadline(line);
                saveFile();
            } else if (line.startsWith("event")) {
                addEvent(line);
                saveFile();
            } else if (line.startsWith("done")) {
                markDone(line);
                saveFile();
            } else if (line.startsWith("delete")) {
                deleteTask(line);
                saveFile();
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("---------------------------------------------------------");
            line = in.nextLine();
        }
        bidGoodbye();
        saveFile();
    }

    public static void loadFile() throws IOException{
        File fileDirectory = new File(dirPath.toString());

        if (!fileDirectory.exists()) {
            fileDirectory.mkdir();
        }

        File dataFile = new File(filePath.toString());
        dataFile.createNewFile();
        Scanner scanner = new Scanner(dataFile);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String type = line.substring(0, 1);
            String info = line.substring(8);
            int dateIndex = info.indexOf('|');
            switch (type) {
            case "T":
                tasks.add(new Todo(info));
                if (line.charAt(4) == 'Y') {
                    tasks.get(tasks.size() - 1).setAsDone();
                }
                break;
            case "D":
                tasks.add(new Deadline(info.substring(0, dateIndex - 1), info.substring(dateIndex + 2)));
                if (line.charAt(4) == 'Y') {
                    tasks.get(tasks.size() - 1).setAsDone();
                }
                break;
            case "E":
                tasks.add(new Event(info.substring(0, dateIndex - 1), info.substring(dateIndex + 2)));
                if (line.charAt(4) == 'Y') {
                    tasks.get(tasks.size() - 1).setAsDone();
                }
                break;
            default:
                break;
            }
        }
    }

    public static void saveFile() {
        try {
            FileWriter writer = new FileWriter(filePath.toString());
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    writer.write("T | " + task.getStatusIcon() + " | " + task.getDescription() + System.lineSeparator());
                } else if (task instanceof Deadline) {
                    writer.write("D | " + task.getStatusIcon() + " | " + task.getDescription() + " | " + ((Deadline) task).getBy() + System.lineSeparator());
                } else if (task instanceof Event) {
                    writer.write("E | " + task.getStatusIcon() + " | " + task.getDescription() + " | " + ((Event) task).getAt() + System.lineSeparator());
                } else {
                    return;
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        greet();
        try {
            loadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        request();
    }
}
