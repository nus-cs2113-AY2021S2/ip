package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printGreeting();

        try {
            loadTasks();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Scanner scannerIn = new Scanner(System.in);

        while (true) {
            String line = scannerIn.nextLine();
            String[] words = line.split(" ", 2);
            String command = words[0];
            String taskType = "";

            try {
                if (command.equals("list")) {
                    printList();
                } else if (command.equals("bye")) {
                    printBye();
                    break;
                } else if (command.equals("done")) {
                    taskType = "done";
                    markDone(line);
                } else if (command.equals("todo")) {
                    taskType = "todo";
                    addTodo(line);
                } else if (command.equals("deadline")) {
                    taskType = "deadline";
                    addDeadline(line);
                } else if (command.equals("event")) {
                    taskType = "event";
                    addEvent(line);
                } else if (command.equals("delete")) {
                    taskType = "delete";
                    deleteTask(line);
                } else if (command.equals("find")) {
                    taskType = "find";
                    findTask(line);
                } else {
                    throw new DukeException();
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of a " + taskType + " cannot be empty.");

            } catch (DukeException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        try {
            saveTasks();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void findTask(String line) {
        String[] words = line.split(" ", 2);
        String keyword = words[1];
        System.out.println("Here are the matching tasks in your list: ");
        int index = 0;
        while (index < tasks.size()) {
            if (tasks.get(index).toString().contains(keyword)) {
                System.out.println((index + 1) + "." + tasks.get(index).toString());
            }
            index++;
        }
    }

    private static void deleteTask(String line) {
        String[] words = line.split(" ", 2);
        int deleteIndex = Integer.parseInt(words[1]);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(deleteIndex - 1).toString());
        tasks.remove(deleteIndex - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEvent(String line) {
        String[] words = line.split(" ", 2);
        String detailWords = words[1];
        String[] info = detailWords.split(" /at ", 2);
        String taskDescription = info[0];
        String atTime = info[1];
        tasks.add(new Event(taskDescription, atTime));
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadline(String line) {
        String[] words = line.split(" ", 2);
        String detailWords = words[1];
        String[] info = detailWords.split(" /by ", 2);
        String taskDescription = info[0];
        String byDate = info[1];
        tasks.add(new Deadline(taskDescription, byDate));
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addTodo(String line) {
        String[] words = line.split(" ", 2);
        String taskDescription = words[1];
        tasks.add(new Todo(taskDescription));
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void markDone(String line) {
        String[] words = line.split(" ", 2);
        int doneIndex = Integer.parseInt(words[1]);
        Task doneTask = tasks.get(doneIndex - 1);
        doneTask.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(doneTask.toString());
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list: ");
        int index = 0;
        while (index < tasks.size()) {
            System.out.println((index + 1) + "." + tasks.get(index).toString());
            index++;
        }
    }

    public static void printBye() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    private static void printGreeting() {
        System.out.println("Hello from\n" + Duke.LOGO);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    private static void loadTasks() throws IOException {
        File file = new File("data/duke.txt");
        file.getParentFile().mkdir();
        file.createNewFile();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            loadData(scanner.nextLine());
        }
    }

    private static void loadData(String nextLine) {
        String[] commandWords = nextLine.split("\\|");
        String commandType = commandWords[0];
        if (commandType.equals("T")) {
            String[] words = nextLine.split("\\|");
            String taskStatus = words[1];
            String taskDescription = words[2];
            tasks.add(new Todo(taskDescription));
            if (taskStatus.equals("true")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        } else if (commandType.equals("D")) {
            String[] words = nextLine.split("\\|");
            String taskStatus = words[1];
            String taskDescription = words[2];
            String taskBy = words[3];
            tasks.add(new Deadline(taskDescription, taskBy));
            if (taskStatus.equals("true")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        } else if (commandType.equals("E")) {
            String[] words = nextLine.split("\\|");
            String taskStatus = words[1];
            String taskDescription = words[2];
            String taskAt = words[3];
            tasks.add(new Event(taskDescription, taskAt));
            if (taskStatus.equals("true")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
    }

    private static void saveTasks() throws IOException {
        int index = 0;
        FileWriter fileWriter = new FileWriter("data/duke.txt");
        while (index < tasks.size()) {
            fileWriter.write(tasks.get(index).saveTask() + System.lineSeparator());
            index++;
        }
        fileWriter.close();
    }

}
