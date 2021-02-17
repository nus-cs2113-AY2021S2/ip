package duke;

import java.io.FileWriter;
import java.io.IOException;

import static duke.Duke.taskList;

public class FileSaver {

    private static final String FILE_DIRECTORY = "data";

    private static void writeToFile(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); ++i) {
            switch (taskList.get(i).getTaskType()) {
            case "[E]":
                //Fallthrough
            case "[D]":
                taskList.get(i).getDate();
                fileWriter.write(taskList.get(i).getTaskType() + " | "
                        + taskList.get(i).getIsDone() + " | "
                        + taskList.get(i).getDescription() + "| "
                        + taskList.get(i).getDate()
                        + System.lineSeparator());
                break;
            case "[T]":
                fileWriter.write(taskList.get(i).getTaskType() + " | "
                        + taskList.get(i).getIsDone() + " | "
                        + taskList.get(i).getDescription()
                        + System.lineSeparator());
            }
        }
        fileWriter.close();
    }

    public static void saveFile() {
        String filePath = "data/duke.txt";
        try {
            writeToFile(filePath);
            System.out.print("Data is saved\n");
        } catch (IOException ioException) {
            System.out.print("Something went wrong" + ioException.getMessage());
        }
    }

}
