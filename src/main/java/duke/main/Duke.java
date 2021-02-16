package duke.main;

import duke.command.AddCommand;
import duke.command.Command;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        loadTasks();
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        while (!input.equals("bye")) {
            Command command = Parser.parse(input);
            try {
                command.execute(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                end();
            }
            saveTasks();
            input = in.nextLine();
        }

    }

    public static void greet() {
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        end();
    }

    public static void end() {
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }

    public static void saveTasks() {
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "data");
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path file = FileSystems.getDefault().getPath(dir, "data", "duke.txt");
            File tasks = file.toFile();

            if (!tasks.exists()) {
                tasks.createNewFile();
            }

            writeToFile(file.toString(), TaskManager.tasks);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeToFile(String filePath, Task[] tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < TaskManager.numOfTasks; i++) {
            bufferedWriter.write(tasks[i].toString());
            bufferedWriter.write("\n");
        }
        bufferedWriter.close();
    }

    public static void loadTasks() {
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "data");
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path file = FileSystems.getDefault().getPath(dir, "data", "duke.txt");
            File tasks = file.toFile();

            if (!tasks.exists()) {
                tasks.createNewFile();
            }

            BufferedReader reader = Files.newBufferedReader(file);
            String data = reader.readLine();
            int i = 0;

            while (data != null) {
                Task newTask = toTask(data);
                TaskManager.tasks[TaskManager.numOfTasks] = newTask;
                TaskManager.numOfTasks++;
                i++;
                data = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Task toTask(String data) {
        String[] read = data.trim().split("]");
        if (read[0].contains("T")) {
            Task newTodo = new Todo(read[2].trim());
            if (read[1].contains("X")) {
                newTodo.setAsDone();
            }
            return newTodo;
        } else if (read[0].contains("D")) {
            String[] split = read[2].trim().split(" \\(by:");
            Task newDeadline = new Deadline(split[0], split[1].replace(")", ""));
            if (read[1].contains("X")) {
                newDeadline.setAsDone();
            }
            return newDeadline;
        } else if (read[0].contains("E")) {
            String[] split = read[2].trim().split(" \\(at:");
            Task newEvent = new Event(split[0], split[1].replace(")", ""));
            if (read[1].contains("X")) {
                newEvent.setAsDone();
            }
            return newEvent;
        }
        return null;
    }
}
