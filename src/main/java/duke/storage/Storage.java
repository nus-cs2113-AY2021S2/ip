package duke.storage;

import duke.Duke;
import duke.exception.IllegalTaskCommandException;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Storage {
    private static final String FILE_PATH = "Duke_Tasks.txt";

    public static void saveTasks() throws IOException {
        File file = new File(FILE_PATH);
        file.createNewFile(); // If file exists, no new file will be made

        FileWriter writer = new FileWriter(file);
        writer.write(TaskList.convertToFileInput());
        writer.close();
    }

    public static void loadTasks() throws IOException, IllegalTaskCommandException {
        File file = new File(FILE_PATH);

        if (file.exists()) {
            int counter = 1;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String [] fileInput = scanner.nextLine().split(",");
                String commandCode;
                switch (fileInput[0].strip()) {
                case "T":
                    commandCode = "TODO";
                    break;
                case "D":
                    commandCode = "DEADLINE";
                    break;
                case "E":
                    commandCode = "EVENT";
                    break;
                default:
                    commandCode = "";
                    break;
                }
                StringJoiner command = new StringJoiner(" ");
                for (int i = 0; i < fileInput.length; i++) {
                    if (i == 1) {
                        continue; // Skips the done
                    }
                    command.add(fileInput[i].strip());
                }

                Duke.executeCommand(commandCode, command.toString());

                if(fileInput[1].strip().equals("X")) {
                    //TaskList.markDone(String.valueOf(counter));
                    System.out.println("Placeholder!");
                }
                counter++;
            }
        } else {
            System.out.println("Archive not found, Commander!");
        }
    }
}
