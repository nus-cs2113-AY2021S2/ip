package duke.main;

import duke.command.Command;
import duke.task.Task;
import duke.task.TaskManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
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
}
